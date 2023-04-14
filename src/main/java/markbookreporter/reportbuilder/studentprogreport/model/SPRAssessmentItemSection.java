/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentprogreport.model;

import markbookreporter.reportbuilder.studentassessmentreport.model.*;
import markbookreporter.model.CompletionStatus;

/**
 *
 * @author General
 */
public class SPRAssessmentItemSection {
    
    private String name;
    private Double markAttained;
    private Double totalMarks;
    private CompletionStatus completionStatus;
    private boolean isNumeracyAssessment = false;

    public String toString(){
        return getName() + ": " + getMarkAttained() + "/" + getTotalMarks();        
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
     * @return the markAttained
     */
    public Double getMarkAttained() {
        return markAttained;
    }

    /**
     * @param markAttained the markAttained to set
     */
    public void setMarkAttained(Double markAttained) {
        this.markAttained = markAttained;
    }

    /**
     * @return the totalMarks
     */
    public Double getTotalMarks() {
        return totalMarks;
    }

    /**
     * @param totalMarks the totalMarks to set
     */
    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
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
     * @return the isNumeracyAssessment
     */
    public boolean isIsNumeracyAssessment() {
        return isNumeracyAssessment;
    }

    /**
     * @param isNumeracyAssessment the isNumeracyAssessment to set
     */
    public void setIsNumeracyAssessment(boolean isNumeracyAssessment) {
        this.isNumeracyAssessment = isNumeracyAssessment;
    }
}
