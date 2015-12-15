package jp.co.worksap.intern.controller;

import jp.co.worksap.intern.tools.DataCollector;
import jp.co.worksap.intern.tools.DateComparator;
import jp.co.worksap.intern.tools.Finder;

import java.io.*;
import java.util.Arrays;

/**
 * Basic functions to support various business reports 
 * @author intern Kyle
 *
 */
public class Report {

	private DataCollector dc;
	private Finder finder;
	public Report(DataCollector dc) throws IOException {
		this.dc = dc;
		this.finder = new Finder(dc);
	}
	
	/**
	 * room occupation of a type of room in one day
	 * @param hotel_id
	 * @param date
	 * @param room_type
	 * @return {# rooms lived, # rooms, occupation ratio}
	 */
	public double[] reportRoomOccupy(int hotel_id, String date, int room_type) {
		int total = 0;
		int live = 0;
		double occupy = 0;
		
		for(int i=0; i<dc.rs_list.size(); i++) {
			if(hotel_id == dc.rs_list.get(i).getHotel_id()
					&& date.equals(dc.rs_list.get(i).getDate())
					&& room_type == dc.rs_list.get(i).getRoom_type_id()) {
				total++;
				if(dc.rs_list.get(i).getLive() == 1) {
					live++;
				}
			}
		}
		occupy = (double)(live) / total;
		return new double[]{live, total, occupy};
	}
	
	/**
	 * report customer flow of all the room in one day
	 * @param hotel_id
	 * @param date
	 * @return # people
	 */
	public int reportCustomerFlow(int hotel_id, String date) {
		int people = 0;
		for(int i=0; i<dc.rs_list.size(); i++) {
			if(hotel_id == dc.rs_list.get(i).getHotel_id()
					&& date.equals(dc.rs_list.get(i).getDate())
					&& dc.rs_list.get(i).getLive() == 1) {
				if(dc.rs_list.get(i).getRoom_type_id() == 1)
					people += 1;
				else if(dc.rs_list.get(i).getRoom_type_id() == 2)
					people += 2;
				else
					people += 3;
			}
		}
		
		return people;
	}
	
	/**
	 * report various costs of one type room one day
	 * @param hotel_id
	 * @param date
	 * @param room_type
	 * @return {water cost, energy cost, goods cost}
	 */
	public int[] reportRoomCost(int hotel_id, String date, int room_type) {
		int water = 0;
		int energy = 0;
		int goods = 0;
		
		for(int i=0; i<dc.rs_list.size(); i++) {
			if(hotel_id == dc.rs_list.get(i).getHotel_id()
					&& date.equals(dc.rs_list.get(i).getDate())
					&& room_type == dc.rs_list.get(i).getRoom_type_id()) {
				water += dc.rs_list.get(i).getWater();
				energy += dc.rs_list.get(i).getEnergy();
				goods += dc.rs_list.get(i).getGoods();
			}
		}
		return new int[]{water, energy, goods};
	}
	
	/**
	 * report sales amount of one type room one day
	 * @param hotel_id
	 * @param date
	 * @param room_type
	 * @return sales amount
	 */
	public int reportSales(int hotel_id, String date, int room_type) {
		int benefit = 0;
		for(int i=0; i<dc.rs_list.size(); i++) {
			if(hotel_id == dc.rs_list.get(i).getHotel_id()
					&& date.equals(dc.rs_list.get(i).getDate())
					&& dc.rs_list.get(i).getLive() == 1
					&& dc.rs_list.get(i).getRoom_type_id() == room_type) {
				if(dc.rs_list.get(i).getRoom_type_id() == 1)
					benefit += finder.getRoomPrice(1);
				else if(dc.rs_list.get(i).getRoom_type_id() == 2)
					benefit += finder.getRoomPrice(2);
				else
					benefit += finder.getRoomPrice(3);
			}
		}
		return benefit;
	}
	
	/**
	 * report top-k VIP customer in one hotel, during a period 
	 * @param hotel_id
	 * @param startDate
	 * @param endDate
	 * @param k
	 * @return {no.1 customer_id, no.1 consumption amount, ... no.k customer_id, no.k consumption amount}
	 */
	public int[] reportVIPCustomer(int hotel_id, String startDate, String endDate, int k) {
		int money[] = new int[finder.getTotalCustomer()+1];
		for(int i=0; i<dc.con_list.size(); i++) {
			if(dc.con_list.get(i).getHotel_id() == hotel_id
					&& DateComparator.compareDate(dc.con_list.get(i).getDate(), startDate) >= 0
					&& DateComparator.compareDate(dc.con_list.get(i).getDate(), endDate) <= 0) {
				int customer_id = dc.con_list.get(i).getCustomer_id();
				money[customer_id] += dc.con_list.get(i).getCost();
			}
		}
		
		return finder.searchTopKWithID(money, k);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DataCollector dc = new DataCollector();
		Report re = new Report(dc);
		//double res[] = re.reportRoomOccupy(1, "2015/1/1", 2);
		//int res = re.reportCustomerFlow(1, "2015/1/1");
		//int res[] = re.reportRoomCost(1, "2015/1/1", 1);
		//int res = re.reportSales(1, "2015/1/1", 1);
		//int res[] = re.reportVIPCustomer(1, "2015/1/1", "2015/1/10", 2);
		System.out.println();
		
	}

}
