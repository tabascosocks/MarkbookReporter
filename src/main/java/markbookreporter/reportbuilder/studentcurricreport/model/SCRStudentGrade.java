/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport.model;

import java.util.HashMap;
import markbookreporter.enums.AggregationMethod;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import markbookreporter.model.CompletionStatus;
import markbookreporter.reportbuilder.studentcurricreport.StudentCurriculumReportBuilder;
import markbookreporter.reportbuilder.studentcurricreport.StudentCurriculumReportConfiguration;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author General
 */
public class SCRStudentGrade {
    
    protected List<SCRAssessmentItemResult> assessmentItemResults = new LinkedList();
    protected SCRProficiencyStrand proficiencyStrand;
    protected SCRCurriculumItem curriculumItem;
    /**
     * Gradient going from green to yellow to red
     */
    private String[] colorGradients = new String[]{"#17CC00","#1BCC00","#1FCD00","#24CE00","#28CE00","#2CCF00","#31D000","#35D100","#39D100","#3ED200","#42D300","#46D300","#4BD400","#4FD500","#53D600","#58D600","#5CD700","#60D800","#65D800","#69D900","#6DDA00","#72DB00","#76DB00","#7ADC00","#7FDD00","#83DD00","#88DE00","#8CDF00","#90E000","#95E000","#99E100","#9DE200","#A2E200","#A6E300","#AAE400","#AFE500","#B3E500","#B7E600","#BCE700","#C0E700","#C4E800","#C9E900","#CDEA00","#D1EA00","#D6EB00","#DAEC00","#DEEC00","#E3ED00","#E7EE00","#ECEF00","#ECEF01","#ECEA00","#ECE500","#ECE000","#ECDC00","#ECD700","#ECD200","#ECCE00","#ECC900","#ECC400","#ECC000","#ECBB00","#ECB600","#EDB200","#EDAD00","#EDA800","#EDA400","#ED9F00","#ED9A00","#ED9600","#ED9100","#ED8C00","#ED8800","#ED8300","#ED7E00","#EE7A00","#EE7500","#EE7000","#EE6C00","#EE6700","#EE6200","#EE5E00","#EE5900","#EE5400","#EE5000","#EE4B00","#EE4600","#EF4200","#EF3D00","#EF3800","#EF3400","#EF2F00","#EF2A00","#EF2600","#EF2100","#EF1C00","#EF1800","#EF1300","#EF0E00","#F00A00","#F00A00"};

    protected StudentCurriculumReportConfiguration config;
    
    public SCRStudentGrade(StudentCurriculumReportConfiguration config){
        this.config = config;
    }
    /**
     * @param aggregateNotAttempted whether or not 'Not Attempted' item sections should be included in the aggregation process
     * @return the percentAttained
     */
    public int getPercentAttained() {
        AggregationMethod aggregationMethod = StudentCurriculumReportBuilder.scrConfig.getAggregationMethod();
        
        if(aggregationMethod == AggregationMethod.ACCUMULATED){
            double marksAttained = 0;
            double totalMarks = 0;
            //aggregate using basic combination
            for(SCRAssessmentItemResult result : assessmentItemResults){
                //only calcualte the grade from questions which have been attempted
                if((result.getCompletionStatus() == CompletionStatus.ATTEMPTED) || 
                        (config.isAggregateNotAttempted() && result.getCompletionStatus() == CompletionStatus.NOT_ATTEMPTED)){
                    marksAttained += result.getMarksAttained();
                    totalMarks += result.getAssessmentItemSectionTotalMarks();
                }
            }
            if(totalMarks == 0) return 0;
            return (int)((marksAttained/totalMarks) * 100);
        }else if(aggregationMethod == AggregationMethod.TAKE_HIGHEST){
            double bestResultValue = 0.0;
            //find the assess item result which produces the highest percentage
            for(SCRAssessmentItemResult result : assessmentItemResults){
                //only calcualte the grade from questions which have been attempted
                if(((result.getCompletionStatus() == CompletionStatus.ATTEMPTED) 
                        || (config.isAggregateNotAttempted() && result.getCompletionStatus() == CompletionStatus.NOT_ATTEMPTED))
                        && result.getAssessmentItemSectionTotalMarks() > 0){
                    double resultValue = result.getMarksAttained() / result.getAssessmentItemSectionTotalMarks();
                    if(resultValue > bestResultValue){
                        bestResultValue = resultValue;
                    }
                }
            }
            return (int)((bestResultValue) * 100);
        }else if(aggregationMethod == AggregationMethod.COMPARE_AND_ACCUMULATE){
            //see enum for documentation on process         
            //create a container for results, grouped by their archetype
            Map<String, List<SCRAssessmentItemResult>> resultsByArchetype = new HashMap();
            //add the 'default' mapping for those with blank archetype
            resultsByArchetype.put("", new LinkedList());
            for(SCRAssessmentItemResult result : assessmentItemResults){
                String resultArchetype = result.getAssessmentItemTaskArchetype();
                //only use results which have been attempted
                if(! (  (result.getCompletionStatus() == CompletionStatus.ATTEMPTED) || 
                        (config.isAggregateNotAttempted() && result.getCompletionStatus() == CompletionStatus.NOT_ATTEMPTED))
                        ) continue;
                //the archetype if blank, automatically add
                if(StringUtils.isEmpty(resultArchetype)){
                    resultsByArchetype.get("").add(result);
                }else{
                    //otherwise, find the archetype in the map
                    if(! resultsByArchetype.containsKey(resultArchetype)){
                        //we need to create a mapping for this archetype and result
                        resultsByArchetype.put(resultArchetype, new LinkedList());                        
                    }
                    resultsByArchetype.get(resultArchetype).add(result);
                }            
            }
            //now we need to aggregate our grades, taking each result from the default mapping
            //and only one result from each other mapping, based on best performance
            double marksAttained = 0;
            double totalMarks = 0;
            //first, iterate the default mapping items
            for(SCRAssessmentItemResult result : resultsByArchetype.get("")){
                marksAttained += result.getMarksAttained();
                totalMarks += result.getAssessmentItemSectionTotalMarks();               
            }
            //next iterate each archetype, compare and select the best performing one
            for(String archetype : resultsByArchetype.keySet()){
                if(archetype.equals("")) continue;
                SCRAssessmentItemResult bestResult = null;
                for(SCRAssessmentItemResult result : resultsByArchetype.get(archetype)){
                    //guard for possible / by 0
                    if(result.getAssessmentItemSectionTotalMarks() == 0) continue;
                    //
                    if(bestResult == null){
                        bestResult = result;
                        continue;
                    }
                    //compare %
                    double resultPercent = result.getMarksAttained() / result.getAssessmentItemSectionTotalMarks();
                    double bestResultPercent = bestResult.getMarksAttained() / bestResult.getAssessmentItemSectionTotalMarks();
                    if(resultPercent > bestResultPercent){
                        bestResult = result;
                    }else if(resultPercent == bestResultPercent){
                        //if its a tie, only choose the current if it has more totoal marks
                        if(result.getAssessmentItemSectionTotalMarks() > bestResult.getAssessmentItemSectionTotalMarks()){
                            bestResult = result;
                        }
                    }
                }
                //add the comtribution using the best result
                marksAttained += bestResult.getMarksAttained();
                totalMarks += bestResult.getAssessmentItemSectionTotalMarks();                   
            }
            //done!
            if(totalMarks == 0) return 0;
            return (int)((marksAttained/totalMarks) * 100);
        }else{
            return -1;
        }
    }
    
