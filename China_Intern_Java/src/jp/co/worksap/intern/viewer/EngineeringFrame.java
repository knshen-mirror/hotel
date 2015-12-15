package jp.co.worksap.intern.viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;

import jp.co.worksap.intern.controller.EngineeringBusiness;
import jp.co.worksap.intern.controller.MaintainListChecker;
import jp.co.worksap.intern.dto.MaintainDTO;
import jp.co.worksap.intern.dto.RuleDTO;
import jp.co.worksap.intern.tools.DataCollector;
import jp.co.worksap.intern.tools.Finder;

public class EngineeringFrame extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTable table;
	private JButton btnMake;
	private JLabel lblMaintainList;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private EngineeringBusiness eb;
	private MaintainListChecker checker;
	private List<Boolean> isDispatch;
	
	public void run() {
		try {
			while(true) {
				checker.importToMaintain();
				
				String headers[] = new String[]{"room id", "device type", "fixed?"};
				Object [][]data = new Object[100][3];
				System.out.println("# tasks: " + checker.getToDoListSize());
				
				DataCollector dc = new DataCollector();
				int j = 0;
				for(int i=0; i<checker.todolist.size(); i++) {
					MaintainDTO task = checker.todolist.get(i);
					String device_name = dc.device.get(task.getDevice());
					data[j][0] = task.getRoom_id();
					data[j][1] = device_name;
					data[j][2] = "no";
					j++;
				} // end for
				DefaultTableModel model1 = new DefaultTableModel(data, headers); 
				table_1 = new JTable(model1);
				scrollPane_1.setViewportView(table_1);
				
				Thread.sleep(1000); //wait a second before dispatch task
				//dispatch maintain task
				for(int i=isDispatch.size(); i<checker.todolist.size(); i++) {
					isDispatch.add(false);
				}
				
				for(int i=0; i<isDispatch.size(); i++) {
					if(!isDispatch.get(i)) {
						// send message to staff
						MaintainDTO dto = checker.todolist.get(i);
						
						String device_name = new DataCollector().device.get(dto.getDevice());
						int staff_id = eb.dispatch(dto);
						if(staff_id == -1) {
							// no rule
							continue;
						}
						String staff_name = new Finder(new DataCollector()).staffID2Name(staff_id);
						String message = "staff id: " + staff_id + "\nstaff name: " + staff_name + "\nroom id: " + dto.getRoom_id() + "\ndevice: " + device_name;
						JOptionPane.showMessageDialog(null, message, "task dispatch message",JOptionPane.ERROR_MESSAGE);
						isDispatch.set(i, true);
					}
				}
				Thread.sleep(5000);
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EngineeringFrame frame = new EngineeringFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EngineeringFrame() throws IOException {
		eb = new EngineeringBusiness(new DataCollector(), "files/MAINTAIN.csv");
		checker = new MaintainListChecker(new DataCollector(), "files/MAINTAIN.csv");
		isDispatch = new ArrayList<Boolean>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblMakeDispatchRule = new JLabel("make dispatch rule:  ");
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnMake = new JButton("make");
		btnMake.addActionListener(new ActionListener() {
			// make task dispatch rules
			public void actionPerformed(ActionEvent e) {
				int rows = table.getRowCount();
				DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				for(int i=0; i<rows; i++) {
					if((String.valueOf(tableModel.getValueAt(i, 0))).equals(""))
						continue;
					
					RuleDTO dto = new RuleDTO();
					dto.setHotel_id(1);
					dto.setDevice_type(Integer.valueOf(String.valueOf(tableModel.getValueAt(i, 0))));
					dto.setStart_room_id(Integer.valueOf(String.valueOf(tableModel.getValueAt(i, 1))));
					dto.setEnd_room_id(Integer.valueOf(String.valueOf(tableModel.getValueAt(i, 2))));
					//System.out.println(String.valueOf(tableModel.getValueAt(i, 3)) + "#");
					dto.setStaff_id(Integer.valueOf(String.valueOf(tableModel.getValueAt(i, 3))));
					eb.makeDispatchRule(dto);
					
					//System.out.println();
				} // end for
				JOptionPane.showMessageDialog(null, "rule made successfully!", "remind", JOptionPane.PLAIN_MESSAGE); 
			}
		});
		
		lblMaintainList = new JLabel("maintain list:  ");
		
		scrollPane_1 = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMakeDispatchRule)
							.addPreferredGap(ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
							.addComponent(btnMake)
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMaintainList)
							.addContainerGap(318, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMakeDispatchRule)
						.addComponent(btnMake))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(lblMaintainList)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addContainerGap())
		);
		// init to do list table ////////////
		String headers1[] = new String[]{"room id", "device type", "fixed?"};
		Object [][]data1 = {{"", "", ""}, {"", "", ""}, {"", "", ""}};
		DefaultTableModel model1 = new DefaultTableModel(data1, headers1); 
		
		
		table_1 = new JTable(model1);
		scrollPane_1.setViewportView(table_1);
		
		// rule making table
		String[] headers = {"device type", "start room id", "end room id", "staff"};
		Object[][] data = {{"","","",""}, {"","","",""}, {"","","",""}, {"","","",""}};
		DefaultTableModel model = new DefaultTableModel(data, headers); 
		table = new JTable(model);
		
		scrollPane.setViewportView(table);
		
		contentPane.setLayout(gl_contentPane);
	}
}
