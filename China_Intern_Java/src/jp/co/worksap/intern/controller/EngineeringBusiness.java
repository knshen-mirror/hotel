package jp.co.worksap.intern.controller;

import java.io.*;

import jp.co.worksap.intern.dto.*;
import jp.co.worksap.intern.tools.CSVWriter;
import jp.co.worksap.intern.tools.DataCollector;
import java.util.*;

/**
 * The business logic of Engineering Department
 * @author intern Kyle
 *
 */
public class EngineeringBusiness {
	private DataCollector dc;
	private String path; // maintain list file path
	public List<RuleDTO> rule_list;
	
	public EngineeringBusiness(DataCollector dc, String path) {
		this.dc = dc;
		this.path = path;
		rule_list = new ArrayList<RuleDTO>();
	}
	
	/**
	 * called when finishing a maintain task
	 * @param hotel_id
	 * @param room_id
	 * @param device_id
	 * @throws IOException
	 */
	public void fixAMaintainTask(int hotel_id, int room_id, int device_id) throws IOException {
		dc.main_list.clear();
		dc.getMainList(path);
		for(int i=0; i<dc.main_list.size(); i++) {
			if(dc.main_list.get(i).getHotel_id() == hotel_id
					&& dc.main_list.get(i).getRoom_id() == room_id
					&& dc.main_list.get(i).getDevice() == device_id
					&& dc.main_list.get(i).getFix() == 0) {
				dc.main_list.get(i).setFix(1);
			}
		}
		//write back
		new CSVWriter().generateMaintainList(dc.main_list, path); 
	}
	
	/**
	 * add device maintain rule to rule list
	 */
	public void makeDispatchRule(RuleDTO rule) {
		rule_list.add(rule);
	}
	
	/**
	 * get staff_id when given a task according to the rules
	 * @param task
	 * @return
	 */
	public int dispatch(MaintainDTO task) {
		int target_staff_id = -1;
		for(int i=0; i<rule_list.size(); i++) {
			RuleDTO rule = rule_list.get(i);
			if(rule.getHotel_id() == task.getHotel_id()
					&& rule.getStart_room_id() <= task.getRoom_id()
					&& rule.getEnd_room_id() >= task.getRoom_id()
					&& rule.getDevice_type() == task.getDevice()) {
				target_staff_id = rule.getStaff_id();
				break;
			}
		}
		
		return target_staff_id;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DataCollector dc = new DataCollector();
		EngineeringBusiness eb = new EngineeringBusiness(dc, "files/MAINTAIN.csv");
		
		eb.fixAMaintainTask(2, 4, 1);
	}

}
