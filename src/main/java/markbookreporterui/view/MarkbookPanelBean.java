/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;
import markbookreporter.enums.AggregationMethod;

/**
 *
 * @author School
 */
public class MarkbookPanelBean {
    public static final String PROP_MARKBOOKFILENAME = "markBookFilename";
    public static final String PROP_MARKBOOKTITLE = "markBookTitle";
    public static final String PROP_CURRICULUMTITLE = "curriculumTitle";
    public static final String PROP_CARPUSHUPCHILDCURRICULUMRESULTS = "carPushUpChildCurriculumResults";
    public static final String PROP_CARUSEFULLCURRICULUMITEMS = "carUseFullCurriculumItems";
    public static final String PROP_CARFULLPROFICIENCYSTRANDS = "carFullProficiencyStrands";
    public static final String PROP_SCRPUSHUPCHILDCURRICULUMRESULTS = "scrPushUpChildCurriculumResults";
    public static final String PROP_SCRUSEFULLCURRICULUMITEMS = "scrUseFullCurriculumItems";
    public static final String PROP_SCRFULLPROFICIENCYSTRANDS = "scrFullProficiencyStrands";
    public static final String PROP_SCRAGGREGATIONMETHOD = "scrAggregationMethod";
    public static final String PROP_ASSESSMENTITEMNAMES = "assessmentItemNames";
    public static final String PROP_STUDENTNAMES = "studentNames";
    public static final String PROP_ASRSELECTEDASSESSMENTITEMSONLY = "asrSelectedAssessmentItemsOnly";
    public static final String PROP_ASRSELECTEDASSESSMENTITEMNAMES = "asrSelectedAssessmentItemNames";
    public static final String PROP_SCRSELECTEDSTUDENTSONLY = "scrSelectedStudentsOnly";
    public static final String PROP_SCRSELECTEDSTUDENTNAMES = "scrSelectedStudentNames";
    public static final String PROP_SARSELECTEDSTUDENTSONLY = "sarSelectedStudentsOnly";
    public static final String PROP_SARSELECTEDSTUDENTNAMES = "sarSelectedStudentNames";
    public static final String PROP_SPRSELECTEDSTUDENTSONLY = "sprSelectedStudentsOnly";
    public static final String PROP_SPRSELECTEDSTUDENTNAMES = "sprSelectedStudentNames";
    public static final String PROP_SCRSELECTEDASSESSITEMSONLY = "scrSelectedAssessItemsOnly";
    public static final String PROP_SCRSELECTEDASSESSITEMS = "scrSelectedAssessItems";
    public static final String PROP_SCRAGGREGATENOTATTEMPTED = "PROP_SCRAGGREGATENOTATTEMPTED";
    
