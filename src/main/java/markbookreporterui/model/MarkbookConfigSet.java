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

/**
 *
 * @author School
 */
public class MarkbookConfigSet implements Serializable{
    public static final long serialVersionUID = -2416099288069951674L; 
    
    protected List<MarkbookConfig> markbookConfigs = new LinkedList();
    protected String title = "New Markbook Set";
    protected File loadedFrom = null;
    /**
     * @return the markbookConfigs
     */
    public List<MarkbookConfig> getMarkbookConfigs() {
        return markbookConfigs;
    }

    /**
     * @param markbookConfigs the markbookConfigs to set
     */
    public void setMarkbookConfigs(List<MarkbookConfig> markbookConfigs) {
        this.markbookConfigs = markbookConfigs;
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
        this.title = title;
    }

    /**
     * @return the loadedFrom
     */
    public File getLoadedFrom() {
        return loadedFrom;
    }

    /**
     * @param loadedFrom the loadedFrom to set
     */
    public void setLoadedFrom(File loadedFrom) {
        this.loadedFrom = loadedFrom;
    }
    
    
}
