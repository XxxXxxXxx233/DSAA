package lab0;

import java.util.Scanner;

public class SummerCamp {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			double studentNumber = in.nextDouble();
			boolean check = false;
			int rowNumber = 0;
			for(int k=2; k<(studentNumber+2)/2; k++) {
				if(k * (k+1) > 2 * studentNumber)
					break;
				double numberFirstRow = (double)(2 * studentNumber - k * k + k) / (double)(2 * k);
				if(numberFirstRow % 1 == 0) {
					rowNumber = k;
					check = true;
					break;
				}
			}
			if(check)
				System.out.println(rowNumber);
			else
				System.out.println("impossible");
		}
	}
}
