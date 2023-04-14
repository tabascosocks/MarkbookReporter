/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentassessmentreport;

import markbookreporter.model.AssessmentItem;
import markbookreporter.model.AssessmentItemSection;
import markbookreporter.model.AssessmentItemSectionResult;
import markbookreporter.model.CompletionStatus;
import markbookreporter.model.MarkBook;
import markbookreporter.model.Student;
import markbookreporter.reportbuilder.ReportBuilder;
import markbookreporter.reportbuilder.ReportConfiguration;
import markbookreporter.reportbuilder.studentassessmentreport.model.SARReportModel;
import markbookreporter.reportbuilder.studentassessmentreport.model.SARAssessmentItem;
import markbookreporter.reportbuilder.studentassessmentreport.model.SARAssessmentItemSection;
import markbookreporter.reportbuilder.studentassessmentreport.model.SARStudent;

/**
 *
 * @author General
 */
public class StudentAssessmentReportBuilder extends ReportBuilder{
    
    StudentAssessmentReportConfiguration sarConfig;
    
    @Override
    protected SARReportModel buildReportModel(MarkBook markBook, ReportConfiguration config) {
        //assign class-global configuration
        sarConfig = (StudentAssessmentReportConfiguration)config;
        //build the report model
        SARReportModel reportModel = new SARReportModel();
        //for each studet, create a report object
        for(Student student : markBook.getStudents()){
            //check config to see if we should exclude this student
            if(sarConfig.isSelectedStudentsOnly()){
                //if its not in the list, skip
                if(! sarConfig.getSelectedStudents().contains(student)){
                    continue;
                }
            }
            SARStudent sarStudent = new SARStudent();
            sarStudent.setName(student.getFirstName() + " " + student.getLastName());            
            //for each assessment item , create a report model for teh student, 
            //then locate sections and entries which relate (top-down data gathering)
            for(AssessmentItem assessItem : markBook.getAssessmentItems()){
                SARAssessmentItem sarAssessItem = new SARAssessmentItem();
                sarAssessItem.setName(assessItem.getName());
                //locate the sections which have a result for this student and add
                //a SARAssessmentItemSection
                for(AssessmentItemSection assessItemSection : assessItem.getAssessmentItemSections()){
                    for(AssessmentItemSectionResult assessItemSecResult : assessItemSection.getAssessmentItemSectionResults()){
                        if(assessItemSecResult.getStudent() == student){
                            //we have found a result that relates to this student, add it to the report model
                            SARAssessmentItemSection sarAssessItemSec = new SARAssessmentItemSection();
                            sarAssessItemSec.setCompletionStatus(assessItemSecResult.getCompletionStatus());
                            sarAssessItemSec.setMarkAttained(assessItemSecResult.getMarksAttained());
                            sarAssessItemSec.setName(assessItemSection.getName());
                            sarAssessItemSec.setTotalMarks(assessItemSection.getTotalMarks());
                            //link to sar assessment item
                            sarAssessItem.getAssessmentItemSections().add(sarAssessItemSec);                            
                        }
                    }
                }
                //if there were no sections sections found for this student (ie they werent in teh markbook for this item)
                //, or if this item was removed from teh students assessment, then dont add an assessment item for this student
                if(sarAssessItem.getAssessmentItemSections().isEmpty()
                        || sarAssessItem.allSectionsHaveCompletionStatus(CompletionStatus.REMOVED_FROM_ASSESSMENT))
                    continue;                
                //set the completion status for this SARAssessmentItem by checking for any
                //'across the board' completion statuses on all result items
                if(sarAssessItem.allSectionsHaveCompletionStatus(CompletionStatus.NOT_ATTEMPTED)){
                    sarAssessItem.setCompletionStatus(CompletionStatus.NOT_ATTEMPTED);                
                }else{
                    //ptherwise assume since some sections have been attempted that the item has been attempted
                    sarAssessItem.setCompletionStatus(CompletionStatus.ATTEMPTED);
                }
            
                
                //link assess item to student
                sarStudent.getAssessmentItems().add(sarAssessItem);
            }
            //link student to reportModel
            reportModel.getStudents().add(sarStudent);         
        }
        return reportModel;
    }
    
    @Override
    protected String getVelocityTemplateName() {
       return "StudentAssessmentReport.vm";
    }
}
