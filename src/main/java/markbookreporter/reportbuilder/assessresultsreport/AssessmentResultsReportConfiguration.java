/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.assessresultsreport;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.model.AssessmentItem;
import markbookreporter.reportbuilder.ReportConfiguration;

/**
 *
 * @author School
 */
public class AssessmentResultsReportConfiguration extends ReportConfiguration{
    protected boolean selectedAssessmentItemsOnly = false;
    protected List<AssessmentItem> selectedAssessmentItems = new LinkedList();

    /**
     * @return the selectedAssessmentItemsOnly
     */
    public boolean isSelectedAssessmentItemsOnly() {
        return selectedAssessmentItemsOnly;
    }

    /**
     * @param selectedAssessmentItemsOnly the selectedAssessmentItemsOnly to set
     */
    public void setSelectedAssessmentItemsOnly(boolean selectedAssessmentItemsOnly) {
        this.selectedAssessmentItemsOnly = selectedAssessmentItemsOnly;
    }

    /**
     * @return the selectedAssessmentItems
     */
    public List<AssessmentItem> getSelectedAssessmentItems() {
        return selectedAssessmentItems;
    }

    /**
     * @param selectedAssessmentItems the selectedAssessmentItems to set
     */
    public void setSelectedAssessmentItems(List<AssessmentItem> selectedAssessmentItems) {
        this.selectedAssessmentItems = selectedAssessmentItems;
    }
    
    
}
