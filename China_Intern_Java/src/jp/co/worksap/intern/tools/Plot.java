package jp.co.worksap.intern.tools;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A tool to plot various figures
 * @author intern Kyle
 *
 */
public class Plot {

	public Plot() {
		
	}
	
	/**
	 * to plot line figure
	 * @param title
	 * @param xName
	 * @param yName
	 * @param product
	 * @param x_data
	 * @param data
	 */
	public void plotLine(String title, String xName, String yName, List<String> product, List<String> x_data, List<List<Integer>> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		
		for(int i=0; i<product.size(); i++) {
			List<Integer> list = data.get(i);
			for(int j=0; j<list.size(); j++) {
				dataset.addValue(list.get(j), product.get(i), x_data.get(j));
			}
			
		}
		
		JFreeChart chart = ChartFactory.createLineChart(  
			       title,                    // title  
			       xName,                      // x 
			       yName,                     // y
			       dataset,                    // data 
			       PlotOrientation.VERTICAL,   // 
			       true,                       // legend  
			       false,                      // tooltip  
			       false                       // url
		);  
		
		ChartFrame chartFrame=new ChartFrame(title,chart); 
        chartFrame.pack(); 
        chartFrame.setVisible(true);
	}
	
	/**
	 * to plot bar figure
	 * @param product
	 * @param data
	 * @param title
	 * @param xName
	 * @param yName
	 */
	public void plotBar(List<String> product, List<Double> data, String title, String xName, String yName) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		for(int i=0; i<product.size(); i++) {
			dataset.addValue(data.get(i),product.get(i),"");  
		}
	    
	    JFreeChart chart = ChartFactory.createBarChart(title, xName, yName, dataset, PlotOrientation.VERTICAL, true, false, false);
	    
	    ChartFrame chartFrame=new ChartFrame(title, chart); 
        chartFrame.pack(); 
        chartFrame.setVisible(true);
	}
	
	/**
	 * to plot pie figure
	 * @param product
	 * @param data
	 * @param title
	 */
	public void plotPie(List<String> product, List<Integer> data, String title) {
		DefaultPieDataset dpd=new DefaultPieDataset();
		for(int i=0; i<product.size(); i++) {
			dpd.setValue(product.get(i), data.get(i));  
		}
      
        JFreeChart chart=ChartFactory.createPieChart(title,dpd,true,true,false); 
 
        ChartFrame chartFrame=new ChartFrame(title,chart);      
        chartFrame.pack(); 
        chartFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plot pd = new Plot();
		
		//System.out.println("#: " + time);
		//pd.plotPie(Arrays.asList("1", "2", "3"), Arrays.asList(100, 200, 300), "occupation");
		//pd.plotBar("12234");
		//pd.plotLine();
	}

}
