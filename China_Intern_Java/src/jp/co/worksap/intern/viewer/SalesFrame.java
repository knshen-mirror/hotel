package jp.co.worksap.intern.viewer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jp.co.worksap.intern.controller.Report;
import jp.co.worksap.intern.tools.DataCollector;
import jp.co.worksap.intern.tools.Plot;
import jp.co.worksap.intern.writer.ResultWriterImpl;

public class SalesFrame extends JFrame {

	private JPanel contentPane;
	JLabel lblStartDate = new JLabel("start date:  ");
	
	JLabel lblEndDate = new JLabel("end date:  ");
	
	JLabel lblYear = new JLabel("year");
	
	JComboBox comboBox = new JComboBox();
	
	JLabel lblMonth = new JLabel("month");
	
	JComboBox comboBox_1 = new JComboBox();
	
	JLabel lblDay = new JLabel("day");
	
	JComboBox comboBox_2 = new JComboBox();
	
	JLabel lblYear_1 = new JLabel("year");
	
	JLabel lblMonth_1 = new JLabel("month");
	
	JLabel lblDay_1 = new JLabel("day");
	
	JComboBox comboBox_3 = new JComboBox();
	
	JComboBox comboBox_4 = new JComboBox();
	
	JComboBox comboBox_5 = new JComboBox();
	
	JLabel lblReportType = new JLabel("report type:  ");
	
	JComboBox comboBox_6 = new JComboBox();
	private final JButton btnPlotBar = new JButton("plot bar");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesFrame frame = new SalesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void addYearData(JComboBox jcb) {
		for(int i=2014; i<=2015; i++) {
			jcb.addItem(i);
		}
	}
	
	public void addMonthData(JComboBox jcb) {
		for(int i=1; i<=12; i++) {
			jcb.addItem(i);
		}
	}
	
	public void addDayData(JComboBox jcb) {
		for(int i=1; i<=31; i++) {
			jcb.addItem(i);
		}
	}
	
	public void addReportType(JComboBox jcb) {
		jcb.addItem("sales amount");
		jcb.addItem("water cost");
		jcb.addItem("electricity cost");
		jcb.addItem("benefit amount");
		
	}
	/**
	 * Create the frame.
	 */
	public SalesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		addYearData(comboBox);
		addYearData(comboBox_3);
		addMonthData(comboBox_1);
		addMonthData(comboBox_4);
		addDayData(comboBox_2);
		addDayData(comboBox_5);
		addReportType(comboBox_6);
		
