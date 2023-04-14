/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport.model;

import markbookreporter.reportbuilder.studentcurricreport.StudentCurriculumReportBuilder;

/**
 *
 * @author General
 */
public class SCRCurriculumItem {

    protected String contentStrand;
    protected String name;
    protected String description;
    protected boolean childCurriculumItem;

    /**
     * Wether or not to disaply the contens of this curriculum as a minor item
     * on the vm template, depeding on configuration and wehter this item is a
     * child item or not. (ie the curriculum item that it was created from had a
     * parent curriculum item)
     *
     * @return
     */
    public boolean displayAsMinorItem() {
        if (childCurriculumItem) {
            return true;
        }
        return false;
    }

    /**
     * Wether or not to disaply the contens of this curriculum
     * on the vm template, depeding on configuration and wehter this item is a
     * child item or not. (ie the curriculum item that it was created from had a
     * parent curriculum item)
     *
     * @return
     */
    public boolean display() {
        if (StudentCurriculumReportBuilder.scrConfig.isPushUpChildCurriculumResults()
                && childCurriculumItem) {
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
     * @return the childCurriculumItem
     */
    public boolean isChildCurriculumItem() {
        return childCurriculumItem;
    }

    /**
     * @param childCurriculumItem the childCurriculumItem to set
     */
    public void setChildCurriculumItem(boolean childCurriculumItem) {
        this.childCurriculumItem = childCurriculumItem;
    }
}
