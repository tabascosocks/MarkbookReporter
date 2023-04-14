/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.curricassessreport;

import markbookreporter.reportbuilder.curricassessreport.model.CARReportModel;
import markbookreporter.reportbuilder.curricassessreport.model.CARProficiencyStrand;
import markbookreporter.reportbuilder.curricassessreport.model.CARAssessmentItemSection;
import markbookreporter.reportbuilder.curricassessreport.model.CARCurriculumItem;
import markbookreporter.reportbuilder.curricassessreport.model.CARAssessmentItem;
import java.util.List;
import java.util.logging.Logger;
import markbookreporter.model.AssessmentItem;
import markbookreporter.model.AssessmentItemSection;
import markbookreporter.model.MarkBook;
import markbookreporter.model.curriculum.Curriculum;
import markbookreporter.model.curriculum.CurriculumItem;
import markbookreporter.model.curriculum.ProficiencyStrand;
import markbookreporter.reportbuilder.ReportBuilder;
import markbookreporter.reportbuilder.ReportConfiguration;

/**
 *
 * @author General
 */
public class CurriculumAssessmentReportBuilder extends ReportBuilder {

    private final static Logger logger = Logger.getLogger(CurriculumAssessmentReportBuilder.class.getName());  
    public static CurriculumAssessmentReportConfiguration carConfig;

    @Override
    protected CARReportModel buildReportModel(MarkBook markBook, ReportConfiguration config) {
        //assign class-global configuration
        carConfig = (CurriculumAssessmentReportConfiguration)config;
        //build the report model
        CARReportModel reportModel = new CARReportModel();
        //add the curriculum items and proficiency strands to the report model
        attachCurriculumItems(reportModel, markBook);
        attachProficiencyStrands(reportModel, markBook);      
        //iterate all the assessment item section in the markbook.
        //for each one, link its curric item and prof strand to the report model
        for(AssessmentItem assessItem : markBook.getAssessmentItems()){
            CARAssessmentItem carAssessmentItem = new CARAssessmentItem();
            carAssessmentItem.setName(assessItem.getName());
            //iterate each section
            for(AssessmentItemSection section : assessItem.getAssessmentItemSections()){
                CARAssessmentItemSection carAssessmentItemSection = new CARAssessmentItemSection();
                //link report assesment item
                carAssessmentItemSection.setAssessmentItem(carAssessmentItem);
                //apply attributes
                carAssessmentItemSection.setName(section.getName());
                carAssessmentItemSection.setTotalMarks(section.getTotalMarks());
                carAssessmentItemSection.setTextBookRef(section.getTextbookReference());
                //add curric items
                for(CurriculumItem curricItem : section.getCurriculumItems()){
                    //find the curric item by code from the report model and link
                    carAssessmentItemSection.getCurriculumItems().add(reportModel.getCARCurriculumItem(curricItem.getCode()));
                    //if we are pushing up curriculum items, then we also need to add its parents
                    if(carConfig.isPushUpChildCurriculumResults()){
                        CurriculumItem nextCurricItemInHeirarchy = curricItem.getParent();
                        while(nextCurricItemInHeirarchy != null){
                            //find the curric item by code from the report model and link
                            carAssessmentItemSection.getCurriculumItems().add(reportModel.getCARCurriculumItem(nextCurricItemInHeirarchy.getCode()));                            
                            nextCurricItemInHeirarchy = nextCurricItemInHeirarchy.getParent();
                        }                        
                    }
                }
                //add proficiency strands
                for(ProficiencyStrand proficStrand : section.getProficiencyStrands()){
                    //find the prof strand by code from the report model
                    CARProficiencyStrand carProficStrand = reportModel.getCARProficiencyStrand(proficStrand.getName());
                    //link
                    carAssessmentItemSection.getProficiencyStrands().add(carProficStrand);
                }
                //link to report model
                reportModel.getAssessmentItemSections().add(carAssessmentItemSection);
            }
        }        
        return reportModel;
    }

    /**
     * Applies the curriculum items which form the table row header for this report, 
     * using either *all* the curriculum items or only those referenced in the markBook, depending
     * on the application configuration
     * @param reportModel
     * @param markBook
     * @param curriculum 
     */
    private void attachCurriculumItems(CARReportModel reportModel, MarkBook markBook) {
        List<CurriculumItem> curriculumItems;        
        if(carConfig.isUseFullCurriculumItems()){
            curriculumItems = markBook.getCurriculum().getCurriculumItems();
        }else{
            curriculumItems = markBook.getReferencedCurriculumItems();
            //since the items will be placed in arbitrary order, realign the ordering to
            //match how they would be found in the curriculum list
            markBook.getCurriculum().sortCurriculumItemsAgaintCurriculum(curriculumItems);             
        }
        for(CurriculumItem curricItem : curriculumItems){
            CARCurriculumItem carCurricItem = new CARCurriculumItem();
            carCurricItem.setDescription(curricItem.getDescription());
            carCurricItem.setName(curricItem.getCode());
            carCurricItem.setContentStrand(curricItem.getContentStrand().getName());
            carCurricItem.setChildCurriculumItem(curricItem.getParent() != null);
            reportModel.getCurriculumItems().add(carCurricItem);
        }        
    }

    /**
     * Applies the proficiency strands which form the table column header for this report, 
     * using either *all* the proficiency strands or only those referenced in the markBook, depending
     * on the application configuration
     * @param reportModel
     * @param markBook
     * @param curriculum 
     */
    private void attachProficiencyStrands(CARReportModel reportModel, MarkBook markBook) {
        List<ProficiencyStrand> proficiencyStrands;
        if(carConfig.isUseFullProficiencyStrands()){
            proficiencyStrands = markBook.getCurriculum().getProficiencyStrands();
        }else{
            proficiencyStrands = markBook.getReferencedProficiencyStrands();
            //since the items will be placed in arbitrary order, realign the ordering to
            //match how they would be found in the curriculum list
            markBook.getCurriculum().sortProficiencyStrandsAgaintCurriculum(proficiencyStrands);               
        }
        for(ProficiencyStrand profStrand : proficiencyStrands){
            CARProficiencyStrand carProfStrand = new CARProficiencyStrand();
            carProfStrand.setDescription(profStrand.getDescription());
            carProfStrand.setName(profStrand.getName());        
            reportModel.getProficiencyStrands().add(carProfStrand);
        }         
    }
    
    @Override
    protected String getVelocityTemplateName() {
       return "CurriculumAssessmentReport.vm";
    }
}