package jp.co.worksap.intern.controller;

import jp.co.worksap.intern.tools.*;
import jp.co.worksap.intern.dto.*;

public class HousekeepingBusiness {
	
	private DataCollector dc;
	public HousekeepingBusiness(DataCollector dc) {
		this.dc = dc;
	}
	
	public void makeMaintainRequest(int hotel_id, int room_id, int device_id) {
		MaintainDTO task = new MaintainDTO();
		task.setHotel_id(hotel_id);
		task.setRoom_id(room_id);
		task.setDevice(device_id);
		task.setFix(0);
		
		
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
		EngineeringBusiness eb = new EngineeringBusiness(dc);
		eb.importToMaintain();
		
		Thread.sleep(3000);
		eb.addAMaintainTask(dto1);
		Thread.sleep(3000);
		eb.addAMaintainTask(dto2);
		Thread.sleep(3000);
		eb.deleteMaintainTask(dto1);
	}

}
