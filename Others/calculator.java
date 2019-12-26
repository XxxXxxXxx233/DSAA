package Others;

import java.util.Scanner;

public class calculator {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Quiz1: ");
		int quiz1 = in.nextInt();
		System.out.println();
		
		System.out.print("Quiz2: ");
		int quiz2 = in.nextInt();
		System.out.println();
		
		System.out.print("Mid: ");
		int mid = in.nextInt();
		System.out.println();
		
		System.out.print("Lab: ");
		double lab = in.nextDouble();
		System.out.println();
		
		System.out.print("Bouns: ");
		int bouns = in.nextInt();
		System.out.println();
		
		System.out.print("Final: ");
		int f = in.nextInt();
		System.out.println();
		
		int sign = 10;
		
		double grade = sign + quiz1 * 0.1 + quiz2 * 0.1 + mid * 0.25 + lab + f * 0.25 + bouns;
		
		System.out.println(grade);
	}
}
