/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentprogreport.model;

import markbookreporter.reportbuilder.studentassessmentreport.model.*;
import java.util.LinkedList;
import java.util.List;
import markbookreporter.model.CompletionStatus;

/**
 *
 * @author General
 */
public class SPRAssessmentItem {
    private String name;
    private CompletionStatus completionStatus;
    private List<SPRAssessmentItemSection> assessmentItemSections = new LinkedList();
    private String categoryTitle;
    
    public int getPercentAttained(){
        //aggregate using basic combination
        double marksAttained = 0;
        double totalMarks = 0;
        for(SPRAssessmentItemSection result : getAssessmentItemSections()){
            //include the assessment section only if it hasnt been 'removed'
            if(result.getCompletionStatus() == CompletionStatus.REMOVED_FROM_ASSESSMENT){
                continue;
            }
            marksAttained += result.getMarkAttained();
            totalMarks += result.getTotalMarks();
        }
        if(totalMarks == 0) return 0;
        return (int)((marksAttained/totalMarks) * 100);        
    }
    
    public boolean allSectionsHaveCompletionStatus(CompletionStatus status){
        for(SPRAssessmentItemSection section : getAssessmentItemSections()){
            if(section.getCompletionStatus() != status){
                return false;
            }
        }
        return true;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the assessmentItemSections
     */
    public List<SPRAssessmentItemSection> getAssessmentItemSections() {
        return assessmentItemSections;
    }

    /**
     * @param assessmentItemSections the assessmentItemSections to set
     */
    public void setAssessmentItemSections(List<SPRAssessmentItemSection> assessmentItemSections) {
        this.assessmentItemSections = assessmentItemSections;
    }

    /**
     * @return the completionStatus
     */
    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    /**
     * @param completionStatus the completionStatus to set
     */
    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    /**
     * @return the categoryTitle
     */
    public String getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * @param categoryTitle the categoryTitle to set
     */
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
