/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentassessmentreport.model;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.reportbuilder.ReportModel;

/**
 *
 * @author General
 */
public class SARReportModel implements ReportModel{
    protected List<SARStudent> students = new LinkedList();

    /**
     * @return the students
     */
    public List<SARStudent> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<SARStudent> students) {
        this.students = students;
    }
}
