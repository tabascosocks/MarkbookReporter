/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.assessresultsreport.model;

import java.util.LinkedList;
import java.util.List;
import markbookreporter.reportbuilder.ReportModel;

/**
 *
 * @author School
 */
public class ASRReportModel implements ReportModel{
    private List<ASRAssessmentItem> asrAssessmentItems = new LinkedList();

    /**
     * @return the asrAssessmentItems
     */
    public List<ASRAssessmentItem> getAsrAssessmentItems() {
        return asrAssessmentItems;
    }

    /**
     * @param asrAssessmentItems the asrAssessmentItems to set
     */
    public void setAsrAssessmentItems(List<ASRAssessmentItem> asrAssessmentItems) {
        this.asrAssessmentItems = asrAssessmentItems;
    }
}
