/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import markbookreporter.MarkbookReporter;
import markbookreporter.model.curriculum.Curriculum;
import markbookreporterui.model.MarkbookConfigSet;
import markbookreporterui.utils.ApplicationSingletonLogHandler;

/**
 *
 * @author School
 */
public class Application {
    
    protected MarkbookConfigSet markbookSet = new MarkbookConfigSet();
    protected List<String> curriculumTitles = new LinkedList();
    /**
     * The string which will contain all the applicationLogBuffer messages generated, submitted
 via the custom applicationLogBuffer handler
     */
    protected StringBuffer applicationLogBuffer = new StringBuffer();
    protected Properties properties;
  
    private Application() {
        //load the curriculum titles
        for(Curriculum curr : MarkbookReporter.getInstance().getCurriculums()){
            curriculumTitles.add(curr.getName());
        }
        Logger.getLogger("").addHandler(new ApplicationSingletonLogHandler());
        
        //Load the package-internal properties file
        properties = new Properties();
        InputStream input = null;
        try {
            input = Application.class.getResourceAsStream("Application.properties");
            // load a properties file
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "", e);
                }
            }
        }
    }
    
    public static Application getInstance() {
        return ApplicationHolder.INSTANCE;
    }

    /**
     * @return the markbookSet
     */
    public MarkbookConfigSet getMarkbookSet() {
        return markbookSet;
    }

    /**
     * @param markbookSet the markbookSet to set
     */
    public void setMarkbookSet(MarkbookConfigSet markbookSet) {
        this.markbookSet = markbookSet;
    }

    /**
     * @return the curriculumTitles
     */
    public List<String> getCurriculumTitles() {
        return curriculumTitles;
    }

    /**
     * @param curriculumTitles the curriculumTitles to set
     */
    public void setCurriculumTitles(List<String> curriculumTitles) {
        this.curriculumTitles = curriculumTitles;
    }

    /**
     * @return the applicationLogBuffer
     */
    public StringBuffer getApplicationLogBuffer() {
        return applicationLogBuffer;
    }

    /**
     * @param applicationLogBuffer the applicationLogBuffer to set
     */
    public void setApplicationLogBuffer(StringBuffer applicationLogBuffer) {
        this.applicationLogBuffer = applicationLogBuffer;
    }
    
    public void appendToApplicationLogBuffer(String s){
        this.applicationLogBuffer.append(s);
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private static class ApplicationHolder {

        private static final Application INSTANCE = new Application();
    }
}
