/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.curricassessreport.model;

import markbookreporter.reportbuilder.curricassessreport.CurriculumAssessmentReportBuilder;

/**
 *
 * @author General
 */
public class CARCurriculumItem {
        
    protected String contentStrand;
    protected String name;
    protected String description;
    /**
     * Whether or not the CARCurriculum Item has a parent curriculum item
     * for the purpose of display configuration
     */
    protected boolean childCurriculumItem;

    /**
     * Dont display this item if push up child curriculum elements is true
     * and its a minor curriculum item
     * @return 
     */
    public boolean display(){
        if (CurriculumAssessmentReportBuilder.carConfig.isPushUpChildCurriculumResults()
                && isChildCurriculumItem()) {
            return false;
        }
        return true;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
     * @return the contentStrand
     */
    public String getContentStrand() {
        return contentStrand;
    }

    /**
     * @param contentStrand the contentStrand to set
     */
    public void setContentStrand(String contentStrand) {
        this.contentStrand = contentStrand;
    }

    /**
     * Whether or not the CARCurriculum Item has a parent curriculum item
     * for the purpose of display configuration
     * @return the childCurriculumItem
     */
    public boolean isChildCurriculumItem() {
        return childCurriculumItem;
    }

    /**
     * Whether or not the CARCurriculum Item has a parent curriculum item
     * for the purpose of display configuration
     * @param childCurriculumItem the childCurriculumItem to set
     */
    public void setChildCurriculumItem(boolean childCurriculumItem) {
        this.childCurriculumItem = childCurriculumItem;
    }


}
