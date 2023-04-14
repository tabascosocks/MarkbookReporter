/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import markbookreporterui.Application;
import markbookreporterui.model.MarkbookConfig;
import markbookreporterui.model.MarkbookConfigSet;
import markbookreporterui.view.ApplicationFrame;
import markbookreporterui.view.MarkbookPanelBean;
import markbookreporterui.view.NewMarkbookDialogBean;
import markbookreporterui.view.enums.ReportType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author School
 */
public class ApplicationFrameController {

    protected ApplicationFrame view;

    public void registerView(ApplicationFrame aThis) {
        view = aThis;
    }

    public void addNewMarkbook(NewMarkbookDialogBean element) {
        MarkbookConfig markbookConfig = new MarkbookConfig();
        markbookConfig.setMarkBookFile(element.getMarkBookFile());
        markbookConfig.setMarkBookTitle(element.getMarkBookTitle());
        markbookConfig.setCurriculumTitle(element.getCurriculumTitle());
        //the rest can be left to the class defaults
        //bind this markbookConfig to a MarbookReporter Markbook
        markbookConfig.bindMarkBook();
        //Add to applicaiton collection
        Application.getInstance().getMarkbookSet().getMarkbookConfigs().add(markbookConfig);
        //Dock in a new panel
        view.addMarkbookPanel(markbookConfig);
    }

    public void removeMarkbook(String markBookTitle) {
        MarkbookConfig toRemove = null;
        //find the markbook to remove
        for (MarkbookConfig mb : Application.getInstance().getMarkbookSet().getMarkbookConfigs()) {
            if (mb.getMarkBookTitle().equals(markBookTitle)) {
                toRemove = mb;
            }
        }
        if (toRemove == null) {
            return;
        }
        //pull it out of the global collection
        Application.getInstance().getMarkbookSet().getMarkbookConfigs().remove(toRemove);
    }

    public void exit() {
        System.exit(0);
    }

    public boolean loadMarkbookConfigSetFromFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }

        MarkbookConfigSet configSet = null;
        ObjectInputStream in = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            configSet = (MarkbookConfigSet) in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ApplicationFrameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApplicationFrameController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ApplicationFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (configSet == null) {
            return false;
        }
        //set loaded from file and title
        configSet.setLoadedFrom(file);
        configSet.setTitle(FilenameUtils.getBaseName(file.getName()));
        //push the rest of the new config set to the UI
        loadMarkbookConfigSet(configSet);
        return true;
    }
    
    public boolean saveAs(File selectedFile) {
        return saveMarkbookConfigSetToFile(selectedFile);
    }

    public boolean save() {
        return saveMarkbookConfigSetToFile(Application.getInstance().getMarkbookSet().getLoadedFrom());
    }

    /**
     * Get all the data that is modifiable by the user and pushes it from the UI
     * beans back to the model
     */
    private void synchMarkbookConfigsToModel() {
        MarkbookConfigSet markbookConfigSet = Application.getInstance().getMarkbookSet();
        for (MarkbookConfig markbookConfig : markbookConfigSet.getMarkbookConfigs()) {
            MarkbookPanelBean mpBean = view.getBeanForMarkbookPanelWithTitle(markbookConfig.getMarkBookTitle());
            markbookConfig.setAsrSelectedAssessmentItemNames(mpBean.getAsrSelectedAssessmentItemNames());
            markbookConfig.setAsrSelectedAssessmentItemsOnly(mpBean.isAsrSelectedAssessmentItemsOnly());
            markbookConfig.setCarFullProficiencyStrands(mpBean.isCarFullProficiencyStrands());
            markbookConfig.setCarPushUpChildCurriculumResults(mpBean.isCarPushUpChildCurriculumResults());
            markbookConfig.setCarUseFullCurriculumItems(mpBean.isCarUseFullCurriculumItems());
            markbookConfig.setSarSelectedStudentsOnly(mpBean.isSarSelectedStudentsOnly());
            markbookConfig.setSarSelectedStudentNames(mpBean.getSarSelectedStudentNames());
            markbookConfig.setScrAggregationMethod(mpBean.getScrAggregationMethod());
            markbookConfig.setScrFullProficiencyStrands(mpBean.isScrFullProficiencyStrands());
            markbookConfig.setScrPushUpChildCurriculumResults(mpBean.isScrPushUpChildCurriculumResults());
            markbookConfig.setScrUseFullCurriculumItems(mpBean.isScrUseFullCurriculumItems());
            markbookConfig.setScrAggregateNotAttempted(mpBean.isScrAggregateNotAttempted());
            markbookConfig.setScrSelectedStudentsOnly(mpBean.isScrSelectedStudentsOnly());
            markbookConfig.setScrSelectedStudentNames(mpBean.getScrSelectedStudentNames());
            markbookConfig.setScrSelectedAssessItems(mpBean.getScrSelectedAssessItems());
            markbookConfig.setScrSelectedAssessItemsOnly(mpBean.isScrSelectedAssessItemsOnly());
            markbookConfig.setSprSelectedStudentsOnly(mpBean.isSprSelectedStudentsOnly());
            markbookConfig.setSprSelectedStudentNames(mpBean.getSprSelectedStudentNames());
        }
    }

    private boolean saveMarkbookConfigSetToFile(File file) {
        if (Application.getInstance().getMarkbookSet() == null) {
            return false;
        }
        //Propagate new filename and file to MarkbookSet
        MarkbookConfigSet markbookConfigSet = Application.getInstance().getMarkbookSet();
        markbookConfigSet.setTitle(FilenameUtils.getBaseName(file.getName()));
        markbookConfigSet.setLoadedFrom(file);
        //apply view state to model
        synchMarkbookConfigsToModel();
        //Write the file
        ObjectOutputStream out = null;
        try {
            if (!FilenameUtils.getExtension(file.getCanonicalPath()).equalsIgnoreCase("mbc")) {
                file = new File(file.getPath() + ".mbc");
            }
            FileOutputStream fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(markbookConfigSet);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationFrameController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ApplicationFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //tell the view that there is a file backing
        view.getBean().setSaveFileIsBound(true);
        view.getBean().setTitle(markbookConfigSet.getTitle());
        return true;
    }

    /**
     * This method uses the given MarkbookConfigSet to load the UI from the set,
     * populating new panels and the fields within, setting this configset ass
     * the application-global
     *
     * @param configSet
     */
    private void loadMarkbookConfigSet(MarkbookConfigSet configSet) {
        if (configSet == null) {
            return;
        }
        //remove existing panels
        view.clearMarkbookPanels();
        //applcy global
        Application.getInstance().setMarkbookSet(configSet);
        //repopulate the application panel
        view.getBean().setSaveFileIsBound(configSet.getLoadedFrom() != null);
        view.getBean().setTitle(configSet.getTitle());
        //repopulate markbook configs
        for (MarkbookConfig conf : configSet.getMarkbookConfigs()) {
            //bind the markbook 
            conf.bindMarkBook();
            //
            view.addMarkbookPanel(conf);
        }
    }

    public void createAndLoadNewConfigSet() {
        MarkbookConfigSet configSet = new MarkbookConfigSet();
        loadMarkbookConfigSet(configSet);
    }

    public void refreshReport(String markBookTitle, ReportType reportType) {
        //find the markbook config for the given title
        MarkbookConfig selectedConfig = null;
        for (MarkbookConfig config : Application.getInstance().getMarkbookSet().getMarkbookConfigs()) {
            if (config.getMarkBookTitle().equals(markBookTitle)) {
                selectedConfig = config;
                break;
            }
        }
        if (selectedConfig == null) {
            return;
        }
        //get the config data in
        synchMarkbookConfigsToModel();
        //fetch the report html
        String html = selectedConfig.generateReport(reportType);
        //bind result to the UI
        view.getBean().setReportHtml(html);
    }
    
    /**
     * 
     */
    public void sendReportToBrowser(){
        if(StringUtils.isBlank(view.getBean().getReportHtml())){
            return;
        }
        
        if(Desktop.isDesktopSupported()){
            try {
                //push the latest html to a temporary file
                File tmp = File.createTempFile("MarkbookReporter", ".html");
                FileUtils.write(tmp, view.getBean().getReportHtml());
                Desktop.getDesktop().browse(tmp.toURI());
            } catch (IOException ex) {
                Logger.getLogger(ApplicationFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        else {
          Logger.getLogger(ApplicationFrameController.class.getName()).log(Level.WARNING, "Desktop is not supported by this host.");
        }        
    }

}
