package jp.co.worksap.intern.viewer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import jp.co.worksap.intern.tools.DataCollector;

public class TaskDispatchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JLabel lblStaff = new JLabel("staff: ");
	JTextArea textArea;
	JComboBox comboBox = new JComboBox();
	
	JLabel lblTaskDetail = new JLabel("task detail: ");
	
	JScrollPane scrollPane = new JScrollPane();
	
	JLabel lblSubject = new JLabel("subject: ");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskDispatchFrame frame = new TaskDispatchFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initStaffData(JComboBox jcb) throws IOException {
		DataCollector dc = new DataCollector();
		for(int i=0; i<dc.staff_list.size(); i++) {
			if(dc.staff_list.get(i).getHotel_id() == 1
					&& dc.staff_list.get(i).getDept_id() == 4
					&& dc.staff_list.get(i).getPosition().equals("staff")) {
				jcb.addItem(dc.staff_list.get(i).getName());
			}
		}
		
	}
	/**
	 * Create the frame.
	 */
	public TaskDispatchFrame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 333, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		initStaffData(comboBox);
		
		JButton btnNewButton = new JButton("send");
		btnNewButton.addActionListener(new ActionListener() {
			// after send a message
			public void actionPerformed(ActionEvent e) {
				String staff_name = String.valueOf(comboBox.getSelectedItem());
				String subject = textField.getText();
				String detail = textArea.getText();
				String info = "from: Engineering Manager" 
						+ "\nto: " + staff_name
						+ "\nsubject: " + subject
						+ "\ncontent: " + detail;
				JOptionPane.showMessageDialog(null, info);  
				JOptionPane.showMessageDialog(null, "send successfully!");
				textField.setText("");
				textArea.setText("");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTaskDetail)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStaff)
								.addComponent(lblSubject))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, 0, 135, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStaff)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSubject)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblTaskDetail)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}
}
