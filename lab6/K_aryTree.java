package lab6;

import java.util.Scanner;

public class K_aryTree {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			 int number = in.nextInt();
			 int k = in.nextInt();
			 if(number <= k) {
				 System.out.println(number-1);
			 } else {
				 long n = k;
				 long n1 = 1;
				 number--;
				 long answer = 0;
				 while(number > n) {
					 number -= n;
					 n = n * k;
					 n1 = n1 * k;
				 }
				 int nodeNumber = number/k;
				 if(number % k != 0) {
					 nodeNumber++;
				 }
				 answer += number + (n1-nodeNumber);
				 System.out.println(answer);
			 }
		}
	}
}
