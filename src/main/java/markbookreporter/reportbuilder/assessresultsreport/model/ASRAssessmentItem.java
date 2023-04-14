/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.assessresultsreport.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import markbookreporter.model.Student;

/**
 *
 * @author School
 */
public class ASRAssessmentItem {
    private String name;
    private List<ASRAssessmentItemSection> asrAssessmentItemSections = new LinkedList();
    private List<ASRStudent> asrStudents = new LinkedList();

    public ASRStudent getOrCreateStudent(Student student) {
        for(ASRStudent asrStudent : asrStudents){
            if(student.getFirstName() == asrStudent.getFirstName()
                    && student.getLastName() == asrStudent.getLastName()){
                return asrStudent;
            }
        }
        ASRStudent asrStudent = new ASRStudent(student);
        asrStudents.add(asrStudent);
        return asrStudent;
        
    }
    
    public void sortStudentsByGrade() {
        final ASRAssessmentItem item = this;
        Collections.sort(asrStudents, new Comparator<ASRStudent>(){
            @Override
            public int compare(ASRStudent o1, ASRStudent o2) {
                int o1Result = o1.getPercentAttainedForAssessmentItem(item);
                int o2Result = o2.getPercentAttainedForAssessmentItem(item);
                if(o1Result < o2Result) return -1;
                else if(o1Result > o2Result) return 1;
                else return 0;
            }            
        });
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
     * @return the asrAssessmentItemSections
     */
    public List<ASRAssessmentItemSection> getAsrAssessmentItemSections() {
        return asrAssessmentItemSections;
    }

    /**
     * @param asrAssessmentItemSections the asrAssessmentItemSections to set
     */
    public void setAsrAssessmentItemSections(List<ASRAssessmentItemSection> asrAssessmentItemSections) {
        this.asrAssessmentItemSections = asrAssessmentItemSections;
    }

    /**
     * @return the asrStudents
     */
    public List<ASRStudent> getAsrStudents() {
        return asrStudents;
    }

    /**
     * @param asrStudents the asrStudents to set
     */
    public void setAsrStudents(List<ASRStudent> asrStudents) {
        this.asrStudents = asrStudents;
    }




    
}
