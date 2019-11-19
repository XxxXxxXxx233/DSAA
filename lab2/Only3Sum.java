package lab2;

import java.util.Scanner;

public class Only3Sum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
//		int k = in.nextInt();
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = in.nextInt();
		System.out.println("Origin arr : ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		quickSort(arr, 0, n-1);
//		System.out.println(sumCounter(arr, k));
	}
	
	public static void quickSort(int[] arr, int begin, int end) {
		if(begin > end)
			return;
		int l = begin;
		int r = end;
		int pivot = arr[l];
		while(l<r) {
			while(l<r && arr[r] > pivot)
				r--;
			while(l<r && arr[l] <= pivot)
				l++;
			if(l<r) {
				int temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		
		int temp = arr[begin];
		arr[begin] = arr[l];
		arr[l] = temp;
		
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		quickSort(arr, begin, l-1);
		quickSort(arr, l+1, end);
	}
	
	public static long sumCounter(int[] arr, int k) {
		long count = 0;
		int length = arr.length;
		for(int i=0; i<length - 2; i++) {
			int rest = k - arr[i];
			for(int l=i + 1; l<length - 1; l++) {
				int rightNumber = rest - arr[l];
				int r = binarySort(rightNumber, arr);
				while((l+1) < r) {
					if(arr[r - 1] == rightNumber)
						count++;
					r--;
				}
			}
		}
		return count;
	}
	
	public static int binarySort(int number, int[] arr) {
		int temp = number;
		int left = 0;
		int center = 0;
		int right = arr.length - 1;
		while(left <= right) {
			center = left + (right-left) / 2;
			if(temp < arr[center])
				right = center - 1;
			else
				left = center + 1;
		}
		return left;
	}
}
