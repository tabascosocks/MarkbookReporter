/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentprogreport;

import markbookreporter.model.AssessmentItem;
import markbookreporter.model.AssessmentItemSection;
import markbookreporter.model.AssessmentItemSectionResult;
import markbookreporter.model.CompletionStatus;
import markbookreporter.model.MarkBook;
import markbookreporter.model.Student;
import markbookreporter.model.curriculum.Curriculum;
import markbookreporter.model.curriculum.CurriculumItem;
import markbookreporter.reportbuilder.ReportBuilder;
import markbookreporter.reportbuilder.ReportConfiguration;
import markbookreporter.reportbuilder.ReportModel;
import markbookreporter.reportbuilder.studentprogreport.model.SPRReportModel;
import markbookreporter.reportbuilder.studentprogreport.model.SPRAssessmentItem;
import markbookreporter.reportbuilder.studentprogreport.model.SPRAssessmentItemSection;
import markbookreporter.reportbuilder.studentprogreport.model.SPRStudent;



/**
 *
 * @author General
 */
public class StudentProgressReportBuilder extends ReportBuilder{    

    public static StudentProgressReportConfiguration sprConfig;
    
    @Override
    protected ReportModel buildReportModel(MarkBook markBook, ReportConfiguration config) {
        //assign class-global configuration
        sprConfig = (StudentProgressReportConfiguration)config;
        //build the report model
        SPRReportModel reportModel = new SPRReportModel();
        //for each studet, create a report object
        for(Student student : markBook.getStudents()){
            //check config to see if we should exclude this student
            if(sprConfig.isSelectedStudentsOnly()){
                //if its not in the list, skip
                if(! sprConfig.getSelectedStudents().contains(student)){
                    continue;
                }
            }
            SPRStudent sprStudent = new SPRStudent();
            sprStudent.setFirstName(student.getFirstName());            
            sprStudent.setLastName(student.getLastName());            
            
            //for each assessment item , create a report model for teh student, 
            //then locate sections and entries which relate (top-down data gathering)
            for(AssessmentItem assessItem : markBook.getAssessmentItems()){
                SPRAssessmentItem sprAssessItem = new SPRAssessmentItem();
                sprAssessItem.setName(assessItem.getName());
                sprAssessItem.setCategoryTitle(assessItem.getCategoryTitle());
                //locate the sections which have a result for this student and add
                //a SARAssessmentItemSection
                for(AssessmentItemSection assessItemSection : assessItem.getAssessmentItemSections()){
                    for(AssessmentItemSectionResult assessItemSecResult : assessItemSection.getAssessmentItemSectionResults()){
                        if(assessItemSecResult.getStudent() == student){
                            //we have found a result that relates to this student, add it to the report model
                            SPRAssessmentItemSection sprAssessItemSec = new SPRAssessmentItemSection();
                            sprAssessItemSec.setCompletionStatus(assessItemSecResult.getCompletionStatus());
                            sprAssessItemSec.setMarkAttained(assessItemSecResult.getMarksAttained());
                            sprAssessItemSec.setName(assessItemSection.getName());
                            sprAssessItemSec.setTotalMarks(assessItemSection.getTotalMarks());
                            //find out if it is a numeracy section
                            for(CurriculumItem curriculumItem : assessItemSection.getCurriculumItems()){
                                if(curriculumItem.getCode().equals("NUM")){
                                    sprAssessItemSec.setIsNumeracyAssessment(true);
                                }
                            }
                            //link to sar assessment item
                            sprAssessItem.getAssessmentItemSections().add(sprAssessItemSec);                            
                        }
                    }
                }
                //if there were no sections sections found for this student (ie they werent in teh markbook for this item)
                //or if this item was removed from teh students assessment, then dont add an assessment item for this student
                if(sprAssessItem.getAssessmentItemSections().isEmpty()
                        || sprAssessItem.allSectionsHaveCompletionStatus(CompletionStatus.REMOVED_FROM_ASSESSMENT))
                    continue;
                //set the completion status for this SARAssessmentItem by checking for any
                //'across the board' completion statuses on all result items
                if(sprAssessItem.allSectionsHaveCompletionStatus(CompletionStatus.NOT_ATTEMPTED)){
                    sprAssessItem.setCompletionStatus(CompletionStatus.NOT_ATTEMPTED);                
                }else{
                    //ptherwise assume since some sections have been attempted that the item has been attempted
                    sprAssessItem.setCompletionStatus(CompletionStatus.ATTEMPTED);
                }                            
                //link assess item to student
                sprStudent.getAssessmentItems().add(sprAssessItem);
            }
            //link student to reportModel
            reportModel.getStudents().add(sprStudent);         
        }
        return reportModel;
    }

    @Override
    protected String getVelocityTemplateName() {
       return "StudentProgressReport.vm";
    }
    
}
