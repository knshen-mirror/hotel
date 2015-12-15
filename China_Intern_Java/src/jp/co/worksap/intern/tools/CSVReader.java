package jp.co.worksap.intern.tools;

import java.io.*;
import java.util.*;

/**
 * A easy CSV file reader
 * @author intern Kyle
 *
 */
public class CSVReader {
	private String path;
	private BufferedReader br;
	
	public CSVReader(String path) throws IOException {
		this.path = path;
		br = new BufferedReader(new FileReader(new File(path)));
	}
	
	/**
	 * get all the data from a csv file
	 * @return
	 * @throws IOException
	 */
	public List<List<String>> getData() throws IOException {
		List<List<String>> res = new ArrayList<List<String>>();
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String data[] = line.split(",");
			List<String> list = new ArrayList<String>();
			for(String str : data)
				list.add(str);
			res.add(list);
			
			line = br.readLine();
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		try {
			String path = "e://data//CUSTOMER_MST.csv";
			CSVReader cr = new CSVReader(path);
			cr.getData();
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
