package lab7;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class test {
	public static void main(String[] args) throws FileNotFoundException {
		int n = 80000;
		int count = 0;
		ArrayList<Integer> list = new ArrayList<>();
		PrintWriter write = new PrintWriter("E:\\test.txt");
		write.print(n + " ");
		while(count < n) {
			int petOrAdopter = (int)(Math.random()*2);
/*			if(count % 2 == 0)
				petOrAdopter = 0;
			else
				petOrAdopter = 1;*/
			
			int num = (int)(Math.random()*2147483647);
			if(!list.contains(num)) {
				write.print(petOrAdopter + " " + num + " ");
				count++;
			}
			list.add(num);
		}
		write.close();
	}
}
