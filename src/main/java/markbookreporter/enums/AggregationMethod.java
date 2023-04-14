/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.enums;

/**
 *
 * @author School
 */
public enum AggregationMethod {
    ACCUMULATED("Accumulated"), 
    TAKE_HIGHEST("Take Highest"), 
    /**
     * Accumulate within curriculum item as per method ACCUMULTED, but
     * if there are > 1 assess item results with section.taskArchetype
     * the same, only use the result with the highest grade
     * of that subset (if the archetype is blank, assume its unique)
     */
    COMPARE_AND_ACCUMULATE("Compare & Accumulate");
    
    private final String name;
    
    private AggregationMethod(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
