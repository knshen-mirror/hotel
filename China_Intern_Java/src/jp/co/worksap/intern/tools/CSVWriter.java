package jp.co.worksap.intern.tools;

import java.io.*;
import java.util.*;
import jp.co.worksap.intern.dto.*;

/**
 * Some naive csv file random generator, may not general and totally automatic
 * @author intern Kyle
 *
 */
public class CSVWriter {
	public static String filePath = "C:\\Users\\ShenKai\\Desktop\\China_Intern_Java\\files\\ROOM_STATUS.csv";
	public static String file = "e://tmp.txt";
	
	
	public CSVWriter() {
		
	}
	
	/**
	 * Randomly generate miantain list
	 * @param list
	 * @param filePath
	 * @throws IOException
	 */
	public void generateMaintainList(List<MaintainDTO> list, String filePath) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath), false));
		String head = "hotel_id,room_id,device,fix";
		bw.write(head + "\n");
		
		for(MaintainDTO dto : list) {
			String line = dto.getHotel_id() + "," + dto.getRoom_id() + "," + dto.getDevice() + "," + dto.getFix();
			bw.write(line + "\n");
			bw.flush();
		}
		
		bw.close();
	}
	
	/**
	 * Randomly generate customer consumptions
	 * @throws IOException
	 */
	public void generateConsumption() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file), false));
		int customer_id = 64;
		int hotel_id = 1;
		
		for(int i=1; i<=6; i++) {
			String date = "2015-1-";
			int day = (int)(Math.random() * 31);
			date += day;
			int cost = (int)(Math.random() * 5000);
			
			String line = customer_id + "," + hotel_id + "," + date + "," + cost;
			bw.write(line + "\n");
			bw.flush();
		}
		bw.close();
	}
	
	/**
	 * Randomly generate room status list
	 * @throws IOException
	 */
	public void generateRoomStatus() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file), false));
		for(int i = 1; i<=31; i++) {
			String date = "2015-1-" + i;
			String hotel_id = "4";
			for(int j=1; j<=6; j++) {
				String room_id = String.valueOf(j);
				String room_type = String.valueOf(Integer.valueOf(room_id) % 3 + 1);
				int live = (int)(Math.random()*10) % 2;
				int water = (int)(Math.random() * 100);
				int energy = (int)(Math.random() * 200);
				int goods = (int)(Math.random() * 100);
				
				String line = hotel_id + "," + room_id + "," + room_type + "," + date + "," + live + "," + water + "," + energy + "," + goods;
				bw.write(line + "\n");
			}
			bw.flush();
		}
		bw.close();
	}
	
	public static void main(String args[]) {
		try {
			new CSVWriter().generateConsumption();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
