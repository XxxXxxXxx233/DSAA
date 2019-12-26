package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("C:\\Users\\18038\\Desktop\\dd_project_vga\\gif\\1uart.coe");
		PrintWriter write = new PrintWriter("C:\\Users\\18038\\Desktop\\dd_project_vga\\gif\\1uart.txt");
		Scanner in = new Scanner(f);
		String str = "";
		int count = 0;
		while(in.hasNext()) {
			String[] arr = in.next().split(",");
			for(int i=0; i<arr.length; i++) {
				String r = arr[i].substring(0, 1);
				String g = arr[i].substring(1, 2);
				String b = arr[i].substring(2, 3);
				String bgr = b + g + r;
				str += bgr;
			}
		}
		int counter = 0;
		for(int i=0; i<str.length(); i++) {
			write.print(str.substring(i, i+1));
			counter++;
			if(counter == 2) {
				write.print(" ");
				counter = 0;
			}
		}
		write.close();
	}
}
