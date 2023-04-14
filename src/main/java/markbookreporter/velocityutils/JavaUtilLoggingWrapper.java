/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.velocityutils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;

/**
 *
 * @author School
 */
public class JavaUtilLoggingWrapper implements LogChute{
    
    private final static Logger logger = Logger.getLogger(JavaUtilLoggingWrapper.class.getName());
    
    public JavaUtilLoggingWrapper(){
    }

    @Override
    public void init(RuntimeServices rs) throws Exception {
        //do nothing
    }

    @Override
    public void log(int level, String message) {
        logger.log(this.getLogLevelForLogChuteLevel(level), message);
    }

    @Override
    public void log(int level, String message, Throwable t) {
        logger.log(this.getLogLevelForLogChuteLevel(level), message, t);
    }

    @Override
    public boolean isLevelEnabled(int level) {
        return true;
    }
    
    private Level getLogLevelForLogChuteLevel(int level){
        switch(level){
            case -1: 
                return Level.FINER;
            case 0:
                return Level.FINE;
            case 1:
                return Level.INFO;
            case 2:
                return Level.WARNING;
            case 3:
                return Level.SEVERE;
            default:
                return Level.ALL;
        }
    }
        
}
