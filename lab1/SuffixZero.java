package lab1;

import java.util.Scanner;

public class SuffixZero {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int test = in.nextInt();
		for(int i=0; i<test; i++) {
			long number = in.nextLong();
			System.out.println(count(number));
		}
	}
	public static long count(long number) {
		long answer = 0;
		while(number > 0) {
			answer += number/5;
			number /= 5;
		}
		return answer;
	}
}
