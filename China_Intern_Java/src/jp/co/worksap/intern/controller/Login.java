package jp.co.worksap.intern.controller;

import jp.co.worksap.intern.tools.DataCollector;
import java.io.*;
import java.util.*;

/**
 * To support login function 
 * @author intern Kyle
 *
 */
public class Login {
	private DataCollector dc;
	
	public Login() throws IOException {
		dc = new DataCollector();
	}
	
	/**
	 * get user position
	 * @param user_name
	 * @return {position, dept_id}
	 */
	public List<Object> getUserPos(String user_name)  {
		//user_name is staff id
		List<Object> ret = new ArrayList<Object>();
		int pos = -1;
		for(int i=0; i<dc.staff_list.size(); i++) {
			if(dc.staff_list.get(i).getStaff_id() == Integer.valueOf(user_name)) {
				ret.add(dc.staff_list.get(i).getPosition());
				ret.add(dc.staff_list.get(i).getDept_id());
				break;
			}
		}
		
		return ret;
	}
}
