/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import markbookreporter.model.AssessmentItem;
import markbookreporter.model.AssessmentItemSection;
import markbookreporter.model.AssessmentItemSectionResult;
import markbookreporter.model.MarkBook;
import markbookreporter.model.Student;
import markbookreporter.model.curriculum.CurriculumItem;
import markbookreporter.model.curriculum.ProficiencyStrand;
import markbookreporter.reportbuilder.ReportBuilder;
import markbookreporter.reportbuilder.ReportConfiguration;
import markbookreporter.reportbuilder.studentcurricreport.model.SCRReportModel;
import markbookreporter.reportbuilder.studentcurricreport.model.SCRAssessmentItemResult;
import markbookreporter.reportbuilder.studentcurricreport.model.SCRCurriculumItem;
import markbookreporter.reportbuilder.studentcurricreport.model.SCRProficiencyStrand;
import markbookreporter.reportbuilder.studentcurricreport.model.SCRStudent;
import markbookreporter.reportbuilder.studentcurricreport.model.SCRStudentGrade;

/**
 *
 * @author General
 */
public class StudentCurriculumReportBuilder extends ReportBuilder{

    public static StudentCurriculumReportConfiguration scrConfig;
    @Override
    protected SCRReportModel buildReportModel(MarkBook markBook, ReportConfiguration config) {
        //assign class-global configuration
        scrConfig = (StudentCurriculumReportConfiguration)config;
        //build the report model
        SCRReportModel reportModel = new SCRReportModel();
        //add the curriculum items and proficiency strands to the report model
        attachCurriculumItems(reportModel, markBook);
        attachProficiencyStrands(reportModel, markBook);     
        //
        //for each student, build the grade
        for(Student student : markBook.getStudents()){
            //check config to see if we should exclude this item
            if(scrConfig.isSelectedStudentsOnly()){
                //if its not in the list, skip
                if(! scrConfig.getSelectedStudents().contains(student)){
                    continue;
                }
            }
            //build top level model component
            SCRStudent scrStudent = new SCRStudent();
            scrStudent.setName(student.getFirstName() + " " + student.getLastName());
            //build a grade matrix for each combination ot curriculum item and performance strand.
            //each element contains a grade object to which we will attach assessmentitem results
            int totalCurriculumItems = reportModel.getCurriculumItems().size();
            int totalProficiencyStrands = reportModel.getProficiencyStrands().size();
            SCRStudentGrade[][] gradeMatrix = new SCRStudentGrade[totalCurriculumItems][totalProficiencyStrands];
            //populate with instantiated grades
            for(int i = 0; i < totalCurriculumItems; i++){
                for(int j = 0; j < totalProficiencyStrands; j++){
                    SCRStudentGrade nextGrade = new SCRStudentGrade(scrConfig);
                    //assign curr item and prof strand references
                    nextGrade.setCurriculumItem(reportModel.getCurriculumItems().get(i));
                    nextGrade.setProficiencyStrand(reportModel.getProficiencyStrands().get(j));
                    //assign to array
                    gradeMatrix[i][j] = nextGrade;   
                    //assign to student
                    scrStudent.getGrades().add(nextGrade);
                }
            }
            //for each assessment item result, locate the SCRStudent grade to which it applies
            //(must reference same id for curriculum item and proficiency strand)
            for(AssessmentItemSectionResult assessResult : student.getAssessmentItemSectionResults()){
                //check config to see if we should exclude this item
                if(scrConfig.isSelectedAssessItemsOnly()){
                    //if its not in the list, skip
                    if(! scrConfig.getSelectedAssessItems().contains(assessResult.getAssessmentItemSection().getAssessmentItem())){
                        continue;
                    }
                }         
                //add the name of the results assessment item to the report's student object
                String assessItemName = assessResult.getAssessmentItemSection().getAssessmentItem().getName();
                if(! scrStudent.getAssessmentItemNames().contains(assessItemName)){
                    scrStudent.getAssessmentItemNames().add(assessItemName);
                }
                //instantiate SCRAssessmentResult
                SCRAssessmentItemResult scrAssessResult = new SCRAssessmentItemResult();
                scrAssessResult.setAssessmentItemName(assessResult.getAssessmentItemSection().getAssessmentItem().getName());
                scrAssessResult.setAssessmentItemSectionName(assessResult.getAssessmentItemSection().getName());
                scrAssessResult.setAssessmentItemSectionTotalMarks(assessResult.getAssessmentItemSection().getTotalMarks());
                scrAssessResult.setAssessmentItemTaskArchetype(assessResult.getAssessmentItemSection().getTaskArchetype());
                scrAssessResult.setMarksAttained(assessResult.getMarksAttained());
                scrAssessResult.setCompletionStatus(assessResult.getCompletionStatus());
                //find all elements in the grade matrix that applies to this result and add it to the grade element in that location
                //this is done by examining in each element in the grade amtrix and seeing if that elements curriculum item
                //and proficiency strand exist as a reference in the assessment item.
                for(int i = 0; i < totalCurriculumItems; i++){
                    for(int j = 0; j < totalProficiencyStrands; j++){
                        if(curriculumItemExistsInList(gradeMatrix[i][j].getCurriculumItem(), assessResult.getAssessmentItemSection().getCurriculumItems())
                                && proficiencyStrandExistsInList(gradeMatrix[i][j].getProficiencyStrand(), assessResult.getAssessmentItemSection().getProficiencyStrands())){
                            //we have found an element in the grade matrix which applies to this assessment result.
                            gradeMatrix[i][j].getAssessmentItemResults().add(scrAssessResult);
                        }
                    }
                }                
            }
            //add student to model
            reportModel.getStudents().add(scrStudent);
        }
        return reportModel;
    }
    
