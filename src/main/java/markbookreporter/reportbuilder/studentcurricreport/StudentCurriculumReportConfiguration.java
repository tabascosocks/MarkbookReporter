/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.enums.AggregationMethod;
import markbookreporter.model.AssessmentItem;
import markbookreporter.model.Student;
import markbookreporter.reportbuilder.ReportConfiguration;

/**
 *
 * @author School
 */
public class StudentCurriculumReportConfiguration extends ReportConfiguration{
    /**
     * if true, only generate a report for the given students
     */
    protected boolean selectedStudentsOnly = false;
    protected List<Student> selectedStudents = new LinkedList();
    /**
     * if true, only generate a report for the assessment items
     */
    protected boolean selectedAssessItemsOnly = false;
    protected List<AssessmentItem> selectedAssessItems= new LinkedList();
    /**
     * Wether the results from a child curriculum element
     * (ie an elaboration item) should appear in the 
     * parent curriculum item as well
     */
    protected boolean pushUpChildCurriculumResults;
    /**
     * Whether or not all the curriculum items should be listed in this report, even if
     * no assessment items have linked to it
     */
    protected boolean useFullCurriculumItems;
    /**
     * Whether or not all the proficiency strands should be listed in this report, even if
     * no assessment items have linked to it
     */
    protected boolean useFullProficiencyStrands;

    /**
     * #Defines the way that results are combined to give a percentage, option are
     * A = Accumulate, to find a global percentage of marks attained against all attempted
     * T = Take highest, use the highest scoring result
     */
    protected AggregationMethod aggregationMethod;
    
    /**
     * whether or not 'Not Attempted' item sections should be included in the aggregation process
     */
    protected boolean aggregateNotAttempted;
    /**
     * Wether the results from a child curriculum element
     * (ie an elaboration item) should appear in the
     * parent curriculum item as well
     * @return the pushUpChildCurriculumResults
     */
    public boolean isPushUpChildCurriculumResults() {
        return pushUpChildCurriculumResults;
    }

    /**
     * Wether the results from a child curriculum element
     * (ie an elaboration item) should appear in the
     * parent curriculum item as well
     * @param pushUpChildCurriculumResults the pushUpChildCurriculumResults to set
     */
    public void setPushUpChildCurriculumResults(boolean pushUpChildCurriculumResults) {
        this.pushUpChildCurriculumResults = pushUpChildCurriculumResults;
    }

    /**
     * Whether or not all the curriculum items should be listed in this report, even if
     * no assessment items have linked to it
     * @return the useFullCurriculumItems
     */
    public boolean isUseFullCurriculumItems() {
        return useFullCurriculumItems;
    }

    /**
     * Whether or not all the curriculum items should be listed in this report, even if
     * no assessment items have linked to it
     * @param useFullCurriculumItems the useFullCurriculumItems to set
     */
    public void setUseFullCurriculumItems(boolean useFullCurriculumItems) {
        this.useFullCurriculumItems = useFullCurriculumItems;
    }

    /**
     * Whether or not all the proficiency strands should be listed in this report, even if
     * no assessment items have linked to it
     * @return the useFullProficiencyStrands
     */
    public boolean isUseFullProficiencyStrands() {
        return useFullProficiencyStrands;
    }

    /**
     * Whether or not all the proficiency strands should be listed in this report, even if
     * no assessment items have linked to it
     * @param useFullProficiencyStrands the useFullProficiencyStrands to set
     */
    public void setUseFullProficiencyStrands(boolean useFullProficiencyStrands) {
        this.useFullProficiencyStrands = useFullProficiencyStrands;
    }   

    /**
     * #Defines the way that results are combined to give a percentage, option are
     * A = Accumulate, to find a global percentage of marks attained against all attempted
     * T = Take highest, use the highest scoring result
     * @return the aggregationMethod
     */
    public AggregationMethod getAggregationMethod() {
        return aggregationMethod;
    }

    /**
     * #Defines the way that results are combined to give a percentage, option are
     * A = Accumulate, to find a global percentage of marks attained against all attempted
     * T = Take highest, use the highest scoring result
     * @param aggregationMethod the aggregationMethod to set
     */
    public void setAggregationMethod(AggregationMethod aggregationMethod) {
        this.aggregationMethod = aggregationMethod;
    }

    /**
     * if true, only generate a report for the given students
     * @return the selectedStudentsOnly
     */
    public boolean isSelectedStudentsOnly() {
        return selectedStudentsOnly;
    }

    /**
     * if true, only generate a report for the given students
     * @param selectedStudentsOnly the selectedStudentsOnly to set
     */
    public void setSelectedStudentsOnly(boolean selectedStudentsOnly) {
        this.selectedStudentsOnly = selectedStudentsOnly;
    }

    /**
     * @return the selectedStudents
     */
    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    /**
     * @param selectedStudents the selectedStudents to set
     */
    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    /**
     * if true, only generate a report for the assessment items
     * @return the selectedAssessItemsOnly
     */
    public boolean isSelectedAssessItemsOnly() {
        return selectedAssessItemsOnly;
    }

    /**
     * if true, only generate a report for the assessment items
     * @param selectedAssessItemsOnly the selectedAssessItemsOnly to set
     */
    public void setSelectedAssessItemsOnly(boolean selectedAssessItemsOnly) {
        this.selectedAssessItemsOnly = selectedAssessItemsOnly;
    }

    /**
     * @return the selectedAssessItems
     */
    public List<AssessmentItem> getSelectedAssessItems() {
        return selectedAssessItems;
    }

    /**
     * @param selectedAssessItems the selectedAssessItems to set
     */
    public void setSelectedAssessItems(List<AssessmentItem> selectedAssessItems) {
        this.selectedAssessItems = selectedAssessItems;
    }

    /**
     * whether or not 'Not Attempted' item sections should be included in the aggregation process
     * @return the aggregateNotAttempted
     */
    public boolean isAggregateNotAttempted() {
        return aggregateNotAttempted;
    }

    /**
     * whether or not 'Not Attempted' item sections should be included in the aggregation process
     * @param aggregateNotAttempted the aggregateNotAttempted to set
     */
    public void setAggregateNotAttempted(boolean aggregateNotAttempted) {
        this.aggregateNotAttempted = aggregateNotAttempted;
    }
}
