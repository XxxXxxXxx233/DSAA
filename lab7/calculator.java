package lab7;

import java.util.Scanner;

public class calculator {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = 3;
		double[] arr = new double[n];
		double total = 0;
		for(int i=0; i<n; i++) {
			double x1 = in.nextDouble();
			double x2 = in.nextDouble();
			arr[i] = Math.abs(x2-x1)/x1;
			arr[i] *= 100;
			total += arr[i];
			System.out.println("Case " + (i+1) + ": " + arr[i] + "%");
		}
		System.out.println("Average = " + total/3 + "%");
	}
}
