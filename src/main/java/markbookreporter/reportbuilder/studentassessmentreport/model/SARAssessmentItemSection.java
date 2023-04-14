/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentassessmentreport.model;

import markbookreporter.model.CompletionStatus;

/**
 *
 * @author General
 */
public class SARAssessmentItemSection {
    
    private String name;
    private Double markAttained;
    private Double totalMarks;
    private CompletionStatus completionStatus;

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
}
