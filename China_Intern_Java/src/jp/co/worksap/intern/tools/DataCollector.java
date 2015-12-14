package jp.co.worksap.intern.tools;

import java.util.*;
import jp.co.worksap.intern.dto.*;
import java.io.*;

public class DataCollector {
	private CSVReader cr;
	
	public List<ConsumptionDTO> con_list = new ArrayList<ConsumptionDTO>();
	public List<CustomerDTO> cus_list = new ArrayList<CustomerDTO>();
	public Map<Integer, String> dept = new HashMap<Integer, String>();
	public Map<Integer, String> device = new HashMap<Integer, String>();
	public List<HotelDTO> hotel_list = new ArrayList<HotelDTO>();
	public List<MaintainDTO> main_list = new ArrayList<MaintainDTO>();
	public List<RegionDTO> region_list = new ArrayList<RegionDTO>();
	public List<RoomDTO> room_list = new ArrayList<RoomDTO>();
	public List<RoomStatusDTO> rs_list = new ArrayList<RoomStatusDTO>();
	public List<StaffDTO> staff_list = new ArrayList<StaffDTO>();
	
	public DataCollector() throws IOException {
		String prefix = "files/";
		this.getCon_list(prefix + "CONSUMPTION.csv");
		this.getCus_list(prefix + "CUSTOMER_MST.csv");
		this.getDept(prefix + "DEPT.csv");
		this.getDevice(prefix + "DEVICE.csv");
		this.getHotel_list(prefix + "HOTEL_MST.csv");
		this.getMainList(prefix + "MAINTAIN.csv");
		this.getRegionList(prefix + "REGION_MST.csv");
		this.getRoomList(prefix + "ROOM_MST.csv");
		this.getRSList(prefix + "ROOM_STATUS.csv");
		this.getStaffList(prefix + "STAFF_MST.csv");
	}
	
	private void getCon_list(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			ConsumptionDTO dto = new ConsumptionDTO();
			dto.setCustomer_id(Integer.valueOf(list.get(0)));
			dto.setHotel_id(Integer.valueOf(list.get(1)));
			dto.setDate(list.get(2));
			dto.setCost(Integer.valueOf(list.get(3)));
			con_list.add(dto);
		}
	}
	
	private void getCus_list(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			CustomerDTO dto = new CustomerDTO();
			dto.setId(Integer.valueOf(list.get(0)));
			dto.setName(list.get(1));
			dto.setEmail(list.get(7));
			dto.setTele(list.get(8));
			cus_list.add(dto);
		}
	}
	
	private void getDept(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			dept.put(Integer.valueOf(list.get(0)), list.get(1));
		}
	}
	
	private void getDevice(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			device.put(Integer.valueOf(list.get(0)), list.get(1));
		}
	}
	
	private void getHotel_list(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			HotelDTO dto = new HotelDTO();
			dto.setHotel_id(Integer.valueOf(list.get(0)));
			dto.setHotel_name(list.get(1));
			dto.setAddr(list.get(2));
			dto.setRegion_id(Integer.valueOf(list.get(4)));
			hotel_list.add(dto);
		}
	}
	
	private void getMainList(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			MaintainDTO dto = new MaintainDTO();
			dto.setHotel_id(Integer.valueOf(list.get(0)));
			dto.setRoom_id(Integer.valueOf(list.get(1)));
			dto.setDevice(Integer.valueOf(list.get(2)));
			dto.setFix(Integer.valueOf(list.get(3)));
			main_list.add(dto);
		}
	}
	
	private void getRegionList(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			RegionDTO dto = new RegionDTO();
			dto.setRegion_id(Integer.valueOf(list.get(0)));
			dto.setRegion_name(list.get(1));
			dto.setStaff_id(Integer.valueOf(list.get(2)));
			region_list.add(dto);
		}
	}
	
	private void getRoomList(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			RoomDTO dto = new RoomDTO();
			dto.setRoom_type_id(Integer.valueOf(list.get(0)));
			dto.setRoom_type(list.get(1));
			dto.setPrice(Integer.valueOf(list.get(2)));
			dto.setPrice_unit(list.get(3));
			room_list.add(dto);
		}
	}
	
	private void getRSList(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			RoomStatusDTO dto = new RoomStatusDTO();
			dto.setHotel_id(Integer.valueOf(list.get(0)));
			dto.setRoom_id(Integer.valueOf(list.get(1)));
			dto.setRoom_type_id(Integer.valueOf(list.get(2)));
			dto.setDate(list.get(3));
			dto.setLive(Integer.valueOf(list.get(4)));
			dto.setWater(Integer.valueOf(list.get(5)));
			dto.setEnergy(Integer.valueOf(list.get(6)));
			dto.setGoods(Integer.valueOf(list.get(7)));
			rs_list.add(dto);
		}
	}
	
	private void getStaffList(String path) throws IOException {
		cr = new CSVReader(path);
		List<List<String>> data = cr.getData();
		
		for(List<String> list : data) {
			StaffDTO dto = new StaffDTO();
			dto.setStaff_id(Integer.valueOf(list.get(0)));
			dto.setName(list.get(1));
			dto.setGender(list.get(2));
			dto.setPosition(list.get(4));
			dto.setHotel_id(Integer.valueOf(Integer.valueOf(list.get(5))));
			dto.setDept_id(Integer.valueOf(list.get(6)));
			
			staff_list.add(dto);
		}
	}
	
	public static void main(String[] args) throws IOException {
		DataCollector dc = new DataCollector();
		//dc.getCon_list("e://data//CONSUMPTION.csv");
		System.out.println();
	}

}
