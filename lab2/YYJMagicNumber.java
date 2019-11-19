package lab2;

import java.util.Scanner;

public class YYJMagicNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int[][] arr = new int[n][2];
			for(int j=0; j<n; j++) {
				for(int k=0; k<2; k++) {
					arr[j][k] = in.nextInt();
				}
			}
		}
	}
}
