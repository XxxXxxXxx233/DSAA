package Others;

import java.util.Scanner;

public class calc {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			double num1 = in.nextDouble();
			double num2 = in.nextDouble();
			num1 = 1/num1;
			num2 = 1/num2;
			double ans = num1 - num2;
			System.out.println(-1/ans);
			
		}
	}
}
