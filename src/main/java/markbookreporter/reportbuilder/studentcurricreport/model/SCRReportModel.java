/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport.model;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.reportbuilder.ReportModel;

/**
 *
 * @author General
 */
public class SCRReportModel implements ReportModel{
    protected List<SCRProficiencyStrand> proficiencyStrands = new LinkedList();
    protected List<SCRCurriculumItem> curriculumItems = new LinkedList();
    protected List<SCRStudent> students = new LinkedList();
    
    public int getTotalTableColumns(){
        return proficiencyStrands.size() + 1;
    }    

    /**
     * @return the proficiencyStrands
     */
    public List<SCRProficiencyStrand> getProficiencyStrands() {
        return proficiencyStrands;
    }

    /**
     * @param proficiencyStrands the proficiencyStrands to set
     */
    public void setProficiencyStrands(List<SCRProficiencyStrand> proficiencyStrands) {
        this.proficiencyStrands = proficiencyStrands;
    }

    /**
     * @return the curriculumItems
     */
    public List<SCRCurriculumItem> getCurriculumItems() {
        return curriculumItems;
    }

    /**
     * @param curriculumItems the curriculumItems to set
     */
    public void setCurriculumItems(List<SCRCurriculumItem> curriculumItems) {
        this.curriculumItems = curriculumItems;
    }

    /**
     * @return the students
     */
    public List<SCRStudent> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<SCRStudent> students) {
        this.students = students;
    }
}
