package lab1;

import java.util.Scanner;

public class TwoProduct {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		long M = in.nextLong();
		long[] arr = new long[N];
		long count = 0;
		for(int i=0; i<N; i++)
			arr[i] = in.nextLong();
		
		if(M == 0) {
			boolean check = false;
			for(int j=0; j<N; j++) {
				if(arr[j] == 0)
					check = true;
				else {
					if(j == 0 || (j > 0 && arr[j] != arr[j - 1])) {
						count++;
					}
				}
			}
			for(int j=0; j<N; j++) {
				if(arr[j] == 0 && arr[j] == arr[j+1]) {
					count++;
					break;
				}
			}
			if(!check)
				System.out.println(0);
			else
				System.out.println(count);
		} else {
			if(M > 0) {
				int loopNumberPos = N - 1;
//				int index = findIndex(arr);
				int index = binarySort(0, arr, 0, loopNumberPos);
				//positive number
				for(int i=index; i<loopNumberPos - 1; i++) {
					if(i == 0 || (i>0 && arr[i] != arr[i-1]) && arr[i] != 0) {
						double num = (double)M / arr[i];
						if(num % 1 == 0) {
							long number = (long)num;
							int position = binarySort(number, arr, i, loopNumberPos);
							if(arr[position - 1] == number && number != arr[i]) {
//								System.out.println("case 1 arr[i] = " + arr[i]);
								count++;
								loopNumberPos = position - 1;
							} else if (arr[position - 1] == number && number == arr[i] && number == arr[position - 2]) {
//								System.out.println("case 2 arr[i] = " + arr[i]);
								count++;
								loopNumberPos = position - 1;
							}
						}
					}
				}
				int loopNumberNeg = index;
				//negative number
				for(int i=0; i<loopNumberNeg - 1; i++) {
					if(i == 0 || (i>0 && arr[i] != arr[i-1])) {
						double num = (double)M / arr[i];
						if(num % 1 == 0) {
							long number = (long)num;
							int position = binarySort(number, arr, i, loopNumberNeg);
							if(arr[position - 1] == number && number != arr[i]) {
//								System.out.println("case 3 arr[i] = " + arr[i]);
								count++;
								loopNumberNeg = position - 1;
							} else if (arr[position - 1] == number && number == arr[i] && number == arr[position - 2]) {
//								System.out.println("case 4 arr[i] = " + arr[i]);
								count++;
								loopNumberNeg = position - 1;
							}
						}
					}
				}
			} else {
//				int index = findIndex(arr);
				int index = binarySort(0, arr, 0, N - 1);
				for(int i=0; i<index; i++) {
					if(arr[i] == 0)
						continue;
					if(i == 0 || (i>0 && arr[i] != arr[i-1]) && arr[i] != 0) {
						double num = (double)M / arr[i];
						if(num % 1 == 0) {
							long number = (long)num;
							int position = binarySort(number, arr, index, N - 1);
							if(arr[position - 1] == number && number != arr[i]) {
//								System.out.println("case 5 arr[i] = " + arr[i]);
								count++;
							} else if (arr[position - 1] == number && number == arr[i] && number == arr[position - 2]) {
//								System.out.println("case 6 arr[i] = " + arr[i]);
								count++;
							}
						}
					}
				}
			}
			System.out.println(count);
		}
	}
	
	public static int binarySort(long number, long[] arr, int l, int r) {
		long temp = number;
		int left = l;
		int center = 0;
		int right = r;
		while(left <= right) {
			center = left + (right - left) / 2;
			if(temp < arr[center])
				right = center - 1;
			else
				left = center + 1;
		}
		return left;
	}
}
