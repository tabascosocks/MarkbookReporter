/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author General
 */
public class AssessmentItem {
    
    private String name;
    /**
     * The 'type' of assessment - read from the markbook and useful in grouping
     * assessment items in reports - mainly for display purposes only
     */
    private String categoryTitle = "";
    private Calendar dateSet;
    private Calendar dateDue;
    
    private List<AssessmentItemSection> assessmentItemSections = new LinkedList();

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
     * @return the dateSet
     */
    public Calendar getDateSet() {
        return dateSet;
    }

    /**
     * @param dateSet the dateSet to set
     */
    public void setDateSet(Calendar dateSet) {
        this.dateSet = dateSet;
    }

    /**
     * @return the dateDue
     */
    public Calendar getDateDue() {
        return dateDue;
    }

    /**
     * @param dateDue the dateDue to set
     */
    public void setDateDue(Calendar dateDue) {
        this.dateDue = dateDue;
    }

    /**
     * @return the assessmentItemSections
     */
    public List<AssessmentItemSection> getAssessmentItemSections() {
        return assessmentItemSections;
    }

    /**
     * @param assessmentItemSections the assessmentItemSections to set
     */
    public void setAssessmentItemSections(List<AssessmentItemSection> assessmentItemSections) {
        this.assessmentItemSections = assessmentItemSections;
    }

    /**
     * @return the categoryTitle
     */
    public String getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * @param categoryTitle the categoryTitle to set
     */
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

}