		JButton btnReport = new JButton("report");
		btnReport.addActionListener(new ActionListener() {
			// report
			public void actionPerformed(ActionEvent e) {
				try {
					ResultWriterImpl rwi; 
					Report re = new Report(new DataCollector());
					String start_date = String.valueOf(comboBox.getSelectedItem()) + "/" + String.valueOf(comboBox_1.getSelectedItem()) + "/" + String.valueOf(comboBox_2.getSelectedItem());
					String end_date = String.valueOf(comboBox_3.getSelectedItem()) + "/" + String.valueOf(comboBox_4.getSelectedItem()) + "/" + String.valueOf(comboBox_5.getSelectedItem());
					String report_type = (String)comboBox_6.getSelectedItem();
					
					// this is caidan >_<
					int year = 2015;
					int month = 1;
					int from_day = Integer.valueOf(String.valueOf(comboBox_2.getSelectedItem()));
					int end_day = Integer.valueOf(String.valueOf(comboBox_5.getSelectedItem()));
					
					if(report_type.equals("sales amount")) {
						rwi = new ResultWriterImpl("outputs/salesReport.csv");
						List<String[]> list = new ArrayList<String[]>();
						String title = "from " + start_date + " to " + end_date + " sales amount report";
						list.add(new String[]{title});
						list.add(new String[]{"date", "room type", "sales amount"});
						
						int sales_amount[] = new int[3];
						for(int i=from_day; i<=end_day; i++) {
							String today = year + "/" + month + "/" + i;
							list.add(new String[]{today, "single room", String.valueOf(re.reportSales(1, today, 1))});
							list.add(new String[]{today, "double room", String.valueOf(re.reportSales(1, today, 2))});
							list.add(new String[]{today, "triple room", String.valueOf(re.reportSales(1, today, 3))});
							
							sales_amount[0] += re.reportSales(1, today, 1);
							sales_amount[1] += re.reportSales(1, today, 2);
							sales_amount[2] += re.reportSales(1, today, 3);
						}
						//System.out.println();
						// generate report
						list.add(new String[]{"", "", "", ""});
						list.add(new String[]{"total: "});
						list.add(new String[]{"single room: ", String.valueOf(sales_amount[0])});
						list.add(new String[]{"double room: ", String.valueOf(sales_amount[1])});
						list.add(new String[]{"triple room: ", String.valueOf(sales_amount[2])});
						
						// write current date
						Date _date=new Date();
						DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						String time=format.format(_date); 
						list.add(new String[]{"", "", "", ""});
						list.add(new String[]{"", "", "", ""});
						list.add(new String[]{"report made time: ", time});
						
						rwi.writeResult(list);
						JOptionPane.showMessageDialog(null, "report made successfully!");  
						
					}
					else if(report_type.equals("water cost")) {
						
					}
					else if(report_type.equals("electricity cost")) {
						
					}
					else if(report_type.equals("benefit amount")) {
						
					}
					
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				
			}
		});
		
		JButton btnPlot = new JButton("plot line");
		btnPlot.addActionListener(new ActionListener() {
			// plot line figure
			public void actionPerformed(ActionEvent e) {
				String start_date = String.valueOf(comboBox.getSelectedItem()) + "/" + String.valueOf(comboBox_1.getSelectedItem()) + "/" + String.valueOf(comboBox_2.getSelectedItem());
				String end_date = String.valueOf(comboBox_3.getSelectedItem()) + "/" + String.valueOf(comboBox_4.getSelectedItem()) + "/" + String.valueOf(comboBox_5.getSelectedItem());
				String report_type = (String)comboBox_6.getSelectedItem();
				
				// this is caidan >_<
				int year = 2015;
				int month = 1;
				int from_day = Integer.valueOf(String.valueOf(comboBox_2.getSelectedItem()));
				int end_day = Integer.valueOf(String.valueOf(comboBox_5.getSelectedItem()));
				
				List<String> product = Arrays.asList("single", "double", "triple");
				List<String> x_data = new ArrayList<String>();
				List<List<Integer>> data = new ArrayList<List<Integer>>();
				
				List<Integer> d1 = new ArrayList<Integer>();
				List<Integer> d2 = new ArrayList<Integer>();
				List<Integer> d3 = new ArrayList<Integer>();
				
				if(report_type.equals("sales amount")) {
					try {
						Report re = new Report(new DataCollector());
						for(int i=from_day; i<=end_day; i++) {
							String today = year + "/" + month + "/" + i;
							d1.add(re.reportSales(1, today, 1));
							d2.add(re.reportSales(1, today, 2));
							d3.add(re.reportSales(1, today, 3));
							x_data.add(today);	
						}
					} catch(IOException ioe) {
						ioe.printStackTrace();
					}
					
					data.add(d1);
					data.add(d2);
					data.add(d3);
					//System.out.println();
					// plot
					Plot p = new Plot();
					p.plotLine("sales amount", "date", "amount", product, x_data, data);
				}
			}
		});
		
		JButton btnNewButton = new JButton("plot pie");
		btnNewButton.addActionListener(new ActionListener() {
			// pot pie figure
			public void actionPerformed(ActionEvent e) {
				try {
					Report re = new Report(new DataCollector());
					String start_date = String.valueOf(comboBox.getSelectedItem()) + "/" + String.valueOf(comboBox_1.getSelectedItem()) + "/" + String.valueOf(comboBox_2.getSelectedItem());
					String end_date = String.valueOf(comboBox_3.getSelectedItem()) + "/" + String.valueOf(comboBox_4.getSelectedItem()) + "/" + String.valueOf(comboBox_5.getSelectedItem());
					String report_type = (String)comboBox_6.getSelectedItem();
					
					// this is caidan >_<
					int year = 2015;
					int month = 1;
					int from_day = Integer.valueOf(String.valueOf(comboBox_2.getSelectedItem()));
					int end_day = Integer.valueOf(String.valueOf(comboBox_5.getSelectedItem()));
					
					if(report_type.equals("sales amount")) {
						List<String> product = Arrays.asList("single room", "double room", "triple room");
						int sales_amount[] = new int[3];
						for(int i=from_day; i<=end_day; i++) {
							String today = year + "/" + month + "/" + i;
							sales_amount[0] += re.reportSales(1, today, 1);
							sales_amount[1] += re.reportSales(1, today, 2);
							sales_amount[2] += re.reportSales(1, today, 3);
						}
						List<Integer> data = new ArrayList<Integer>();
						for(int val : sales_amount) {
							data.add(val);
						}
						new Plot().plotPie(product, data, "Product Categorized Sales Amount");
					}
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnPlot)
							.addGap(30)
							.addComponent(btnNewButton)
							.addGap(31)
							.addComponent(btnPlotBar)
							.addGap(61))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnReport)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblEndDate)
									.addComponent(lblStartDate)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblReportType)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(comboBox_6, 0, 157, Short.MAX_VALUE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblYear)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblYear_1)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboBox_3, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
												.addGap(34)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(lblMonth)
													.addComponent(lblMonth_1))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
													.addComponent(comboBox_4, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(comboBox_1, 0, 72, Short.MAX_VALUE))))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblDay)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblDay_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBox_5, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
								.addGap(24)))))
		);
		btnPlotBar.addActionListener(new ActionListener() {
			// plot bar figure
			public void actionPerformed(ActionEvent e) {
				
				try {
					Report re = new Report(new DataCollector());
					String start_date = String.valueOf(comboBox.getSelectedItem()) + "/" + String.valueOf(comboBox_1.getSelectedItem()) + "/" + String.valueOf(comboBox_2.getSelectedItem());
					String end_date = String.valueOf(comboBox_3.getSelectedItem()) + "/" + String.valueOf(comboBox_4.getSelectedItem()) + "/" + String.valueOf(comboBox_5.getSelectedItem());
					String report_type = (String)comboBox_6.getSelectedItem();
					
					// this is caidan >_<
					int year = 2015;
					int month = 1;
					int from_day = Integer.valueOf(String.valueOf(comboBox_2.getSelectedItem()));
					int end_day = Integer.valueOf(String.valueOf(comboBox_5.getSelectedItem()));
					
					if(report_type.equals("sales amount")) {
						List<String> product = Arrays.asList("single room", "double room", "triple room");
						int sales_amount[] = new int[3];
						for(int i=from_day; i<=end_day; i++) {
							String today = year + "/" + month + "/" + i;
							sales_amount[0] += re.reportSales(1, today, 1);
							sales_amount[1] += re.reportSales(1, today, 2);
							sales_amount[2] += re.reportSales(1, today, 3);
						}
						List<Double> data = new ArrayList<Double>();
						for(int val : sales_amount) {
							data.add(Double.valueOf(val));
						}
						new Plot().plotBar(product, data, "Product Categorized Sales Amount", "product category", "sales amount");
					}
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(lblStartDate)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYear)
						.addComponent(lblMonth)
						.addComponent(lblDay)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblEndDate)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYear_1)
						.addComponent(lblMonth_1)
						.addComponent(lblDay_1)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReportType)
						.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnReport)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlot)
						.addComponent(btnNewButton)
						.addComponent(btnPlotBar))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
