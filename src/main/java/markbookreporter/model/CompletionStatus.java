/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model;

/**
 * This enum defines the completion status of a particular assessment item section, 
 * the data is indicated from the markbook - the defualt is 'attempted', an 'n' code
 * means the student did not attempt this section, and an 'r' code indicates the teachers
 * decision to not assess this student on this section.
 * 
 * @author School
 */
public enum CompletionStatus {
    ATTEMPTED("A"), NOT_ATTEMPTED("N"), REMOVED_FROM_ASSESSMENT("R");
    
    private final String s;
    
    CompletionStatus(String s){
        this.s = s;
    }
    
    @Override
    public String toString(){
        return s;
    }
}
