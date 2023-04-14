/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author General
 */
public class SCRStudent {
    protected String name;
    protected List<SCRStudentGrade> grades = new LinkedList();
    protected List<String> assessmentItemNames = new LinkedList();

    public SCRStudentGrade getGradeForItemAndProficiency(SCRCurriculumItem currItem, SCRProficiencyStrand profStrand){
        for(SCRStudentGrade grade : grades){
            if(grade.getCurriculumItem() == currItem && grade.getProficiencyStrand() == profStrand){
                return grade;
            }
        }
        return null;
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
     * @return the grades
     */
    public List<SCRStudentGrade> getGrades() {
        return grades;
    }

    /**
     * @param grades the grades to set
     */
    public void setGrades(List<SCRStudentGrade> grades) {
        this.grades = grades;
    }

    /**
     * @return the assessmentItemNames
     */
    public List<String> getAssessmentItemNames() {
        return assessmentItemNames;
    }

    /**
     * @param assessmentItemNames the assessmentItemNames to set
     */
    public void setAssessmentItemNames(List<String> assessmentItemNames) {
        this.assessmentItemNames = assessmentItemNames;
    }
}
