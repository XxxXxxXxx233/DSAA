package lab2;

import java.util.Scanner;

public class LuckyNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = in.nextInt();
		quickSort(arr, 0, n-1);
		System.out.println(arr[k-1]);
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
		
		quickSort(arr, begin, l-1);
		quickSort(arr, l+1, end);
	}
}
