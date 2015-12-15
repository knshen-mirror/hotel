package jp.co.worksap.intern.controller;

import jp.co.worksap.intern.tools.*;
import jp.co.worksap.intern.writer.ResultWriterImpl;
import jp.co.worksap.intern.dto.*;
import java.util.*;

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
		
		dc.main_list.clear();
		dc.getMainList(path);
		dc.main_list.add(task);
		writeBack();
	}
	
	private void writeBack() throws IOException {
		// re-write maintain list (not append)
		new CSVWriter().generateMaintainList(dc.main_list, path);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DataCollector dc = new DataCollector();
		HousekeepingBusiness hkb = new HousekeepingBusiness(dc, "files/MAINTAIN.csv");
		hkb.makeMaintainRequest(1, 2, 1);
		hkb.makeMaintainRequest(1, 4, 1);
		
	}

}