    /**
     * 
     * @return The status of the students grade, see comment on the enum for more detail
     */
    public SCRStudentGradeStatus getGradeStatus(){
        if(assessmentItemResults.isEmpty() || allResultsHaveCompletionStatus(CompletionStatus.REMOVED_FROM_ASSESSMENT)){
            return SCRStudentGradeStatus.NOT_ASSESSED;
        }
        
        if(allResultsHaveCompletionStatus(CompletionStatus.NOT_ATTEMPTED)){
            return SCRStudentGradeStatus.NOT_ATTEMPTED;
        }
        
        return SCRStudentGradeStatus.ASSESSED;
    }
    
    /**
     * scans the results looking for the case that each one has the same given CompletionStatus
     * @param status
     * @return 
     */
    public boolean allResultsHaveCompletionStatus(CompletionStatus status){
        for(SCRAssessmentItemResult result : assessmentItemResults){
            if(result.getCompletionStatus() != status){
                return false;
            }
        }
        return true;
    }    
  
    public String getColorValue(){
        SCRStudentGradeStatus status = this.getGradeStatus();
        
        if(status == SCRStudentGradeStatus.NOT_ASSESSED){
            //white
            return "#FFFFFF";
        }
        if(status == SCRStudentGradeStatus.NOT_ATTEMPTED){
            //grey
            return "#D3D3D3";
        }
        int index = 100 - getPercentAttained();
        if(index > 100) index = 100;
        if(index < 0) index = 0;
        return colorGradients[index];
    }

    /**
     * @return the assessmentItemResults
     */
    public List<SCRAssessmentItemResult> getAssessmentItemResults() {
        return assessmentItemResults;
    }

    /**
     * @param assessmentItemResults the assessmentItemResults to set
     */
    public void setAssessmentItemResults(List<SCRAssessmentItemResult> assessmentItemResults) {
        this.assessmentItemResults = assessmentItemResults;
    }

    /**
     * @return the proficiencyStrand
     */
    public SCRProficiencyStrand getProficiencyStrand() {
        return proficiencyStrand;
    }

    /**
     * @param proficiencyStrand the proficiencyStrand to set
     */
    public void setProficiencyStrand(SCRProficiencyStrand proficiencyStrand) {
        this.proficiencyStrand = proficiencyStrand;
    }

    /**
     * @return the curriculumItem
     */
    public SCRCurriculumItem getCurriculumItem() {
        return curriculumItem;
    }

    /**
     * @param curriculumItem the curriculumItem to set
     */
    public void setCurriculumItem(SCRCurriculumItem curriculumItem) {
        this.curriculumItem = curriculumItem;
    }

}
