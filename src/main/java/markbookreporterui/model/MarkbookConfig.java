/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.model;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import markbookreporter.MarkbookReporter;
import markbookreporter.enums.AggregationMethod;
import markbookreporter.model.AssessmentItem;
import markbookreporter.model.MarkBook;
import markbookreporter.model.Student;
import markbookreporter.model.curriculum.Curriculum;
import markbookreporter.reportbuilder.assessresultsreport.AssessmentResultsReportConfiguration;
import markbookreporter.reportbuilder.curricassessreport.CurriculumAssessmentReportConfiguration;
import markbookreporter.reportbuilder.studentassessmentreport.StudentAssessmentReportConfiguration;
import markbookreporter.reportbuilder.studentcurricreport.StudentCurriculumReportConfiguration;
import markbookreporter.reportbuilder.studentprogreport.StudentProgressReportConfiguration;
import markbookreporterui.view.enums.ReportType;

/**
 *
 * @author School
 */
public class MarkbookConfig implements Serializable{
    public static final long serialVersionUID = -2416099288069951674L; 
    
    protected transient MarkBook markBook;
    
    protected File markBookFile;
    protected String markBookTitle;
    protected String curriculumTitle;
    //ASR Report Config Elements
    protected boolean asrSelectedAssessmentItemsOnly = false;
    protected List<String> asrSelectedAssessmentItemNames = new LinkedList();
    //CAR Report Config Elements
    protected boolean carPushUpChildCurriculumResults = false;
    protected boolean carUseFullCurriculumItems = false;
    protected boolean carFullProficiencyStrands = false;
    //SAR Report Config Elements
    protected boolean sarSelectedStudentsOnly = false;
    protected List<String> sarSelectedStudentNames = new LinkedList();     
    //SCR Report Config Elements
    protected boolean scrPushUpChildCurriculumResults = false;
    protected boolean scrUseFullCurriculumItems = false;
    protected boolean scrFullProficiencyStrands = false;
    protected AggregationMethod scrAggregationMethod = AggregationMethod.ACCUMULATED;
    protected boolean scrAggregateNotAttempted = false;
    protected boolean scrSelectedStudentsOnly = false;
    protected List<String> scrSelectedStudentNames = new LinkedList(); 
    protected boolean scrSelectedAssessItemsOnly = false;
    protected List<String> scrSelectedAssessItems = new LinkedList();
    //SPR Report Config Elements
    protected boolean sprSelectedStudentsOnly = false;
    protected List<String> sprSelectedStudentNames = new LinkedList();   
    /**
     * @return the markBook
     */
    public MarkBook getMarkBook() {
        return markBook;
    }
    
    public List<String> getAssessmentItemNamesFromMarkbook(){
        List<String> result = new LinkedList();
        for(AssessmentItem item : markBook.getAssessmentItems()){
            result.add(item.getName());
        }
        return result;
    }
    
    public List<String> getStudentNamesFromMarkbook(){
        List<String> result = new LinkedList();
        for(Student student : markBook.getStudents()){
            result.add(student.toString());
        }
        return result;
    }
    
    /**
     * Delegates a call to the relevant MarkbookReporter generate method based
     * on the report type
     * @param reportType
     * @return the html, or nul if something didnt work
     */
    public String generateReport(ReportType reportType){
        //make sure the markbook is bound
        if (markBook == null) {
            bindMarkBook();
        }
        //fetch the report html
        String html = null;
        switch (reportType) {
            case ASR:
                html = MarkbookReporter.getInstance().generateAssessmentResultsReport(markBook, buildASRConfig());
                break;
            case CAR:
                html = MarkbookReporter.getInstance().generateCurriculumAssessmentReport(markBook, buildCARConfig());
                break;
            case SAR:
                html = MarkbookReporter.getInstance().generateStudentAssessmentReport(markBook, buildSARConfig());
                break;
            case SCR:
                html = MarkbookReporter.getInstance().generateStudentCurriculumReport(markBook, buildSCRConfig());
                break;
            case SPR:
                html = MarkbookReporter.getInstance().generateStudentProgressReport(markBook, buildSPRConfig());
                break;
        }   
        return html;
    }
    
