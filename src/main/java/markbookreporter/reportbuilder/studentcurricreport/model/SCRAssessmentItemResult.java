/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport.model;

import markbookreporter.model.CompletionStatus;

/**
 *
 * @author General
 */
public class SCRAssessmentItemResult {
    private Double marksAttained;
    private String assessmentItemSectionName;
    private Double assessmentItemSectionTotalMarks;
    private String assessmentItemName;
    protected String assessmentItemTaskArchetype;
    private CompletionStatus completionStatus;

    @Override
    public String toString(){
        return getMarksAttained() + "/" + getAssessmentItemSectionTotalMarks() + 
                " in " + getAssessmentItemSectionName() +
                " of " + getAssessmentItemName();
    }
    /**
     * @return the marksAttained
     */
    public Double getMarksAttained() {
        return marksAttained;
    }

    /**
     * @param marksAttained the marksAttained to set
     */
    public void setMarksAttained(Double marksAttained) {
        this.marksAttained = marksAttained;
    }

    /**
     * @return the assessmentItemSectionName
     */
    public String getAssessmentItemSectionName() {
        return assessmentItemSectionName;
    }

    /**
     * @param assessmentItemSectionName the assessmentItemSectionName to set
     */
    public void setAssessmentItemSectionName(String assessmentItemSectionName) {
        this.assessmentItemSectionName = assessmentItemSectionName;
    }

    /**
     * @return the assessmentItemSectionTotalMarks
     */
    public Double getAssessmentItemSectionTotalMarks() {
        return assessmentItemSectionTotalMarks;
    }

    /**
     * @param assessmentItemSectionTotalMarks the assessmentItemSectionTotalMarks to set
     */
    public void setAssessmentItemSectionTotalMarks(Double assessmentItemSectionTotalMarks) {
        this.assessmentItemSectionTotalMarks = assessmentItemSectionTotalMarks;
    }

    /**
     * @return the assessmentItemName
     */
    public String getAssessmentItemName() {
        return assessmentItemName;
    }

    /**
     * @param assessmentItemName the assessmentItemName to set
     */
    public void setAssessmentItemName(String assessmentItemName) {
        this.assessmentItemName = assessmentItemName;
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
     * @return the assessmentItemTaskArchetype
     */
    public String getAssessmentItemTaskArchetype() {
        return assessmentItemTaskArchetype;
    }

    /**
     * @param assessmentItemTaskArchetype the assessmentItemTaskArchetype to set
     */
    public void setAssessmentItemTaskArchetype(String assessmentItemTaskArchetype) {
        this.assessmentItemTaskArchetype = assessmentItemTaskArchetype;
    }
}