    protected String markBookFilename = "";
    protected String markBookTitle = "";
    protected String curriculumTitle = "";
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
    //Utility list data
    protected List<String> assessmentItemNames = new LinkedList();
    protected List<String> studentNames = new LinkedList();
    
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    
     public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    } 
    
    /**
     * @return the markBookFilename
     */
    public String getMarkBookFilename() {
        return markBookFilename;
    }

    /**
     * @param markBookFilename the markBookFilename to set
     */
    public void setMarkBookFilename(String markBookFilename) {
        java.lang.String oldMarkBookFilename = this.markBookFilename;
        this.markBookFilename = markBookFilename;
        propertyChangeSupport.firePropertyChange(PROP_MARKBOOKFILENAME, oldMarkBookFilename, markBookFilename);
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
        java.lang.String oldMarkBookTitle = this.markBookTitle;
        this.markBookTitle = markBookTitle;
        propertyChangeSupport.firePropertyChange(PROP_MARKBOOKTITLE, oldMarkBookTitle, markBookTitle);
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
        java.lang.String oldCurriculumTitle = this.curriculumTitle;
        this.curriculumTitle = curriculumTitle;
        propertyChangeSupport.firePropertyChange(PROP_CURRICULUMTITLE, oldCurriculumTitle, curriculumTitle);
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
        boolean oldCarPushUpChildCurriculumResults = this.carPushUpChildCurriculumResults;
        this.carPushUpChildCurriculumResults = carPushUpChildCurriculumResults;
        propertyChangeSupport.firePropertyChange(PROP_CARPUSHUPCHILDCURRICULUMRESULTS, oldCarPushUpChildCurriculumResults, carPushUpChildCurriculumResults);
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
        boolean oldCarUseFullCurriculumItems = this.carUseFullCurriculumItems;
        this.carUseFullCurriculumItems = carUseFullCurriculumItems;
        propertyChangeSupport.firePropertyChange(PROP_CARUSEFULLCURRICULUMITEMS, oldCarUseFullCurriculumItems, carUseFullCurriculumItems);
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
        boolean oldCarFullProficiencyStrands = this.carFullProficiencyStrands;
        this.carFullProficiencyStrands = carFullProficiencyStrands;
        propertyChangeSupport.firePropertyChange(PROP_CARFULLPROFICIENCYSTRANDS, oldCarFullProficiencyStrands, carFullProficiencyStrands);
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
        boolean oldScrPushUpChildCurriculumResults = this.scrPushUpChildCurriculumResults;
        this.scrPushUpChildCurriculumResults = scrPushUpChildCurriculumResults;
        propertyChangeSupport.firePropertyChange(PROP_SCRPUSHUPCHILDCURRICULUMRESULTS, oldScrPushUpChildCurriculumResults, scrPushUpChildCurriculumResults);
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
        boolean oldScrUseFullCurriculumItems = this.scrUseFullCurriculumItems;
        this.scrUseFullCurriculumItems = scrUseFullCurriculumItems;
        propertyChangeSupport.firePropertyChange(PROP_SCRUSEFULLCURRICULUMITEMS, oldScrUseFullCurriculumItems, scrUseFullCurriculumItems);
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
        boolean oldScrFullProficiencyStrands = this.scrFullProficiencyStrands;
        this.scrFullProficiencyStrands = scrFullProficiencyStrands;
        propertyChangeSupport.firePropertyChange(PROP_SCRFULLPROFICIENCYSTRANDS, oldScrFullProficiencyStrands, scrFullProficiencyStrands);
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
        markbookreporter.enums.AggregationMethod oldScrAggregationMethod = this.scrAggregationMethod;
        this.scrAggregationMethod = scrAggregationMethod;
        propertyChangeSupport.firePropertyChange(PROP_SCRAGGREGATIONMETHOD, oldScrAggregationMethod, scrAggregationMethod);
    }

    /**
     * @return the assessmentItemNames
     */
    public List<String> getAssessmentItemNames() {
        return assessmentItemNames;
    }

    /**
     * @param assessmentItemNames the assessmentItemNames to set
     */
    public void setAssessmentItemNames(List<String> assessmentItemNames) {
        java.util.List<java.lang.String> oldAssessmentItemNames = this.assessmentItemNames;
        this.assessmentItemNames = assessmentItemNames;
        propertyChangeSupport.firePropertyChange(PROP_ASSESSMENTITEMNAMES, oldAssessmentItemNames, assessmentItemNames);
    }
    
    public boolean addAssessmentItemName(String assessmentItemName){
        List<String> oldAssessmentItemNames = assessmentItemNames;
        assessmentItemNames = new LinkedList();
        assessmentItemNames.addAll(oldAssessmentItemNames);   
        boolean added = assessmentItemNames.add(assessmentItemName);
        if(added)
            propertyChangeSupport.firePropertyChange(PROP_ASSESSMENTITEMNAMES, oldAssessmentItemNames, assessmentItemNames);         
        return added;        
    }
    
    public boolean removeAssessmentItemName(String assessmentItemName){
        List<String> oldAssessmentItemNames = assessmentItemNames;
        assessmentItemNames = new LinkedList();
        assessmentItemNames.addAll(oldAssessmentItemNames);   
        boolean removed = assessmentItemNames.remove(assessmentItemName);
        if(removed)
            propertyChangeSupport.firePropertyChange(PROP_ASSESSMENTITEMNAMES, oldAssessmentItemNames, assessmentItemNames);         
        return removed;        
    }
    
    public void removeAllAssessmentItemNames() {
        List<String> oldAssessmentItemNames = assessmentItemNames;
        assessmentItemNames = new LinkedList();
        propertyChangeSupport.firePropertyChange(PROP_ASSESSMENTITEMNAMES, oldAssessmentItemNames, assessmentItemNames);                  
    }

    /**
     * @return the studentNames
     */
    public List<String> getStudentNames() {
        return studentNames;
    }

    /**
     * @param studentNames the studentNames to set
     */
    public void setStudentNames(List<String> studentNames) {
        java.util.List<java.lang.String> oldStudentNames = this.studentNames;
        this.studentNames = studentNames;
        propertyChangeSupport.firePropertyChange(PROP_STUDENTNAMES, oldStudentNames, studentNames);
    }
    
    public boolean addStudentName(String studentName){
        List<String> oldStudentNames = studentNames;
        studentNames = new LinkedList();
        studentNames.addAll(oldStudentNames);   
        boolean added = studentNames.add(studentName);
        if(added)
            propertyChangeSupport.firePropertyChange(PROP_ASSESSMENTITEMNAMES, oldStudentNames, studentNames);         
        return added;        
    }
    
    public boolean removeStudentName(String studentName){
        List<String> oldStudentNames = studentNames;
        studentNames = new LinkedList();
        studentNames.addAll(oldStudentNames);   
        boolean removed = studentNames.remove(studentName);
        if(removed)
            propertyChangeSupport.firePropertyChange(PROP_ASSESSMENTITEMNAMES, oldStudentNames, studentNames);         
        return removed;        
    }
    
    public void removeAllStudentNames() {
        List<String> oldStudentNames = studentNames;
        studentNames = new LinkedList();
        propertyChangeSupport.firePropertyChange(PROP_ASSESSMENTITEMNAMES, oldStudentNames, studentNames);                  
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
        boolean oldAsrSelectedAssessmentItemsOnly = this.asrSelectedAssessmentItemsOnly;
        this.asrSelectedAssessmentItemsOnly = asrSelectedAssessmentItemsOnly;
        propertyChangeSupport.firePropertyChange(PROP_ASRSELECTEDASSESSMENTITEMSONLY, oldAsrSelectedAssessmentItemsOnly, asrSelectedAssessmentItemsOnly);
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
        java.util.List<java.lang.String> oldAsrSelectedAssessmentItemNames = this.asrSelectedAssessmentItemNames;
        this.asrSelectedAssessmentItemNames = asrSelectedAssessmentItemNames;
        propertyChangeSupport.firePropertyChange(PROP_ASRSELECTEDASSESSMENTITEMNAMES, oldAsrSelectedAssessmentItemNames, asrSelectedAssessmentItemNames);
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
        boolean oldScrSelectedStudentsOnly = this.scrSelectedStudentsOnly;
        this.scrSelectedStudentsOnly = scrSelectedStudentsOnly;
        propertyChangeSupport.firePropertyChange(PROP_SCRSELECTEDSTUDENTSONLY, oldScrSelectedStudentsOnly, scrSelectedStudentsOnly);
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
        java.util.List<java.lang.String> oldScrSelectedStudentNames = this.scrSelectedStudentNames;
        this.scrSelectedStudentNames = scrSelectedStudentNames;
        propertyChangeSupport.firePropertyChange(PROP_SCRSELECTEDSTUDENTNAMES, oldScrSelectedStudentNames, scrSelectedStudentNames);
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
        boolean oldSarSelectedStudentsOnly = this.sarSelectedStudentsOnly;
        this.sarSelectedStudentsOnly = sarSelectedStudentsOnly;
        propertyChangeSupport.firePropertyChange(PROP_SARSELECTEDSTUDENTSONLY, oldSarSelectedStudentsOnly, sarSelectedStudentsOnly);
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
        java.util.List<java.lang.String> oldSarSelectedStudentNames = this.sarSelectedStudentNames;
        this.sarSelectedStudentNames = sarSelectedStudentNames;
        propertyChangeSupport.firePropertyChange(PROP_SARSELECTEDSTUDENTNAMES, oldSarSelectedStudentNames, sarSelectedStudentNames);
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
        boolean oldSprSelectedStudentsOnly = this.sprSelectedStudentsOnly;
        this.sprSelectedStudentsOnly = sprSelectedStudentsOnly;
        propertyChangeSupport.firePropertyChange(PROP_SPRSELECTEDSTUDENTSONLY, oldSprSelectedStudentsOnly, sprSelectedStudentsOnly);
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
        java.util.List<java.lang.String> oldSprSelectedStudentNames = this.sprSelectedStudentNames;
        this.sprSelectedStudentNames = sprSelectedStudentNames;
        propertyChangeSupport.firePropertyChange(PROP_SPRSELECTEDSTUDENTNAMES, oldSprSelectedStudentNames, sprSelectedStudentNames);
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
        boolean oldScrSelectedAssessItemsOnly = this.scrSelectedAssessItemsOnly;
        this.scrSelectedAssessItemsOnly = scrSelectedAssessItemsOnly;
        propertyChangeSupport.firePropertyChange(PROP_SCRSELECTEDASSESSITEMSONLY, oldScrSelectedAssessItemsOnly, scrSelectedAssessItemsOnly);
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
        java.util.List<java.lang.String> oldScrSelectedAssessItems = this.scrSelectedAssessItems;
        this.scrSelectedAssessItems = scrSelectedAssessItems;
        propertyChangeSupport.firePropertyChange(PROP_SCRSELECTEDASSESSITEMS, oldScrSelectedAssessItems, scrSelectedAssessItems);
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
        boolean oldScrAggregateNotAttempted = this.scrAggregateNotAttempted;
        this.scrAggregateNotAttempted = scrAggregateNotAttempted;
        propertyChangeSupport.firePropertyChange(PROP_SCRAGGREGATENOTATTEMPTED, oldScrAggregateNotAttempted, scrAggregateNotAttempted);
    }

}
