/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.model.curriculum;

/**
 *
 * @author General
 */
public class CurriculumItem {
    
    protected String code;    
    protected String description;

    protected CurriculumItem parent;
    protected ContentStrand contentStrand;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the parent
     */
    public CurriculumItem getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(CurriculumItem parent) {
        this.parent = parent;
    }

    /**
     * @return the contentStrand
     */
    public ContentStrand getContentStrand() {
        return contentStrand;
    }

    /**
     * @param contentStrand the contentStrand to set
     */
    public void setContentStrand(ContentStrand contentStrand) {
        this.contentStrand = contentStrand;
    }

        
}
