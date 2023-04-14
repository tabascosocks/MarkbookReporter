/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.curricassessreport;

import markbookreporter.reportbuilder.ReportConfiguration;

/**
 *
 * @author School
 */
public class CurriculumAssessmentReportConfiguration extends ReportConfiguration{
    
    /**
     * Wether the results from a child curriculum element
     * (ie an elaboration item) should appear in the 
     * parent curriculum item as well
     */
    protected boolean pushUpChildCurriculumResults;
    /**
     * Whether or not all the curriculum items should be listed in this report, even if
     * no assessment items have linked to it
     */
    protected boolean useFullCurriculumItems;
    /**
     * Whether or not all the proficiency strands should be listed in this report, even if
     * no assessment items have linked to it
     */
    protected boolean useFullProficiencyStrands;

    /**
     * Wether the results from a child curriculum element
     * (ie an elaboration item) should appear in the
     * parent curriculum item as well
     * @return the pushUpChildCurriculumResults
     */
    public boolean isPushUpChildCurriculumResults() {
        return pushUpChildCurriculumResults;
    }

    /**
     * Wether the results from a child curriculum element
     * (ie an elaboration item) should appear in the
     * parent curriculum item as well
     * @param pushUpChildCurriculumResults the pushUpChildCurriculumResults to set
     */
    public void setPushUpChildCurriculumResults(boolean pushUpChildCurriculumResults) {
        this.pushUpChildCurriculumResults = pushUpChildCurriculumResults;
    }

    /**
     * Whether or not all the curriculum items should be listed in this report, even if
     * no assessment items have linked to it
     * @return the useFullCurriculumItems
     */
    public boolean isUseFullCurriculumItems() {
        return useFullCurriculumItems;
    }

    /**
     * Whether or not all the curriculum items should be listed in this report, even if
     * no assessment items have linked to it
     * @param useFullCurriculumItems the useFullCurriculumItems to set
     */
    public void setUseFullCurriculumItems(boolean useFullCurriculumItems) {
        this.useFullCurriculumItems = useFullCurriculumItems;
    }

    /**
     * Whether or not all the proficiency strands should be listed in this report, even if
     * no assessment items have linked to it
     * @return the useFullProficiencyStrands
     */
    public boolean isUseFullProficiencyStrands() {
        return useFullProficiencyStrands;
    }

    /**
     * Whether or not all the proficiency strands should be listed in this report, even if
     * no assessment items have linked to it
     * @param useFullProficiencyStrands the useFullProficiencyStrands to set
     */
    public void setUseFullProficiencyStrands(boolean useFullProficiencyStrands) {
        this.useFullProficiencyStrands = useFullProficiencyStrands;
    }
}
