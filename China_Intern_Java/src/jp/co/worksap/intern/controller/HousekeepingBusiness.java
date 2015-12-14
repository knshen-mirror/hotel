package jp.co.worksap.intern.controller;

import jp.co.worksap.intern.tools.*;
import jp.co.worksap.intern.writer.ResultWriterImpl;
import jp.co.worksap.intern.dto.*;

import java.io.*;

public class HousekeepingBusiness {
	
	private DataCollector dc;
	private String path; // path of maintain list CSV file 
	
	public HousekeepingBusiness(DataCollector dc, String path) {
		this.dc = dc;
		this.path = path;
	}
	
	/**
	 * post a maintain task; update to csv file
	 * @param hotel_id
	 * @param room_id
	 * @param device_id
	 */
	public void makeMaintainRequest(int hotel_id, int room_id, int device_id) throws IOException {
		MaintainDTO task = new MaintainDTO();
		task.setHotel_id(hotel_id);
		task.setRoom_id(room_id);
		task.setDevice(device_id);
		task.setFix(0);
		
		dc.getMainList(path);
		dc.main_list.add(task);
		
	}
	
	public void writeBack() throws IOException {
		ResultWriterImpl rwi = new ResultWriterImpl();
		String line[] = new String[];
		
		for(int i=0; i<dc.main_list.size(); i++) {
			
		}
		
		
		rwi.writeResult(list);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MaintainDTO dto1 = new MaintainDTO();
		MaintainDTO dto2 = new MaintainDTO();
		
		dto1.setHotel_id(1);
		dto1.setRoom_id(2);
		dto1.setDevice(1);
		dto1.setFix(0);
		
		dto2.setHotel_id(1);
		dto2.setRoom_id(4);
		dto2.setDevice(1);
		dto2.setFix(0);
		
		DataCollector dc = new DataCollector();
		MaintainListChecker eb = new MaintainListChecker(dc);
		eb.importToMaintain();
		
		Thread.sleep(3000);
		eb.addAMaintainTask(dto1);
		Thread.sleep(3000);
		eb.addAMaintainTask(dto2);
		Thread.sleep(3000);
		eb.deleteMaintainTask(dto1);
	}

}
