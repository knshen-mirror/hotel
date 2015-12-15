package jp.co.worksap.intern.dto;

public class RuleDTO {
	private int hotel_id;
	private int start_room_id;
	private int end_room_id;
	private int device_type;
	private int staff_id;
	
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public int getStart_room_id() {
		return start_room_id;
	}
	public void setStart_room_id(int start_room_id) {
		this.start_room_id = start_room_id;
	}
	public int getEnd_room_id() {
		return end_room_id;
	}
	public void setEnd_room_id(int end_room_id) {
		this.end_room_id = end_room_id;
	}
	
	
}
