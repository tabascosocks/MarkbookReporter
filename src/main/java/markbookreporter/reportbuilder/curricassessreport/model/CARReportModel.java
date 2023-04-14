/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.curricassessreport.model;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.reportbuilder.ReportModel;

/**
 *
 * @author General
 */
public class CARReportModel implements ReportModel{
    
    /*
     * All the curriculum items
     */
    protected List<CARCurriculumItem> curriculumItems = new LinkedList();
    /*
     * all the profic strands
     */
    protected List<CARProficiencyStrand> proficiencyStrands = new LinkedList();
    /*
     * the assessreportsections with thier respective subset of the curric. items
     * and proficiency strands.
     */
    protected List<CARAssessmentItemSection> assessmentItemSections = new LinkedList();

    public int getTotalTableColumns(){
        return proficiencyStrands.size() + 1;
    }
    public CARCurriculumItem getCARCurriculumItem(String name){        
        for(CARCurriculumItem c : curriculumItems){
            if(c.getName().equals(name)) return c;
        }
        return null;
    }
    
    public CARProficiencyStrand getCARProficiencyStrand(String name){
        for(CARProficiencyStrand c : proficiencyStrands){
            if(c.getName().equals(name)) return c;
        }
        return null;
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
    public void setProficiencyStrands(List<CARProficiencyStrand> proficiencyStrands) {
        this.proficiencyStrands = proficiencyStrands;
    }

    /**
     * @return the assessmentItemSections
     */
    public List<CARAssessmentItemSection> getAssessmentItemSections() {
        return assessmentItemSections;
    }

    /**
     * @param assessmentItemSections the assessmentItemSections to set
     */
    public void setAssessmentItemSections(List<CARAssessmentItemSection> assessmentItemSections) {
        this.assessmentItemSections = assessmentItemSections;
    }
    
}