    public AssessmentResultsReportConfiguration buildASRConfig(){
        AssessmentResultsReportConfiguration conf = new AssessmentResultsReportConfiguration();
        conf.setSelectedAssessmentItemsOnly(this.isAsrSelectedAssessmentItemsOnly());
        for(String itemName : this.getAsrSelectedAssessmentItemNames()){
            for(AssessmentItem item : markBook.getAssessmentItems()){
                if(item.getName().equals(itemName)){
                    conf.getSelectedAssessmentItems().add(item);
                }
            }
        }
        return conf;
    }
    
    public CurriculumAssessmentReportConfiguration buildCARConfig(){
        CurriculumAssessmentReportConfiguration conf = new CurriculumAssessmentReportConfiguration();
        conf.setPushUpChildCurriculumResults(carPushUpChildCurriculumResults);
        conf.setUseFullCurriculumItems(carUseFullCurriculumItems);
        conf.setUseFullProficiencyStrands(carFullProficiencyStrands);
        return conf;
    }
    
    public StudentAssessmentReportConfiguration buildSARConfig(){
        StudentAssessmentReportConfiguration conf = new StudentAssessmentReportConfiguration();
        conf.setSelectedStudentsOnly(this.isSarSelectedStudentsOnly());
        for(String studentName : this.getSarSelectedStudentNames()){
            for(Student student : markBook.getStudents()){
                if(student.toString().equals(studentName)){
                    conf.getSelectedStudents().add(student);
                }
            }
        }        
        return conf;   
    }
    
    public StudentCurriculumReportConfiguration buildSCRConfig(){
        StudentCurriculumReportConfiguration conf = new StudentCurriculumReportConfiguration();
        conf.setPushUpChildCurriculumResults(scrPushUpChildCurriculumResults);
        conf.setUseFullCurriculumItems(scrUseFullCurriculumItems);
        conf.setUseFullProficiencyStrands(scrFullProficiencyStrands);
        conf.setAggregationMethod(scrAggregationMethod);
        conf.setAggregateNotAttempted(scrAggregateNotAttempted);
        conf.setSelectedStudentsOnly(this.isScrSelectedStudentsOnly());
        for(String studentName : this.getScrSelectedStudentNames()){
            for(Student student : markBook.getStudents()){
                if(student.toString().equals(studentName)){
                    conf.getSelectedStudents().add(student);
                }
            }
        }
        conf.setSelectedAssessItemsOnly(this.isScrSelectedAssessItemsOnly());
        for(String assessItemName : this.getScrSelectedAssessItems()){
            for(AssessmentItem assessItem : markBook.getAssessmentItems()){
                if(assessItem.getName().equals(assessItemName)){
                    conf.getSelectedAssessItems().add(assessItem);
                }
            }
        }
        return conf;
    }
    
    public StudentProgressReportConfiguration buildSPRConfig(){
        StudentProgressReportConfiguration conf = new StudentProgressReportConfiguration();
        conf.setSelectedStudentsOnly(this.isSprSelectedStudentsOnly());
        for(String studentName : this.getSprSelectedStudentNames()){
            for(Student student : markBook.getStudents()){
                if(student.toString().equals(studentName)){
                    conf.getSelectedStudents().add(student);
                }
            }
        }
        return conf;
    }
    
    /**
     * Uses the curriculum title and the markbook file to bind
     * an instantiated MarkBook
     * @return Whether or not the binding was successful
     */
    public boolean bindMarkBook(){
        //fetch the curriculum object attached to this markbook
        Curriculum configCurriculum = null;
        for(Curriculum curr : MarkbookReporter.getInstance().getCurriculums()){
            if(curr.getName().equals(this.getCurriculumTitle())){
                configCurriculum = curr;
                break;
            }
        }
        if(this.getMarkBookFile() == null || configCurriculum == null) return false;
        
        MarkBook markBook = MarkbookReporter.getInstance().loadMarkBook(this.getMarkBookFile(), configCurriculum);
        if(markBook == null) return false;
        this.setMarkBook(markBook);
        return true;
    }

    /**
     * @param markBook the markBook to set
     */
    public void setMarkBook(MarkBook markBook) {
        this.markBook = markBook;
    }

    /**
     * @return the markBookTitle
     */
    public String getMarkBookTitle() {
        return markBookTitle;
    }

    /**
     * @param markBookTitle the markBookTitle to set
     */
    public void setMarkBookTitle(String markBookTitle) {
        this.markBookTitle = markBookTitle;
    }

