/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.utils;

/**
 *
 * @author General
 */
public class DialogResult <T>{
    
    private T element;
    private ResultType resultType;

    /**
     * @return the element
     */
    public T getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * @return the resultType
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public enum ResultType {
        OK, CANCEL;
    }
    
}
