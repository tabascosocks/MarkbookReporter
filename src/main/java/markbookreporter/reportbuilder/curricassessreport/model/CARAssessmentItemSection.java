/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.curricassessreport.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author General
 */
public class CARAssessmentItemSection {
    
    protected String name;
    protected Double totalMarks;
    protected String textBookRef;
    
    protected CARAssessmentItem assessmentItem;
    
    protected List<CARCurriculumItem> curriculumItems = new LinkedList();
    protected List<CARProficiencyStrand> proficiencyStrands = new LinkedList();

    public boolean hasTextBookRef(){
        return textBookRef != null && textBookRef.trim().length() > 0;
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
     * @return the assessmentItem
     */
    public CARAssessmentItem getAssessmentItem() {
        return assessmentItem;
    }

    /**
     * @param assessmentItem the assessmentItem to set
     */
    public void setAssessmentItem(CARAssessmentItem assessmentItem) {
        this.assessmentItem = assessmentItem;
    }

    /**
     * @return the curriculumItems
     */
    public List<CARCurriculumItem> getCurriculumItems() {
        return curriculumItems;
    }

    /**
     * @param curriculumItems the curriculumItems to set
     */
    public void setCurriculumItems(List<CARCurriculumItem> curriculumItems) {
        this.curriculumItems = curriculumItems;
    }

    /**
     * @return the proficiencyStrands
     */
    public List<CARProficiencyStrand> getProficiencyStrands() {
        return proficiencyStrands;
    }

    /**
     * @param proficiencyStrands the proficiencyStrands to set
     */
    public void setProficiencyStrands(List<CARProficiencyStrand> proficinecyStrands) {
        this.proficiencyStrands = proficinecyStrands;
    }

    /**
     * @return the textBookRef
     */
    public String getTextBookRef() {
        return textBookRef;
    }

    /**
     * @param textBookRef the textBookRef to set
     */
    public void setTextBookRef(String textBookRef) {
        this.textBookRef = textBookRef;
    }
    
}