    /**
     * @return the curriculumTitle
     */
    public String getCurriculumTitle() {
        return curriculumTitle;
    }

    /**
     * @param curriculumTitle the curriculumTitle to set
     */
    public void setCurriculumTitle(String curriculumTitle) {
        this.curriculumTitle = curriculumTitle;
    }

    /**
     * @return the carPushUpChildCurriculumResults
     */
    public boolean isCarPushUpChildCurriculumResults() {
        return carPushUpChildCurriculumResults;
    }

    /**
     * @param carPushUpChildCurriculumResults the carPushUpChildCurriculumResults to set
     */
    public void setCarPushUpChildCurriculumResults(boolean carPushUpChildCurriculumResults) {
        this.carPushUpChildCurriculumResults = carPushUpChildCurriculumResults;
    }

    /**
     * @return the carUseFullCurriculumItems
     */
    public boolean isCarUseFullCurriculumItems() {
        return carUseFullCurriculumItems;
    }

    /**
     * @param carUseFullCurriculumItems the carUseFullCurriculumItems to set
     */
    public void setCarUseFullCurriculumItems(boolean carUseFullCurriculumItems) {
        this.carUseFullCurriculumItems = carUseFullCurriculumItems;
    }

    /**
     * @return the carFullProficiencyStrands
     */
    public boolean isCarFullProficiencyStrands() {
        return carFullProficiencyStrands;
    }

    /**
     * @param carFullProficiencyStrands the carFullProficiencyStrands to set
     */
    public void setCarFullProficiencyStrands(boolean carFullProficiencyStrands) {
        this.carFullProficiencyStrands = carFullProficiencyStrands;
    }

    /**
     * @return the scrPushUpChildCurriculumResults
     */
    public boolean isScrPushUpChildCurriculumResults() {
        return scrPushUpChildCurriculumResults;
    }

    /**
     * @param scrPushUpChildCurriculumResults the scrPushUpChildCurriculumResults to set
     */
    public void setScrPushUpChildCurriculumResults(boolean scrPushUpChildCurriculumResults) {
        this.scrPushUpChildCurriculumResults = scrPushUpChildCurriculumResults;
    }

    /**
     * @return the scrUseFullCurriculumItems
     */
    public boolean isScrUseFullCurriculumItems() {
        return scrUseFullCurriculumItems;
    }

    /**
     * @param scrUseFullCurriculumItems the scrUseFullCurriculumItems to set
     */
    public void setScrUseFullCurriculumItems(boolean scrUseFullCurriculumItems) {
        this.scrUseFullCurriculumItems = scrUseFullCurriculumItems;
    }

    /**
     * @return the scrFullProficiencyStrands
     */
    public boolean isScrFullProficiencyStrands() {
        return scrFullProficiencyStrands;
    }

    /**
     * @param scrFullProficiencyStrands the scrFullProficiencyStrands to set
     */
    public void setScrFullProficiencyStrands(boolean scrFullProficiencyStrands) {
        this.scrFullProficiencyStrands = scrFullProficiencyStrands;
    }

    /**
     * @return the scrAggregationMethod
     */
    public AggregationMethod getScrAggregationMethod() {
        return scrAggregationMethod;
    }

    /**
     * @param scrAggregationMethod the scrAggregationMethod to set
     */
    public void setScrAggregationMethod(AggregationMethod scrAggregationMethod) {
        this.scrAggregationMethod = scrAggregationMethod;
    }

    /**
     * @return the markBookFile
     */
    public File getMarkBookFile() {
        return markBookFile;
    }

    /**
     * @param markBookFile the markBookFile to set
     */
    public void setMarkBookFile(File markBookFile) {
        this.markBookFile = markBookFile;
    }

    /**
     * @return the asrSelectedAssessmentItemsOnly
     */
    public boolean isAsrSelectedAssessmentItemsOnly() {
        return asrSelectedAssessmentItemsOnly;
    }

    /**
     * @param asrSelectedAssessmentItemsOnly the asrSelectedAssessmentItemsOnly to set
     */
    public void setAsrSelectedAssessmentItemsOnly(boolean asrSelectedAssessmentItemsOnly) {
        this.asrSelectedAssessmentItemsOnly = asrSelectedAssessmentItemsOnly;
    }

    /**
     * @return the asrSelectedAssessmentItemNames
     */
    public List<String> getAsrSelectedAssessmentItemNames() {
        return asrSelectedAssessmentItemNames;
    }

