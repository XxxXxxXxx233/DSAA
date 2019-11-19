package lab5;

import java.util.Scanner;

public class HowManySubstrings {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int length = in.next().length();
			System.out.println((length * (length+1))/2);
		}
	}
}
