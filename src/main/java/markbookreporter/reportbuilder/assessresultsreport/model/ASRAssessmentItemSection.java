/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.assessresultsreport.model;

import markbookreporter.model.AssessmentItemSection;
import markbookreporter.model.curriculum.CurriculumItem;
import markbookreporter.model.curriculum.ProficiencyStrand;

/**
 *
 * @author School
 */
public class ASRAssessmentItemSection {
    private String name;
    private String curriculumItemCode;
    private String profStrandCode;
    private double totalMarks;
    private ASRAssessmentItem assessmentItem;

    public ASRAssessmentItemSection(AssessmentItemSection section){
        String curriculumItemStr = "";
        for(CurriculumItem currItem : section.getCurriculumItems()){
            curriculumItemStr += currItem.getCode() + " ";
        }
        setCurriculumItemCode(curriculumItemStr);
        String profStrandStr = "";
        for(ProficiencyStrand profStrand : section.getProficiencyStrands()){
            profStrandStr += profStrand.getCode() + " ";
        }
        setProfStrandCode(profStrandStr);
        setName(section.getName());
        setTotalMarks(section.getTotalMarks());        
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
     * @return the curriculumItemCode
     */
    public String getCurriculumItemCode() {
        return curriculumItemCode;
    }

    /**
     * @param curriculumItemCode the curriculumItemCode to set
     */
    public void setCurriculumItemCode(String curriculumItemCode) {
        this.curriculumItemCode = curriculumItemCode;
    }

    /**
     * @return the profStrandCode
     */
    public String getProfStrandCode() {
        return profStrandCode;
    }

    /**
     * @param profStrandCode the profStrandCode to set
     */
    public void setProfStrandCode(String profStrandCode) {
        this.profStrandCode = profStrandCode;
    }

    /**
     * @return the totalMarks
     */
    public double getTotalMarks() {
        return totalMarks;
    }

    /**
     * @param totalMarks the totalMarks to set
     */
    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    /**
     * @return the assessmentItem
     */
    public ASRAssessmentItem getAssessmentItem() {
        return assessmentItem;
    }

    /**
     * @param assessmentItem the assessmentItem to set
     */
    public void setAssessmentItem(ASRAssessmentItem assessmentItem) {
        this.assessmentItem = assessmentItem;
    }
}
