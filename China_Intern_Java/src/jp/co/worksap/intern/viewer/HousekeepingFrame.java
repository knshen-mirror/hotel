package jp.co.worksap.intern.viewer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import jp.co.worksap.intern.controller.HousekeepingBusiness;
import jp.co.worksap.intern.controller.Report;
import jp.co.worksap.intern.tools.DataCollector;
import jp.co.worksap.intern.tools.Plot;
import jp.co.worksap.intern.writer.ResultWriterImpl;

public class HousekeepingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JLabel lblYear = new JLabel("year");
	
	JLabel lblNewLabel = new JLabel("month");
	
	JComboBox comboBox = new JComboBox();
	
	JLabel lblDay = new JLabel("day");
	
	JComboBox comboBox_1 = new JComboBox();
	
	JComboBox comboBox_2 = new JComboBox();
	JLabel lblMakeAMaintain = new JLabel("make a maintain request: ");
	
	JLabel lblRoomid = new JLabel("room_id: ");
	
	JLabel lblNewLabel_1 = new JLabel("device type: ");
	JSeparator separator = new JSeparator();
	
	JSeparator separator_1 = new JSeparator();
	
	JComboBox comboBox_3 = new JComboBox();
	private final JButton btnNewButton_2 = new JButton("plot");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HousekeepingFrame frame = new HousekeepingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void addYearData(JComboBox jcb) {
		for(int i=2012; i<=2015; i++) {
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
	
	public void addDeviceData(JComboBox jcb) {
		jcb.addItem("air-condition");
		jcb.addItem("electricity");
		jcb.addItem("furniture");
	}
	
	/**
	 * Create the frame.
	 */
	public HousekeepingFrame() {
		btnNewButton_2.addActionListener(new ActionListener() {
			// plot bar chart
			public void actionPerformed(ActionEvent e) {
				Plot plot = new Plot();
				///
				String year = String.valueOf(comboBox.getSelectedItem());
				String month = String.valueOf(comboBox_1.getSelectedItem());
				String day = String.valueOf(comboBox_2.getSelectedItem());
				String date = year + "/" + month + "/" + day;
			
				try {
					Report re = new Report(new DataCollector());
					double single[] = re.reportRoomOccupy(1, date, 1);
					double _double[] = re.reportRoomOccupy(1, date, 2);
					double triple[] = re.reportRoomOccupy(1, date, 3);
					
					List<String> product = new ArrayList<String>();
					List<Double> data = new ArrayList<Double>();
					product.add("single room");
					product.add("double room");
					product.add("triple room");
					
					data.add(single[2]);
					data.add(_double[2]);
					data.add(triple[2]);
					plot.plotBar(product, data, "room occupation", "room type", "occupation rate");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JButton btnNewButton = new JButton("report");
		btnNewButton.addActionListener(new ActionListener() {
			// report button
			public void actionPerformed(ActionEvent e) {
				String year = String.valueOf(comboBox.getSelectedItem());
				String month = String.valueOf(comboBox_1.getSelectedItem());
				String day = String.valueOf(comboBox_2.getSelectedItem());
				String date = year + "/" + month + "/" + day;
			
				try {
					Report re = new Report(new DataCollector());
					double single[] = re.reportRoomOccupy(1, date, 1);
					double _double[] = re.reportRoomOccupy(1, date, 2);
					double triple[] = re.reportRoomOccupy(1, date, 3);
					
					ResultWriterImpl rwi = new ResultWriterImpl("outputs/room_occupation.csv");
					List<String[]> list = new ArrayList<String[]>();
					String head[] = new String[]{"room_type", "lived", "total", "occupy ratio"};
					list.add(head);
					String data1[] = new String[4];
					data1[0] = "single room"; 
					data1[1] = String.valueOf(single[0]); 
					data1[2] = String.valueOf(single[1]);
					data1[3] = String.valueOf(single[2]);
					list.add(data1);
					
					String data2[] = new String[4];
					data2[0] = "double room"; 
					data2[1] = String.valueOf(_double[0]); 
					data2[2] = String.valueOf(_double[1]);
					data2[3] = String.valueOf(_double[2]);
					list.add(data2);
					
					String data3[] = new String[4];
					data3[0] = "triple room"; 
					data3[1] = String.valueOf(triple[0]); 
					data3[2] = String.valueOf(triple[1]);
					data3[3] = String.valueOf(triple[2]);
					list.add(data3);
					
					// write current date
					Date _date=new Date();
					DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					String time=format.format(_date); 
					list.add(new String[]{"", "", "", ""});
					list.add(new String[]{"", "", "", ""});
					list.add(new String[]{"report made time: ", time});
					rwi.writeResult(list);
				    JOptionPane.showMessageDialog(null, "report made successfully!");  
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setToolTipText("generate product categorized room occupation report");
		
		
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("request");
		btnNewButton_1.addActionListener(new ActionListener() {
			// request button
			public void actionPerformed(ActionEvent e) {
				int room_id = Integer.valueOf(textField.getText());
				String device_name = String.valueOf(comboBox_3.getSelectedItem());
				int device_id = -1;
				
				if(device_name.equals("air-condition")) 
					device_id = 1;
				else if(device_name.equals("electricity"))
					device_id = 2;
				else if(device_name.equals("furniture"))
					device_id = 3;
				
				try {
					HousekeepingBusiness hkb = new HousekeepingBusiness(new DataCollector(), "files/MAINTAIN.csv");
					hkb.makeMaintainRequest(1, room_id, device_id);
					System.out.println("room id: " + room_id + "device id: " + device_id);
					
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
				
			}
		});
		
		addYearData(comboBox);
		addMonthData(comboBox_1);
		addDayData(comboBox_2);
		addDeviceData(comboBox_3);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblRoomid))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
										.addComponent(comboBox_3, 0, 142, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblYear)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMakeAMaintain, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_1)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDay)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(btnNewButton_2, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
											.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGap(6)))))
					.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYear)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDay)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(lblMakeAMaintain)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRoomid)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGap(23))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addGap(23))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
