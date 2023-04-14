/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import markbookreporter.model.curriculum.Curriculum;
import markbookreporter.model.curriculum.CurriculumItem;
import markbookreporter.model.curriculum.ProficiencyStrand;

/**
 *
 * @author General
 */
public class MarkBook {
    
    protected String name;
    protected List<AssessmentItem> assessmentItems = new LinkedList();
    protected List<Student> students = new LinkedList();
    /**
     * The curriculum to which this markbook's assessment items are assigned
     */
    protected Curriculum curriculum;
    
    public Student getOrCreateStudent(String firstName, String lastName){
        //try to locate the student in the list        
        for(Student student : students){
            if(student.getFirstName().equalsIgnoreCase(firstName) && student.getLastName().equalsIgnoreCase(lastName)){
                //found the student
                return student;
            }
        }
        //didnt find student, create on and add to the list
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        students.add(student);
        return student;
    }
    
    /**
     * 
     * @return All the curriculum items referenced by any of the assessment item 
     * sections in the markbook
     */
    public List<CurriculumItem> getReferencedCurriculumItems(){
        Set<CurriculumItem> items = new HashSet();
        for(AssessmentItem assessmentItem : assessmentItems){
            for(AssessmentItemSection assessmentitemSection : assessmentItem.getAssessmentItemSections()){
                items.addAll(assessmentitemSection.getCurriculumItems());
            }
        }
        //convert to a list
        List<CurriculumItem> result = new LinkedList();
        result.addAll(items);
        return result;
    }
    
    /**
     * 
     * @return All the curriculum items referenced by any of the assessment item 
     * sections in the markbook
     */
    public List<ProficiencyStrand> getReferencedProficiencyStrands(){
        Set<ProficiencyStrand> items = new HashSet();
        for(AssessmentItem assessmentItem : assessmentItems){
            for(AssessmentItemSection assessmentitemSection : assessmentItem.getAssessmentItemSections()){
                items.addAll(assessmentitemSection.getProficiencyStrands());
            }
        }
        //convert to a list
        List<ProficiencyStrand> result = new LinkedList();
        result.addAll(items);
        return result;
    }    
    /**
     * @return the assessmentItems
     */
    public List<AssessmentItem> getAssessmentItems() {
        return assessmentItems;
    }

    /**
     * @param assessmentItems the assessmentItems to set
     */
    public void setAssessmentItems(List<AssessmentItem> assessmentItems) {
        this.assessmentItems = assessmentItems;
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
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * The curriculum to which this markbook's assessment items are assigned
     * @return the curriculum
     */
    public Curriculum getCurriculum() {
        return curriculum;
    }

    /**
     * The curriculum to which this markbook's assessment items are assigned
     * @param curriculum the curriculum to set
     */
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
