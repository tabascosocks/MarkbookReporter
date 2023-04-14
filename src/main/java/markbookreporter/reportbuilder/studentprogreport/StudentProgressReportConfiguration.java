/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentprogreport;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.model.Student;
import markbookreporter.reportbuilder.ReportConfiguration;

/**
 *
 * @author School
 */
public class StudentProgressReportConfiguration extends ReportConfiguration{
    /**
     * if true, only generate a report for the given students
     */
    protected boolean selectedStudentsOnly = false;
    protected List<Student> selectedStudents = new LinkedList();    

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
}
