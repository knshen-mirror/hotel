package jp.co.worksap.intern.tools;

import java.io.IOException;
import jp.co.worksap.intern.dto.*;

public class Finder {
	private DataCollector dc;
	
	public Finder(DataCollector dc) throws IOException {
		this.dc = dc;
	}
	
	public int getRoomPrice(int room_type_id) {
		for(RoomDTO dto : dc.room_list) {
			if(dto.getRoom_type_id() == room_type_id)
				return dto.getPrice();
		}
		return -1;
	}
	
	public int getTotalCustomer() {
		return dc.cus_list.size();
	}
	
	public int[] searchTopKWithID(int money[], int k) {
		int []res = new int[2*k];
		int x = 0;
		
		for(int i=1; i<=k; i++) {
			int max_money = money[1];
			int max_index = 1;
			for(int j=2; j<money.length; j++) {
				if(money[j] > max_money) {
					max_money = money[j];
					max_index = j;
				}
			}
			res[x++] = max_index;
			res[x++] = max_money;
			
			money[max_index] = -1;
		}
		return res;	
	}
}
