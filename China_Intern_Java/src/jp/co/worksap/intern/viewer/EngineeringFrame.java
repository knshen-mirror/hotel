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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import jp.co.worksap.intern.controller.EngineeringBusiness;
import jp.co.worksap.intern.controller.MaintainListChecker;
import jp.co.worksap.intern.dto.RuleDTO;
import jp.co.worksap.intern.tools.DataCollector;

public class EngineeringFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnMake;
	private JLabel lblMaintainList;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private EngineeringBusiness eb;
	private MaintainListChecker checker;
	
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
		checker.start();
		
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
					if(((String)tableModel.getValueAt(i, 0)).equals(""))
						continue;
					
					RuleDTO dto = new RuleDTO();
					dto.setHotel_id(1);
					dto.setDevice_type(Integer.valueOf((String)tableModel.getValueAt(i, 0)));
					dto.setStart_room_id(Integer.valueOf((String)tableModel.getValueAt(i, 1)));
					dto.setEnd_room_id(Integer.valueOf((String)tableModel.getValueAt(i, 2)));
					dto.setStaff_id(Integer.valueOf((String)tableModel.getValueAt(i, 3)));
					eb.makeDispatchRule(dto);
					//System.out.println();
				} // end for
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
		// to do list table
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
