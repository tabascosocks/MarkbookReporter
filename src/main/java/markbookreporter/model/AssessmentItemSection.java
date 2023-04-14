/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model;

import markbookreporter.model.curriculum.ProficiencyStrand;
import markbookreporter.model.curriculum.CurriculumItem;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author General
 */
public class AssessmentItemSection {
    
    protected String name;
    protected Double totalMarks;
    protected String textbookReference;
    protected AssessmentItem assessmentItem;
    /**
     * The 'category' of question used for mark aggregation grouping
     */
    protected String taskArchetype;
    
    protected List<AssessmentItemSectionResult> assessmentItemSectionResults = new LinkedList();
    protected List<CurriculumItem> curriculumItems = new LinkedList();
    protected List<ProficiencyStrand> proficiencyStrands = new LinkedList();
    

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
     * @return the textbookReference
     */
    public String getTextbookReference() {
        return textbookReference;
    }

    /**
     * @param textbookReference the textbookReference to set
     */
    public void setTextbookReference(String textbookReference) {
        this.textbookReference = textbookReference;
    }

    /**
     * @return the assessmentItem
     */
    public AssessmentItem getAssessmentItem() {
        return assessmentItem;
    }

    /**
     * @param assessmentItem the assessmentItem to set
     */
    public void setAssessmentItem(AssessmentItem assessmentItem) {
        this.assessmentItem = assessmentItem;
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

    /**
     * @return the curriculumItems
     */
    public List<CurriculumItem> getCurriculumItems() {
        return curriculumItems;
    }

    /**
     * @param curriculumItems the curriculumItems to set
     */
    public void setCurriculumItems(List<CurriculumItem> curriculumItems) {
        this.curriculumItems = curriculumItems;
    }

    /**
     * @return the proficiencyStrands
     */
    public List<ProficiencyStrand> getProficiencyStrands() {
        return proficiencyStrands;
    }

    /**
     * @param proficiencyStrands the proficiencyStrands to set
     */
    public void setProficiencyStrands(List<ProficiencyStrand> proficiencyStrands) {
        this.proficiencyStrands = proficiencyStrands;
    }

    /**
     * The 'category' of question used for mark aggregation grouping
     * @return the taskArchetype
     */
    public String getTaskArchetype() {
        return taskArchetype;
    }

    /**
     * The 'category' of question used for mark aggregation grouping
     * @param taskArchetype the taskArchetype to set
     */
    public void setTaskArchetype(String taskArchetype) {
        this.taskArchetype = taskArchetype;
    }
    
}
