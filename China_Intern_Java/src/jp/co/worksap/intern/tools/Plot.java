package jp.co.worksap.intern.tools;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Plot {

	public Plot() {
		
	}
	
	public void plotLine() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		dataset.addValue(100, "test2", "1");  
		dataset.addValue(150, "test2", "2");  
		dataset.addValue(300, "test2", "3");  
		dataset.addValue(100, "test2", "4");  
		
		JFreeChart chart = ChartFactory.createLineChart(  
			       "line",                    // title  
			       "-x-",                      // x 
			       "-y-",                     // y
			       dataset,                    // data 
			       PlotOrientation.VERTICAL,   // 
			       true,                       // legend  
			       false,                      // tooltip  
			       false                       // url
		);  
		
		ChartFrame chartFrame=new ChartFrame("line-title",chart); 
        chartFrame.pack(); 
        chartFrame.setVisible(true);
	}
	
	public void plotBar() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
	    dataset.addValue(100,"Spring　Security","Jan");  
	    dataset.addValue(200,"jBPM　4","Jan");  
	    dataset.addValue(300,"Ext　JS","Jan");  
	    dataset.addValue(400,"JFreeChart","Jan");  
	
	    JFreeChart chart = ChartFactory.createBarChart("chart", "num", "type", dataset, PlotOrientation.VERTICAL, true, false, false);
	    
	    ChartFrame chartFrame=new ChartFrame("title",chart); 
        chartFrame.pack(); 
        chartFrame.setVisible(true);
	}
	
	public void plotPie() {
		DefaultPieDataset dpd=new DefaultPieDataset(); 
        dpd.setValue("v1", 25);  
        dpd.setValue("v2", 25);
        dpd.setValue("v3", 45);
        dpd.setValue("v4", 10);
        
        JFreeChart chart=ChartFactory.createPieChart("title",dpd,true,true,false); 
        
        
        ChartFrame chartFrame=new ChartFrame("title",chart); 
       
        chartFrame.pack(); 
        chartFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plot pd = new Plot();
		//pd.plotPie();
		//pd.plotBar();
		pd.plotLine();
	}

}
