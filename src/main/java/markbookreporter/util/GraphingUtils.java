/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import markbookreporter.reportbuilder.studentprogreport.model.SPRStudent;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author School
 */
public class GraphingUtils {

    public static String convertChartToBase64PNGString(JFreeChart chart, int width, int height) {
        String imageString = "";
        BufferedImage image = chart.createBufferedImage(width, height);
        try {
            //convert the buffered image to a base 64 encoded png string
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //
            ImageIO.write(image, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            bos.close();
            
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            //BASE64Encoder encoder = new BASE64Encoder();
            //imageString = encoder.encode(imageBytes);
            
        } catch (IOException ex) {
            Logger.getLogger(SPRStudent.class.getName()).log(Level.WARNING, null, ex);
        }
        return imageString;
    }
}
