/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author General
 */
public class Student {
    
    String firstName;
    String lastName;
    
    List<AssessmentItemSectionResult> assessmentItemSectionResults = new LinkedList();

    @Override
    public String toString(){
        return ((firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "")).trim();
    }
    
    /*
    public int getPercentAttainedFor(CurriculumItem currItem, ProficiencyStrand profStrand, AggregationMethod aggmethod){
        //Search assessment item results for any that match the given curriculum item and proficiency strand
        List<AssessmentItemSectionResult> matchingResults = new LinkedList();
        for(AssessmentItemSectionResult currentResult : assessmentItemSectionResults){
            if(currentResult.getAssessmentItemSection().getCurriculumItems().contains(currItem) &&
                    currentResult.getAssessmentItemSection().getProficiencyStrands().contains(profStrand)){
                matchingResults.add(currentResult);
            }
        }
    }
    */
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
     * @return the assessmentItemSectionResults
     */
    public List<AssessmentItemSectionResult> getAssessmentItemSectionResults() {
        return assessmentItemSectionResults;
    }

    /**
     * @param assessmentItemSectionResults the assessmentItemSectionResults to set
     */
    public void setAssessmentItemSectionResults(List<AssessmentItemSectionResult> assessmentItemSectionResults) {
        this.assessmentItemSectionResults = assessmentItemSectionResults;
    }
    
    
}

class Attainment{
    public CompletionStatus completionStatus;
    public int percentAttained = -1;
}
