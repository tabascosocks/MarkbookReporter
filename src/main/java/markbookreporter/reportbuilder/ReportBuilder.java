/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import markbookreporter.model.MarkBook;
import markbookreporter.velocityutils.VelocityProductionHelper;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 *
 * @author General
 */
public abstract class ReportBuilder {
    
    protected final Logger logger = Logger.getLogger(this.getClass().getName());
    
    protected abstract ReportModel buildReportModel(MarkBook markBook, ReportConfiguration config);   
    
    protected abstract String getVelocityTemplateName();
    
    public String generateReport(MarkBook markBook, ReportConfiguration config){
        ReportModel reportModel = buildReportModel(markBook, config);
        
        VelocityContext context = new VelocityContext();
        context.put("reportModel", reportModel);

        return VelocityProductionHelper.mergeTemplate(context, getVelocityTemplateName());
    }
}
