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

/**
 *
 * @author School
 */
public class ApplicationFrameBean {
    public static final String PROP_MARKBOOKPANELBEANS = "markbookPanelBeans";
    public static final String PROP_TITLE = "title";
    public static final String PROP_SAVEFILEISBOUND = "saveFileIsBound";
    public static final String PROP_REPORTHTML = "reportHtml";
    
    protected List<MarkbookPanelBean> markbookPanelBeans = new LinkedList();
    protected String title;
    protected boolean saveFileIsBound;
    protected String reportHtml;
    
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    } 
    /**
     * @return the markbookPanelBeans
     */
    public List<MarkbookPanelBean> getMarkbookPanelBeans() {
        return markbookPanelBeans;
    }

    /**
     * @param markbookPanelBeans the markbookPanelBeans to set
     */
    public void setMarkbookPanelBeans(List<MarkbookPanelBean> markbookPanelBeans) {
        java.util.List<markbookreporterui.view.MarkbookPanelBean> oldMarkbookPanelBeans = this.markbookPanelBeans;
        this.markbookPanelBeans = markbookPanelBeans;
        propertyChangeSupport.firePropertyChange(PROP_MARKBOOKPANELBEANS, oldMarkbookPanelBeans, markbookPanelBeans);
    }
    
    
    public boolean addMarkbookPanelBean(MarkbookPanelBean markbookPanelBean){
        List<MarkbookPanelBean> oldMarkbookPanelBeans = markbookPanelBeans;
        markbookPanelBeans = new LinkedList();
        markbookPanelBeans.addAll(oldMarkbookPanelBeans);   
        boolean added = markbookPanelBeans.add(markbookPanelBean);
        if(added)
            propertyChangeSupport.firePropertyChange(PROP_MARKBOOKPANELBEANS, oldMarkbookPanelBeans, markbookPanelBeans);         
        return added;        
    }
    
    public boolean removeMarkbookPanelBean(MarkbookPanelBean markbookPanelBean){
        List<MarkbookPanelBean> oldMarkbookPanelBeans = markbookPanelBeans;
        markbookPanelBeans = new LinkedList();
        markbookPanelBeans.addAll(oldMarkbookPanelBeans);   
        boolean removed = markbookPanelBeans.remove(markbookPanelBean);
        if(removed)
            propertyChangeSupport.firePropertyChange(PROP_MARKBOOKPANELBEANS, oldMarkbookPanelBeans, markbookPanelBeans);         
        return removed;        
    }
    
    public void removeAllMarkbookPanelBeans() {
        List<MarkbookPanelBean> oldMarkbookPanelBeans = markbookPanelBeans;
        markbookPanelBeans = new LinkedList();
        propertyChangeSupport.firePropertyChange(PROP_MARKBOOKPANELBEANS, oldMarkbookPanelBeans, markbookPanelBeans);                  
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        java.lang.String oldTitle = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange(PROP_TITLE, oldTitle, title);
    }

    /**
     * @return the saveFileIsBound
     */
    public boolean isSaveFileIsBound() {
        return saveFileIsBound;
    }

    /**
     * @param saveFileIsBound the saveFileIsBound to set
     */
    public void setSaveFileIsBound(boolean saveFileIsBound) {
        boolean oldSaveFileIsBound = this.saveFileIsBound;
        this.saveFileIsBound = saveFileIsBound;
        propertyChangeSupport.firePropertyChange(PROP_SAVEFILEISBOUND, oldSaveFileIsBound, saveFileIsBound);
    }

    /**
     * @return the reportHtml
     */
    public String getReportHtml() {
        return reportHtml;
    }

    /**
     * @param reportHtml the reportHtml to set
     */
    public void setReportHtml(String reportHtml) {
        java.lang.String oldReportHtml = this.reportHtml;
        this.reportHtml = reportHtml;
        propertyChangeSupport.firePropertyChange(PROP_REPORTHTML, oldReportHtml, reportHtml);
    }


    
}
