package mms2.leasing.session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author PhiLong
 */
public class CreateChart extends JFrame {
    //@PersistenceContext
    //private EntityManager em;
    
    public CreateChart(String appTitle, String chartTitle){
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel  = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500,300));
        setContentPane(chartPanel);
    }
    
    private PieDataset createDataset(){
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("F&B", 25);
        result.setValue("Retail", 25);
        result.setValue("Entertainment", 25);
        result.setValue("Event", 25);
        return result;
    }
    private JFreeChart createChart(PieDataset dataset, String chartTitle){
        JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset,true,true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
}
