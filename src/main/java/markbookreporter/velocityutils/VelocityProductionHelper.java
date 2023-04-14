/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.velocityutils;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import markbookreporter.MarkbookReporter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author School
 */
public class VelocityProductionHelper {
    private final static Logger logger = Logger.getLogger(VelocityProductionHelper.class.getName());
    
    /**
     * Accepts a prepared velocity context and a template name and attempts to merge them
     * using the configured classloader prepared velocity engine in the Application Singleton
     * @param ctx
     * @param templateName
     * @return The merged template, or null if there was an error
     */
    public static String mergeTemplate(VelocityContext ctx, String templateName){
        try{
            VelocityEngine ve = MarkbookReporter.getInstance().getVelocityEngine();
            Template template = ve.getTemplate(templateName);
            StringWriter sw = new StringWriter();
            template.merge(ctx, sw);
            return sw.toString();
        }catch(Exception ex){
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
