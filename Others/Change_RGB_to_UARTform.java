package Others;

import java.io.*;
import java.util.*;

public class Change_RGB_to_UARTform {
	public static void main(String[] args) throws FileNotFoundException {
		int T = 1;
		PrintWriter write = new PrintWriter("C:\\Users\\18038\\Desktop\\dd_project_vga\\uartFile\\RoverUART.txt");
		int count = 12;
		write.print("80 02 1E 00 00 00 ");
		for(int t=1; t<T+1; t++) {
			String filePath = "C:\\Users\\18038\\Desktop\\dd_project_vga\\uartFile\\RoverUART.coe";
			File f = new File(filePath);
			Scanner in = new Scanner(f);
			String str = "";
			while(in.hasNext()) {
				String[] arr = in.next().split(",");
				for(int i=0; i<arr.length; i++) {
					str += arr[i] + " ";
				}
			}
			
			String out = "";
			String[] s = str.split(" ");
			for(int i=0; i<s.length; i+=2) {
				String r1 = s[i].substring(0, 1);
				String g1 = s[i].substring(1, 2);
				String b1 = s[i].substring(2, 3);
				String r2 = "f";
				String g2 = "f";
				String b2 = "f";
				if(i + 1 < s.length) {
					r2 = s[i+1].substring(0, 1);
					g2 = s[i+1].substring(1, 2);
					b2 = s[i+1].substring(2, 3);
				}
				out += (g1 + b1 + b2 + r1 + r2 + g2);
			}
			
			int counter = 0;
			for(int i=0; i<out.length(); i++) {
				write.print(out.substring(i, i+1));
				counter++;
				if(counter == 2) {
					write.print(" ");
					counter = 0;
				}
				count++;
			}
			System.out.println(count/2);
			in.close();
		}
		write.close();
	}
}
