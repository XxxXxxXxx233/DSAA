package lab2;

import java.util.Scanner;

public class VinceblackStore {
	public static long cost = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = in.nextInt();
		}
		mergeSort(arr, 0, n-1);
		System.out.print(cost);
	}
	
	public static void mergeSort(int[] arr, int l, int r) {
		if(l < r) {
			 int center = l + (r - l) / 2;
			 mergeSort(arr, l, center);
			 mergeSort(arr, center + 1, r);
			 merge(arr, l, center, r);
		}
	}
	
	public static void merge(int[] arr, int l, int center, int r) {
		int leftLength = center - l + 1;
		int rightLength = r - center;
		int[] leftArr = new int[leftLength];
		int[] rightArr = new int[rightLength];
		for(int i=0; i<leftLength; i++)
			leftArr[i] = arr[l + i];
		for(int i=0; i<rightLength; i++)
			rightArr[i] = arr[center + 1 + i];
	
		int i = 0;
		int j = 0;
		int k = l;
		
		while(i<leftLength && j<rightLength) {
			if(leftArr[i] <= rightArr[j]) {
				cost += (long)leftArr[i] * (long)(j-0);
				arr[k] = leftArr[i];
				i++;
				k++;
			} else {
				cost += (long)rightArr[j] * (long)(leftLength-i);
				arr[k] = rightArr[j];
				j++;
				k++;
			}
		}
		while(i<leftLength) {
			cost += (long)leftArr[i] * (long)(j-0);
			arr[k] = leftArr[i];
			i++;
			k++;
		}
		while(j<rightLength) {
			cost += (long)rightArr[j] * (long)(leftLength-i);
			arr[k] = rightArr[j];
			j++;
			k++;
		}
	}
}
