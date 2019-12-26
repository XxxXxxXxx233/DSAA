package Bouns;

import java.util.Scanner;

public class BeggingForGames {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long money = in.nextLong();
		long income = in.nextLong();
		int number = in.nextInt();
		long decrease = in.nextLong();
		long[] arr = new long[number];
		for(int i=0; i<number; i++) {
			arr[i] = in.nextLong();
		}
		long day = 100000000000L;
		long left = 1;
		long right = day;
		long center = 0;
		while(left <= right) {
			center = left + (right-left)/2;
			long tempMoney = money + income * center;
			long tempTotal = countGame(center-1, arr, decrease);
			if(tempMoney >= tempTotal) {
				right = center - 1;
			} else {
				left = center + 1;
			}
		}
		long game = countGame(left-1, arr, decrease);			//after decrease
		long hasMoney = money + income * (left-1);			//before income
		
		if(hasMoney >= game) {
			System.out.println(left + " morning");
		} else {
			System.out.println(left + " evening");
		}
		
	}
	public static long countGame(long day, long[] arr, long decrease) {
		long total = 0;
		long[] temp = new long[arr.length];
		for(int i=0; i<arr.length; i++) {
			temp[i] = arr[i];
		}
		for(int i=0; i<arr.length; i++) {
			if(temp[i] > decrease * day) {
				temp[i] -= decrease * day;
			} else if(arr[i] == decrease * day) {
				temp[i] = decrease;
			} else {
				if(temp[i] % decrease != 0) {
					temp[i] %= decrease;
				} else {
					temp[i] = decrease;
				}
			}
			total += temp[i];
		}
		return total;
	}
}
