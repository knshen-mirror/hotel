package jp.co.worksap.intern.tools;

public class DateComparator {
	/**
	 * compare two date String "yyyy/mm/dd" 
	 * @param date1
	 * @param date2
	 * @return -1, 0, 1
	 */
	public static int compareDate(String date1, String date2) {
		String _date1[] = date1.split("/");
		String _date2[] = date2.split("/");
		
		if(Integer.valueOf(_date1[0]) < Integer.valueOf(_date2[0])) 
			return -1;
		if(Integer.valueOf(_date1[0]) > Integer.valueOf(_date2[0]))
			return 1;
		
		if(Integer.valueOf(_date1[1]) < Integer.valueOf(_date2[1]))
			return -1;
		if(Integer.valueOf(_date1[1]) > Integer.valueOf(_date2[1]))
			return 1;
		
		if(Integer.valueOf(_date1[2]) < Integer.valueOf(_date2[2]))
			return -1;
		if(Integer.valueOf(_date1[2]) > Integer.valueOf(_date2[2]))
			return 1;
		
		return 0;
	}
	
}
