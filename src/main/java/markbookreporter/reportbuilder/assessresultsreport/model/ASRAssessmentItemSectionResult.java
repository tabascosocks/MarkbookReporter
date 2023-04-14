/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.assessresultsreport.model;

import markbookreporter.model.AssessmentItemSectionResult;
import markbookreporter.model.CompletionStatus;
import markbookreporter.reportbuilder.studentcurricreport.model.SCRStudentGradeStatus;

/**
 *
 * @author School
 */
public class ASRAssessmentItemSectionResult {
    
    private CompletionStatus completionStatus;
    private double marksAttained;
    private ASRAssessmentItemSection asrAssessmentItemSection;
    
    private String[] colorGradients = new String[]{"#17CC00","#1BCC00","#1FCD00","#24CE00","#28CE00","#2CCF00","#31D000","#35D100","#39D100","#3ED200","#42D300","#46D300","#4BD400","#4FD500","#53D600","#58D600","#5CD700","#60D800","#65D800","#69D900","#6DDA00","#72DB00","#76DB00","#7ADC00","#7FDD00","#83DD00","#88DE00","#8CDF00","#90E000","#95E000","#99E100","#9DE200","#A2E200","#A6E300","#AAE400","#AFE500","#B3E500","#B7E600","#BCE700","#C0E700","#C4E800","#C9E900","#CDEA00","#D1EA00","#D6EB00","#DAEC00","#DEEC00","#E3ED00","#E7EE00","#ECEF00","#ECEF01","#ECEA00","#ECE500","#ECE000","#ECDC00","#ECD700","#ECD200","#ECCE00","#ECC900","#ECC400","#ECC000","#ECBB00","#ECB600","#EDB200","#EDAD00","#EDA800","#EDA400","#ED9F00","#ED9A00","#ED9600","#ED9100","#ED8C00","#ED8800","#ED8300","#ED7E00","#EE7A00","#EE7500","#EE7000","#EE6C00","#EE6700","#EE6200","#EE5E00","#EE5900","#EE5400","#EE5000","#EE4B00","#EE4600","#EF4200","#EF3D00","#EF3800","#EF3400","#EF2F00","#EF2A00","#EF2600","#EF2100","#EF1C00","#EF1800","#EF1300","#EF0E00","#F00A00","#F00A00"};

    public ASRAssessmentItemSectionResult(AssessmentItemSectionResult result) {
        completionStatus = result.getCompletionStatus();
        marksAttained = result.getMarksAttained();
    }

    public int getPercentAttained(){
        if(getAsrAssessmentItemSection().getTotalMarks() == 0) return 0;
        return (int)((getMarksAttained()/getAsrAssessmentItemSection().getTotalMarks()) * 100);        
    }
    
    public String getColorValue(){
        
        if(getCompletionStatus() == CompletionStatus.REMOVED_FROM_ASSESSMENT){
            //white
            return "#FFFFFF";
        }
        int index = 100 - getPercentAttained();
        if(index > 100) index = 100;
        if(index < 0) index = 0;
        return getColorGradients()[index];
    }

    /**
     * @return the completionStatus
     */
    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    /**
     * @param completionStatus the completionStatus to set
     */
    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    /**
     * @return the marksAttained
     */
    public double getMarksAttained() {
        return marksAttained;
    }

    /**
     * @param marksAttained the marksAttained to set
     */
    public void setMarksAttained(double marksAttained) {
        this.marksAttained = marksAttained;
    }

    /**
     * @return the asrAssessmentItemSection
     */
    public ASRAssessmentItemSection getAsrAssessmentItemSection() {
        return asrAssessmentItemSection;
    }

    /**
     * @param asrAssessmentItemSection the asrAssessmentItemSection to set
     */
    public void setAsrAssessmentItemSection(ASRAssessmentItemSection asrAssessmentItemSection) {
        this.asrAssessmentItemSection = asrAssessmentItemSection;
    }

    /**
     * @return the colorGradients
     */
    public String[] getColorGradients() {
        return colorGradients;
    }

    /**
     * @param colorGradients the colorGradients to set
     */
    public void setColorGradients(String[] colorGradients) {
        this.colorGradients = colorGradients;
    }
    

}
