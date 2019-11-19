package lab1;

import java.util.Scanner;

public class CrazyPlan {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int day = in.nextInt();
			long answer = (long)(((long)day * (long)(day + 1) * (long)(day + 2)) / 6);
			System.out.println(answer);
		}
	}
}
