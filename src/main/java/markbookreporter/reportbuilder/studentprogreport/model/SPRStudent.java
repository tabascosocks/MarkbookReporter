/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentprogreport.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import markbookreporter.model.CompletionStatus;
import markbookreporter.reportbuilder.studentprogreport.NumeracyChartTheme;
import markbookreporter.util.GraphingUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author General
 */
public class SPRStudent {

    private String lastName;
    private String firstName;
    private List<SPRAssessmentItem> assessmentItems = new LinkedList();

    public List<SPRAssessmentItem> getAssessmentItemsWithCategoryTitle(String categoryTitle){
        List<SPRAssessmentItem> result = new LinkedList();
        for(SPRAssessmentItem assessmentItem : getAssessmentItems()){
            if(assessmentItem.getCategoryTitle().equalsIgnoreCase(categoryTitle)){
                result.add(assessmentItem);
            }
        }
        return result;
    }
    
    /**
     * 
     * @return A list of all teh differetn cetagory titles for this stidents assesmsnet items
     */
    public Iterable<String> getAssessmentItemCategoryTitles(){
        Set<String> result = new HashSet();
        for(SPRAssessmentItem assessmentItem : getAssessmentItems()){          
            result.add(assessmentItem.getCategoryTitle());
        }
        return result;    
    }
    
    /**
     * build the dataset for the graph
     * @return 
     */
    public DefaultCategoryDataset getGraphDataset(){
        /* Step - 1: Define the data for the line chart  */
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(SPRAssessmentItem assessItem : getAssessmentItems()){
            //collect all the sections in this item that covers numeracy
            List<SPRAssessmentItemSection> numeracySections = new LinkedList();
            for(SPRAssessmentItemSection assessItemSection : assessItem.getAssessmentItemSections()){
                //if we have a section that link to numeracy AND has been attempted, then add
                if(assessItemSection.isIsNumeracyAssessment() && 
                       assessItemSection.getCompletionStatus() == CompletionStatus.ATTEMPTED){
                    numeracySections.add(assessItemSection);                   
                }
            }
            //if there were no numeracy sections in this item, then skip
            if(numeracySections.isEmpty())
                continue;
            //create an aggregated grade for the numberacy sections and add to
            //graph data
            double totalMarks = 0;
            double marksAttained = 0;
            for(SPRAssessmentItemSection numeracySection : numeracySections){         
                totalMarks += numeracySection.getTotalMarks();
                marksAttained += numeracySection.getMarkAttained();
            }

            //calculate the percentage
            int grade = 0;
            if(totalMarks != 0){
                grade = (int)((marksAttained / totalMarks) * 100);
            }
            dataset.addValue(grade, "grade" ,assessItem.getName() );             
            
        }
        return dataset;
    }
    
    public String getNumeracyGraph() {         
        DefaultCategoryDataset dataset = getGraphDataset();        
        /* Step -2:Define the JFreeChart object to create line chart */
        JFreeChart lineChartObject = ChartFactory.createLineChart("", "", "Grade (%)", dataset, PlotOrientation.VERTICAL, false, false, false);
        new NumeracyChartTheme().apply(lineChartObject);
        
        return GraphingUtils.convertChartToBase64PNGString(lineChartObject, 600, 370);
    }
    
    /**
     * If a produced numeracy graph would be pointless (ie, nothing to display)
     * then return false
     * @return 
     */
    public boolean shouldCreateNumeracyGraph(){
        return getGraphDataset().getColumnCount() >= 2;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the assessmentItems
     */
    public List<SPRAssessmentItem> getAssessmentItems() {
        return assessmentItems;
    }

    /**
     * @param assessmentItems the assessmentItems to set
     */
    public void setAssessmentItems(List<SPRAssessmentItem> assessmentItems) {
        this.assessmentItems = assessmentItems;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
