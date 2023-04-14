/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentprogreport.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import markbookreporter.reportbuilder.ReportModel;

/**
 *
 * @author General
 */
public class SPRReportModel implements ReportModel{
    protected List<SPRStudent> students = new LinkedList();

    public String getReportDate(){
        SimpleDateFormat sf = new SimpleDateFormat("EEE MMM d, yyyy");
        return sf.format(Calendar.getInstance().getTime());
    }
    /**
     * @return the students
     */
    public List<SPRStudent> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<SPRStudent> students) {
        this.students = students;
    }
}
