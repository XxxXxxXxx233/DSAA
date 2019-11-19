package lab7;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class test {
	public static void main(String[] args) throws FileNotFoundException {
		int n = 10000;
		int count = 0;
		int x = 3789;
		ArrayList<Integer> list = new ArrayList<>();
		PrintWriter write = new PrintWriter("E:\\test.txt");
		while(count < n) {
			int num = (int)(Math.random()*2147483647);
			if(!list.contains(num)) {
				write.print(num + " ");
				count++;
			}
			list.add(num);
		}
		for(int i=0; i<n-x+1; i++) {
			int num = (int)(Math.random()*x);
			if(num <= 0)
				write.print(1 + " ");
			else
				write.print(num + " ");
		}
		write.close();
	}
}
