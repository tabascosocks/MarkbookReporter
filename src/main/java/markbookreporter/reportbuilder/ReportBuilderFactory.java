/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder;

import markbookreporter.reportbuilder.assessresultsreport.AssessmentResultsReportBuilder;
import markbookreporter.reportbuilder.curricassessreport.CurriculumAssessmentReportBuilder;
import markbookreporter.reportbuilder.studentassessmentreport.StudentAssessmentReportBuilder;
import markbookreporter.reportbuilder.studentcurricreport.StudentCurriculumReportBuilder;
import markbookreporter.reportbuilder.studentprogreport.StudentProgressReportBuilder;

/**
 *
 * @author General
 */
public class ReportBuilderFactory {

    public static ReportBuilder createReportBuilder(ReportType reportType) {
        switch (reportType) {
            case CURR_ASSESS_REPORT:
                return new CurriculumAssessmentReportBuilder();
            case STUD_CURR_REPORT:
                return new StudentCurriculumReportBuilder();
            case STUD_ASSESS_REPORT:
                return new StudentAssessmentReportBuilder();
            case STUD_PROGRESS_REPORT:
                return new StudentProgressReportBuilder();
            case ASSESS_RESULT_REPORT:
                return new AssessmentResultsReportBuilder();              
            default:
                return null;
        }
    }

}
