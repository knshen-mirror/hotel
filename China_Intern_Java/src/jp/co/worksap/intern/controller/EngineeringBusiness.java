package jp.co.worksap.intern.controller;

import jp.co.worksap.intern.tools.DataCollector;
import jp.co.worksap.intern.dto.*;

import java.io.IOException;
import java.util.*;

public class EngineeringBusiness extends Thread {
	private DataCollector dc;
	private static List<MaintainDTO> todoList;
	
	static {
		todoList = new ArrayList<MaintainDTO>();
	}
	
	public EngineeringBusiness(DataCollector dc) {
		this.dc = dc;		
	}
	
	public void importToMaintain() {
		for(int i=0; i<dc.main_list.size(); i++) {
			if(dc.main_list.get(i).getFix() == 0) {
				todoList.add(dc.main_list.get(i));
			}
		}
	}
	
	synchronized public void addAMaintainTask(MaintainDTO task) {
		todoList.add(task);
	}
	
	synchronized public void deleteMaintainTask(MaintainDTO task) {
		for(int i=0; i<todoList.size(); i++) {
			if(todoList.get(i).getDevice() == task.getDevice()
					&& todoList.get(i).getHotel_id() == task.getHotel_id()
					&& todoList.get(i).getRoom_id() == task.getRoom_id()) {
				todoList.remove(i);
				return;
			}
		}
	}
	
	public void setRule(int hotel_id, int device, int room_id) {
		
	}
	
	public void run() {
		while(true) {
			System.out.println("to do list size: " + todoList.size());
			
			try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace(); 
	        }
		}
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		DataCollector dc = new DataCollector();
		EngineeringBusiness check = new EngineeringBusiness(dc);
		
		check.start();
		

		//System.out.println();

	}

}
