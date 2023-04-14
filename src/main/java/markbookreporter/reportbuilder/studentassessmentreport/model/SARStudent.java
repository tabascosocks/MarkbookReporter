/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentassessmentreport.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author General
 */
public class SARStudent {
    protected String name;
    protected List<SARAssessmentItem> assessmentItems = new LinkedList();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the assessmentItems
     */
    public List<SARAssessmentItem> getAssessmentItems() {
        return assessmentItems;
    }

    /**
     * @param assessmentItems the assessmentItems to set
     */
    public void setAssessmentItems(List<SARAssessmentItem> assessmentItems) {
        this.assessmentItems = assessmentItems;
    }
    
    
    
}
