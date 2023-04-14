/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter.reportbuilder.studentprogreport;

import java.awt.Color;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;

/**
 *
 * @author School
 */
public class NumeracyChartTheme implements ChartTheme{

    @Override
    public void apply(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();        
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.getRenderer().setSeriesPaint(0, Color.BLACK);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
    }
    
}
