package lab1;

import java.util.Scanner;

public class FactorialMagic {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		long answer = 0;
		if(n <= 1)
			answer = factorial(1, m);
		else if(n == 2)
			answer = factorial(2, m);
		else if(n == 3)
			answer = factorial(720, m);
		else
			answer = 0;				
		System.out.println(answer);
	}
	
	public static long factorial(long n, long m) {
		if(n > m || n % m == 0)
			return 0;
		if(n == 0)
			return 1;
		long answer = 1;
		for(long i=n; i>1; i--) {
			answer = (answer * (i % m) % m);
			if(answer == 0)
				return 0;
		}
		return answer;
	}
}
