/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 *
 * @author School
 */
public class NewMarkbookDialogBean {
    public static final String PROP_MARKBOOKTITLE = "markBookTitle";
    public static final String PROP_MARKBOOKFILELOCATION = "markBookFileLocation";
    public static final String PROP_CURRICULUMTITLE = "curriculumTitle";
    public static final String PROP_MARKBOOKFILE = "markBookFile";
    
    protected String markBookTitle = "";
    protected File markBookFile;
    protected String markBookFileLocation = "";
    protected String curriculumTitle = "";
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
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
     * @return the markBookFileLocation
     */
    public String getMarkBookFileLocation() {
        return markBookFileLocation;
    }

    /**
     * @param markBookFileLocation the markBookFileLocation to set
     */
    public void setMarkBookFileLocation(String markBookFileLocation) {
        java.lang.String oldMarkBookFileLocation = this.markBookFileLocation;
        this.markBookFileLocation = markBookFileLocation;
        propertyChangeSupport.firePropertyChange(PROP_MARKBOOKFILELOCATION, oldMarkBookFileLocation, markBookFileLocation);
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
     * @return the markBookFile
     */
    public File getMarkBookFile() {
        return markBookFile;
    }

    /**
     * @param markBookFile the markBookFile to set
     */
    public void setMarkBookFile(File markBookFile) {
        java.io.File oldMarkBookFile = this.markBookFile;
        this.markBookFile = markBookFile;
        propertyChangeSupport.firePropertyChange(PROP_MARKBOOKFILE, oldMarkBookFile, markBookFile);
    }
            
}
