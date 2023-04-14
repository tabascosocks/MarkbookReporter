/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model;

/**
 *
 * @author General
 */
public class AssessmentItemSectionResult {
    
    private Double marksAttained;
    private AssessmentItemSection assessmentItemSection;
    private Student student;
    private CompletionStatus completionStatus;

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
     * @return the assessmentItemSection
     */
    public AssessmentItemSection getAssessmentItemSection() {
        return assessmentItemSection;
    }

    /**
     * @param assessmentItemSection the assessmentItemSection to set
     */
    public void setAssessmentItemSection(AssessmentItemSection assessmentItemSection) {
        this.assessmentItemSection = assessmentItemSection;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
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
