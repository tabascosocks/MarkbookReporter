/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.assessresultsreport;

import java.util.logging.Level;
import markbookreporter.model.AssessmentItem;
import markbookreporter.model.AssessmentItemSection;
import markbookreporter.model.AssessmentItemSectionResult;
import markbookreporter.model.MarkBook;
import markbookreporter.model.curriculum.Curriculum;
import markbookreporter.reportbuilder.ReportBuilder;
import markbookreporter.reportbuilder.ReportConfiguration;
import markbookreporter.reportbuilder.ReportModel;
import markbookreporter.reportbuilder.assessresultsreport.model.ASRAssessmentItem;
import markbookreporter.reportbuilder.assessresultsreport.model.ASRAssessmentItemSection;
import markbookreporter.reportbuilder.assessresultsreport.model.ASRAssessmentItemSectionResult;
import markbookreporter.reportbuilder.assessresultsreport.model.ASRReportModel;
import markbookreporter.reportbuilder.assessresultsreport.model.ASRStudent;

/**
 *
 * @author School
 */
public class AssessmentResultsReportBuilder extends ReportBuilder {

    public static AssessmentResultsReportConfiguration asrConfig;
    
    @Override
    protected ReportModel buildReportModel(MarkBook markBook, ReportConfiguration config) {
        //assign class-global configuration
        asrConfig = (AssessmentResultsReportConfiguration)config;
        //build the report model
        ASRReportModel reportModel = new ASRReportModel();
        
        for(AssessmentItem assessmentItem : markBook.getAssessmentItems()){
            //check config to see if we should exclude this item
            if(asrConfig.isSelectedAssessmentItemsOnly()){
                //if its not in the list, skip
                if(! asrConfig.getSelectedAssessmentItems().contains(assessmentItem)){
                    continue;
                }
            }
            //build top level model component
            ASRAssessmentItem asrAssessmentItem = new ASRAssessmentItem();
            asrAssessmentItem.setName(assessmentItem.getName());
                        
            for(AssessmentItemSection section : assessmentItem.getAssessmentItemSections()){
                ASRAssessmentItemSection asrSection = new ASRAssessmentItemSection(section);
                //link
                asrAssessmentItem.getAsrAssessmentItemSections().add(asrSection);
                asrSection.setAssessmentItem(asrAssessmentItem);
                //
                for(AssessmentItemSectionResult result : section.getAssessmentItemSectionResults()){
                    ASRStudent asrStudent = asrAssessmentItem.getOrCreateStudent(result.getStudent());
                    ASRAssessmentItemSectionResult asrResult = new ASRAssessmentItemSectionResult(result);
                    asrResult.setAsrAssessmentItemSection(asrSection);                    
                    asrStudent.getResults().add(asrResult);
                }
            }
            reportModel.getAsrAssessmentItems().add(asrAssessmentItem);
            //sort the students by the grade they got for the assessment item
            asrAssessmentItem.sortStudentsByGrade();            
        }

        /*
        for(ASRAssessmentItem item : reportModel.getAsrAssessmentItems()){
            for(ASRStudent student : item.getAsrStudents()){
                for(ASRAssessmentItemSection section : item.getAsrAssessmentItemSections()){
                    if(student.getResultForSection(section) == null){
                        logger.log(Level.FINE, student.getFirstName() + "->" + section.getName());
                    }
                }
            }
        }
        */
        return reportModel;
    }   
    
    @Override
    protected String getVelocityTemplateName() {
       return "AssessmentResultsReport.vm";
    }
}
