/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.utils;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import markbookreporterui.Application;

/**
 *
 * @author School
 */
public class ApplicationSingletonLogHandler extends Handler{

    protected SimpleFormatter formatter = new SimpleFormatter();
    
    public ApplicationSingletonLogHandler(){
        
    }
    @Override
    public void publish(LogRecord record) {
        Application.getInstance().appendToApplicationLogBuffer(formatter.format(record));
    }

    @Override
    public void flush() {
        
    }

    @Override
    public void close() throws SecurityException {
       
    }
    
}
