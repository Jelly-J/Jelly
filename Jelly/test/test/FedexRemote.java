package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.EnableLoadTimeWeaving;

public class FedexRemote {

	public static void main(String[] args) throws Exception {

		List<String> list = new ArrayList<String>();
		

		// BufferedReader reader = new BufferedReader(new
		// FileReader("d:/aaaa.txt"));
		// int read = reader.read();System.out.println(read);
		// for (int i = 0; i < read; i++) {
		// String readLine = reader.readLine();
		// System.out.println(readLine);
		// }

		InputStreamReader read = new InputStreamReader(new FileInputStream("d:/postCode.txt"), "GBK");// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			list.add(lineTxt);
		}
		// read.close();
		//
		// String str = "('FedEx', 'CA', '3', 'A3W', 'Remote', '100', '-99999',
		// now(), '-99999', now()),{0}{1}";
		//
		// for (String string : list) {
		// String[] strings = string.split(",");
		// System.out.println("('FedEx', 'CA', '3', '" + strings[0] + "',
		// 'Remote', '" + strings[1]
		// + "', '-99999', now(), '-99999', now()),");
		// }
		Set<String> set = new HashSet<String>();
		for (String string : list) {
			String str = string.substring(0,3);
			set.add(str);
		}
		
		for (String string : set) {
			System.out.println("'%"+string+"',");
		}
		
		
	}

}
