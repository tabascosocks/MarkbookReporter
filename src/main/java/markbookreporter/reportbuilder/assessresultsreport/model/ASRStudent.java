/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.assessresultsreport.model;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.model.CompletionStatus;
import markbookreporter.model.Student;

/**
 *
 * @author School
 */
public class ASRStudent {
    private String firstName;
    private String lastName;
    private List<ASRAssessmentItemSectionResult> results = new LinkedList();

    public ASRStudent(Student student) {
        firstName = student.getFirstName();
        lastName = student.getLastName();
    }
    
    public boolean submitted(ASRAssessmentItem item){
        for(ASRAssessmentItemSectionResult result : results){
            if(result.getAsrAssessmentItemSection().getAssessmentItem() == item){
                if(result.getCompletionStatus() == CompletionStatus.ATTEMPTED)
                    return true;
            }
        }
        return false;
    }
    
    public ASRAssessmentItemSectionResult getResultForSection(ASRAssessmentItemSection section){
        for(ASRAssessmentItemSectionResult result : results){
            if(result.getAsrAssessmentItemSection() == section)
                return result;
        }
        return null;
    }
    
    public int getPercentAttainedForAssessmentItem(ASRAssessmentItem item){
        double totalMarksAttained = 0;
        double totalMarks = 0;
        
        for(ASRAssessmentItemSectionResult result : results){
            if(result.getCompletionStatus() == CompletionStatus.REMOVED_FROM_ASSESSMENT)
                continue;            
            if(result.getAsrAssessmentItemSection().getAssessmentItem() == item){
                totalMarksAttained += result.getMarksAttained();
                totalMarks += result.getAsrAssessmentItemSection().getTotalMarks();                                    
            }            
        }    
        if(totalMarks == 0) return 0;
        return (int)((totalMarksAttained / totalMarks) * 100);
    }
    //public get

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
     * @return the results
     */
    public List<ASRAssessmentItemSectionResult> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(List<ASRAssessmentItemSectionResult> results) {
        this.results = results;
    }

}
