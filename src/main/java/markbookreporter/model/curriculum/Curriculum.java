/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model.curriculum;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author General
 */
public class Curriculum {
    
    protected String name;
    
    protected List<CurriculumItem> curriculumItems = new LinkedList();
    protected List<ProficiencyStrand> proficiencyStrands = new LinkedList();
    protected List<ContentStrand> contentStrands = new LinkedList();
    
    /**
     * Sorts the given list of curriculum items against the full list 
     * kept in this object. It is assumed the given list is a subset
     * of the full curriculum items
     * @param subset 
     */
    public void sortCurriculumItemsAgaintCurriculum(List<CurriculumItem> subset){
        Collections.sort(subset, new Comparator<CurriculumItem>(){
            @Override
            public int compare(CurriculumItem o1, CurriculumItem o2) {
                int o1Index = curriculumItems.indexOf(o1);
                int o2Index = curriculumItems.indexOf(o2);
                return (o1Index < o2Index) ? -1 : (o1Index == o2Index) ? 0 : 1;
            }
        });
    }
    
    /**
     * Sorts the given list of curriculum items against the full list 
     * kept in this object. It is assumed the given list is a subset
     * of the full curriculum items
     * @param subset 
     */
    public void sortProficiencyStrandsAgaintCurriculum(List<ProficiencyStrand> subset){
        Collections.sort(subset, new Comparator<ProficiencyStrand>(){
            @Override
            public int compare(ProficiencyStrand o1, ProficiencyStrand o2) {
                int o1Index = proficiencyStrands.indexOf(o1);
                int o2Index = proficiencyStrands.indexOf(o2);
                return (o1Index < o2Index) ? -1 : (o1Index == o2Index) ? 0 : 1;
            }
        });
    }    
    
    public ProficiencyStrand getProficiencyStrandByCode(String code){
        for(ProficiencyStrand currItem : proficiencyStrands){
            if(currItem.getCode().equalsIgnoreCase(code)){
                return currItem;
            }
        }
        return null;
    }
    
    public ContentStrand getContentStrandByCode(String code){
        for(ContentStrand currItem : contentStrands){
            if(currItem.getCode().equalsIgnoreCase(code)){
                return currItem;
            }
        }
        return null;
    }
    
    public CurriculumItem getCurriculumItemByCode(String code){
        for(CurriculumItem currItem : curriculumItems){
            if(currItem.getCode().equalsIgnoreCase(code)){
                return currItem;
            }
        }
        return null;
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
     * @return the curriculumItems
     */
    public List<CurriculumItem> getCurriculumItems() {
        return curriculumItems;
    }

    /**
     * @param curriculumItems the curriculumItems to set
     */
    public void setCurriculumItems(List<CurriculumItem> curriculumItems) {
        this.curriculumItems = curriculumItems;
    }

    /**
     * @return the proficiencyStrands
     */
    public List<ProficiencyStrand> getProficiencyStrands() {
        return proficiencyStrands;
    }

    /**
     * @param proficiencyStrands the proficiencyStrands to set
     */
    public void setProficiencyStrands(List<ProficiencyStrand> proficiencyStrands) {
        this.proficiencyStrands = proficiencyStrands;
    }

    /**
     * @return the contentStrands
     */
    public List<ContentStrand> getContentStrands() {
        return contentStrands;
    }

    /**
     * @param contentStrands the contentStrands to set
     */
    public void setContentStrands(List<ContentStrand> contentStrands) {
        this.contentStrands = contentStrands;
    }
}
