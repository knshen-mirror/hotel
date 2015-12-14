package jp.co.worksap.intern.controller;

import jp.co.worksap.intern.tools.DataCollector;
import jp.co.worksap.intern.dto.*;

import java.io.IOException;
import java.util.*;

public class MaintainListChecker extends Thread {
	private DataCollector dc;
	public List<MaintainDTO> todolist = new ArrayList<MaintainDTO>();
	private String path; // path of maintain list CSV file
	
	public MaintainListChecker(DataCollector dc, String path) {
		this.path = path;
		this.dc = dc;		
	}
	
	public void importToMaintain() throws IOException {
		dc.main_list.clear();
		dc.getMainList(path);
		todolist = new ArrayList<MaintainDTO>(dc.main_list);
	}
	
	
	public void run() {
		while(true) {
			try {
			this.importToMaintain();
			System.out.println("to do list size: " + todolist.size());
			Thread.sleep(3000);	           
	        } catch (InterruptedException e) {
	            e.printStackTrace(); 
	        } catch (IOException ioe) {
	        	ioe.printStackTrace();
	        }
		}
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		DataCollector dc = new DataCollector();
		MaintainListChecker check = new MaintainListChecker(dc, "files/MAINTAIN.csv");
		
		check.start();
		//System.out.println();

	}

}