    private boolean curriculumItemExistsInList(SCRCurriculumItem scrCurricItem, List<CurriculumItem> curricItemlist){
        boolean result = false;
        for(CurriculumItem curricItem : curricItemlist){
            if(scrCurricItem.getName().equalsIgnoreCase(curricItem.getCode())) return true;
            //if we are pushing up curriculum items, then we also need to check if its parent(s) are a match
            if(scrConfig.isPushUpChildCurriculumResults()){
                CurriculumItem nextCurricItemInHeirarchy = curricItem.getParent();
                while(nextCurricItemInHeirarchy != null){
                    if(scrCurricItem.getName().equalsIgnoreCase(nextCurricItemInHeirarchy.getCode())) return true;
                    nextCurricItemInHeirarchy = nextCurricItemInHeirarchy.getParent();
                }
            }
        }
        return result;
    }
    
    private boolean proficiencyStrandExistsInList(SCRProficiencyStrand scrProfStrand, List<ProficiencyStrand> profStrandList){
        boolean result = false;
        for(ProficiencyStrand profStrand : profStrandList){
            if(scrProfStrand.getName().equalsIgnoreCase(profStrand.getName())) return true;
        }
        return result;
    }    
    
    /**
     * Applies the curriculum items which form the table row header for this report, 
     * using either *all* the curriculum items or only those referenced in the markBook, depending
     * on the application configuration
     * @param reportModel
     * @param markBook
     * @param curriculum 
     */
    private void attachCurriculumItems(SCRReportModel reportModel, MarkBook markBook) {
        List<CurriculumItem> curriculumItems;        
        if(scrConfig.isUseFullCurriculumItems()){
            curriculumItems = markBook.getCurriculum().getCurriculumItems();
        }else if(scrConfig.isSelectedAssessItemsOnly()){
            //only add the curriculum items refernced by the assessment items provided
            Set<CurriculumItem> currItemsFound = new HashSet();
            for(AssessmentItem item : scrConfig.getSelectedAssessItems()){
                for(AssessmentItemSection section : item.getAssessmentItemSections()){
                    currItemsFound.addAll(section.getCurriculumItems());
                }
            }
            curriculumItems = new LinkedList();
            curriculumItems.addAll(currItemsFound);
        }else{
            //otherwise, only add those refernced by all the assesmsnet items in the markbook
            curriculumItems = markBook.getReferencedCurriculumItems();
        }
        //since the items will be placed in arbitrary order, realign the ordering to
        //match how they would be found in the curriculum list
        markBook.getCurriculum().sortCurriculumItemsAgaintCurriculum(curriculumItems);       
        for(CurriculumItem curricItem : curriculumItems){
            SCRCurriculumItem scrCurricItem = new SCRCurriculumItem();
            scrCurricItem.setDescription(curricItem.getDescription());
            scrCurricItem.setName(curricItem.getCode());
            scrCurricItem.setContentStrand(curricItem.getContentStrand().getName());
            scrCurricItem.setChildCurriculumItem(curricItem.getParent() != null);
            reportModel.getCurriculumItems().add(scrCurricItem);
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
    private void attachProficiencyStrands(SCRReportModel reportModel, MarkBook markBook) {
        List<ProficiencyStrand> proficiencyStrands;
        if(scrConfig.isUseFullProficiencyStrands()){
            proficiencyStrands = markBook.getCurriculum().getProficiencyStrands();
        }else if(scrConfig.isSelectedAssessItemsOnly()){
            //only add the proficiency strands refernced by the assessment items provided
            Set<ProficiencyStrand> profStrandsFound = new HashSet();
            for(AssessmentItem item : scrConfig.getSelectedAssessItems()){
                for(AssessmentItemSection section : item.getAssessmentItemSections()){
                    profStrandsFound.addAll(section.getProficiencyStrands());
                }
            }
            proficiencyStrands = new LinkedList();
            proficiencyStrands.addAll(profStrandsFound);
        }else{
            proficiencyStrands = markBook.getReferencedProficiencyStrands();          
        }
        //since the items will be placed in arbitrary order, realign the ordering to
        //match how they would be found in the curriculum list
        markBook.getCurriculum().sortProficiencyStrandsAgaintCurriculum(proficiencyStrands);   
        for(ProficiencyStrand profStrand : proficiencyStrands){
            SCRProficiencyStrand scrProfStrand = new SCRProficiencyStrand();
            scrProfStrand.setDescription(profStrand.getDescription());
            scrProfStrand.setName(profStrand.getName());        
            reportModel.getProficiencyStrands().add(scrProfStrand);
        }         
    } 
    
    @Override
    protected String getVelocityTemplateName() {
       return "StudentCurriculumReport.vm";
    }
    
}
