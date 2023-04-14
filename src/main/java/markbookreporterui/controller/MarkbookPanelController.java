/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.controller;

import markbookreporterui.Application;
import markbookreporterui.model.MarkbookConfig;
import markbookreporterui.view.MarkbookPanel;

/**
 *
 * @author School
 */
public class MarkbookPanelController {

    protected MarkbookPanel view;
    
    public void registerView(MarkbookPanel aThis) {
       view = aThis;
    }
    
    /**
     * Called by the view when 'refresh markbook data' option is selected, 
     * get the markbook config object to make another call to buildMarkbook() within
     * the MarkbookReporter package. Its intended to allow hot changes to the
     * underlying xls file and allowing the user to update without
     * having to reload the markbook set
     * 
     * @param markbookTitle
     * @return 
     */
    public void refreshMarkbookData(String markbookTitle){
        for (MarkbookConfig mb : Application.getInstance().getMarkbookSet().getMarkbookConfigs()) {
            if (mb.getMarkBookTitle().equals(markbookTitle)) {
                mb.bindMarkBook();
            }
        }        
    }

    
}
