/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;

/**
 *
 * @author General
 */
public class VelocityLogWrapper implements LogChute{
    
    private Logger logger;
    
    public VelocityLogWrapper(Logger logger){
        this.logger = logger;
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
