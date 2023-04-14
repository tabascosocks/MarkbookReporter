/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentcurricreport.model;

/**
 * Defines the status of a students grade in a particular curric item and prof strand
 * 
 * ASSESSED: Indicates that some results have been located and a set of those have "ATTEMPTED" status
 * NOT_ASSESSED: This grade has no attached results meaning no assessment items have
 * been set for the current curric item and prof strand
 * NOT_ATTEMPTED: Indicates that there are some results for the student, but they ALL have 
 * status NOT_ATTEMPTED
 * @author School
 */
public enum SCRStudentGradeStatus {
    ASSESSED("ASS"), NOT_ASSESSED("NAS"), NOT_ATTEMPTED("NAT");
    
    private final String s;
    
    SCRStudentGradeStatus(String s){
        this.s = s;
    }
    
    @Override
    public String toString(){
        return s;
    }
}
