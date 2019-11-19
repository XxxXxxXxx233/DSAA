package lab1;

import java.util.Scanner;

public class NearestPrice {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int day = in.nextInt();
		int kinds = in.nextInt();
		int[] moneyEachDay = new int[day];
		for(int i=0; i<day; i++)
			moneyEachDay[i] = in.nextInt();
		int[] priceOfFish = new int[kinds];
		for(int i=0; i<kinds; i++) {
			priceOfFish[i] = in.nextInt();
		}
		
		for(int i=0; i<day; i++) {
			int position = binarySort(moneyEachDay[i], priceOfFish);
			if(position == 0)
				System.out.println(moneyEachDay[i]);
			else {
				int change = moneyEachDay[i] - priceOfFish[position - 1];
				if(change == 0)
					System.out.println("Meow");
				else {
					System.out.println(change);
				}
			}
		}
	}
	
	public static int binarySort(int money, int[] priceOfFish) {
		int temp = money;
		int left = 0;
		int center = 0;
		int right = priceOfFish.length - 1;
		while(left <= right) {
			center = (left + right) / 2;
			if(temp < priceOfFish[center])
				right = center - 1;
			else
				left = center + 1;
		}
		return left;
	}
}
