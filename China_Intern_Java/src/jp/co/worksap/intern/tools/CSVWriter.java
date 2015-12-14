package jp.co.worksap.intern.tools;

import java.io.*;


public class CSVWriter {
	public static String filePath = "C:\\Users\\ShenKai\\Desktop\\China_Intern_Java\\files\\ROOM_STATUS.csv";
	public static String file = "e://tmp.txt";
	
	
	public CSVWriter() {
		
	}
	
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