    /**
     * @param asrSelectedAssessmentItemNames the asrSelectedAssessmentItemNames to set
     */
    public void setAsrSelectedAssessmentItemNames(List<String> asrSelectedAssessmentItemNames) {
        this.asrSelectedAssessmentItemNames = asrSelectedAssessmentItemNames;
    }

    /**
     * @return the scrSelectedStudentsOnly
     */
    public boolean isScrSelectedStudentsOnly() {
        return scrSelectedStudentsOnly;
    }

    /**
     * @param scrSelectedStudentsOnly the scrSelectedStudentsOnly to set
     */
    public void setScrSelectedStudentsOnly(boolean scrSelectedStudentsOnly) {
        this.scrSelectedStudentsOnly = scrSelectedStudentsOnly;
    }

    /**
     * @return the scrSelectedStudentNames
     */
    public List<String> getScrSelectedStudentNames() {
        return scrSelectedStudentNames;
    }

    /**
     * @param scrSelectedStudentNames the scrSelectedStudentNames to set
     */
    public void setScrSelectedStudentNames(List<String> scrSelectedStudentNames) {
        this.scrSelectedStudentNames = scrSelectedStudentNames;
    }

    /**
     * @return the sarSelectedStudentsOnly
     */
    public boolean isSarSelectedStudentsOnly() {
        return sarSelectedStudentsOnly;
    }

    /**
     * @param sarSelectedStudentsOnly the sarSelectedStudentsOnly to set
     */
    public void setSarSelectedStudentsOnly(boolean sarSelectedStudentsOnly) {
        this.sarSelectedStudentsOnly = sarSelectedStudentsOnly;
    }

    /**
     * @return the sarSelectedStudentNames
     */
    public List<String> getSarSelectedStudentNames() {
        return sarSelectedStudentNames;
    }

    /**
     * @param sarSelectedStudentNames the sarSelectedStudentNames to set
     */
    public void setSarSelectedStudentNames(List<String> sarSelectedStudentNames) {
        this.sarSelectedStudentNames = sarSelectedStudentNames;
    }

    /**
     * @return the sprSelectedStudentsOnly
     */
    public boolean isSprSelectedStudentsOnly() {
        return sprSelectedStudentsOnly;
    }

    /**
     * @param sprSelectedStudentsOnly the sprSelectedStudentsOnly to set
     */
    public void setSprSelectedStudentsOnly(boolean sprSelectedStudentsOnly) {
        this.sprSelectedStudentsOnly = sprSelectedStudentsOnly;
    }

    /**
     * @return the sprSelectedStudentNames
     */
    public List<String> getSprSelectedStudentNames() {
        return sprSelectedStudentNames;
    }

    /**
     * @param sprSelectedStudentNames the sprSelectedStudentNames to set
     */
    public void setSprSelectedStudentNames(List<String> sprSelectedStudentNames) {
        this.sprSelectedStudentNames = sprSelectedStudentNames;
    }

    /**
     * @return the scrSelectedAssessItemsOnly
     */
    public boolean isScrSelectedAssessItemsOnly() {
        return scrSelectedAssessItemsOnly;
    }

    /**
     * @param scrSelectedAssessItemsOnly the scrSelectedAssessItemsOnly to set
     */
    public void setScrSelectedAssessItemsOnly(boolean scrSelectedAssessItemsOnly) {
        this.scrSelectedAssessItemsOnly = scrSelectedAssessItemsOnly;
    }

    /**
     * @return the scrSelectedAssessItems
     */
    public List<String> getScrSelectedAssessItems() {
        return scrSelectedAssessItems;
    }

    /**
     * @param scrSelectedAssessItems the scrSelectedAssessItems to set
     */
    public void setScrSelectedAssessItems(List<String> scrSelectedAssessItems) {
        this.scrSelectedAssessItems = scrSelectedAssessItems;
    }

    /**
     * @return the scrAggregateNotAttempted
     */
    public boolean isScrAggregateNotAttempted() {
        return scrAggregateNotAttempted;
    }

    /**
     * @param scrAggregateNotAttempted the scrAggregateNotAttempted to set
     */
    public void setScrAggregateNotAttempted(boolean scrAggregateNotAttempted) {
        this.scrAggregateNotAttempted = scrAggregateNotAttempted;
    }
    
}
