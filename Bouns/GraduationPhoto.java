package Bouns;

import java.util.Scanner;

public class GraduationPhoto {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int length = in.nextInt();
			String str = in.next();
			if(length == 1) {
				System.out.println(str);
			} else {
				char[] arr = str.toCharArray();
				quickSort(arr, 0, length-1);
				
				label1:
				while(true) {
					for(int i=0; i<length; i++) {
						System.out.print(arr[i]);
					}
					System.out.println();
					
					int index = 0;
					int index2 = length - 1;
					for(int i=length-1; i>0; i--) {
						if(arr[i-1] < arr[i]) {
							index = i-1;
							break;
						}
						else if(i == 1)		//don't find
							break label1;
					}
					for(int i=length-1; i>=0; i--) {
						if(arr[i] > arr[index]) {
							index2 = i;
							break;
						}
					}
					char temp=arr[index2];
					arr[index2]=arr[index];
					arr[index]=temp;
					for(int i=index+1, j=length-1; i<j; i++, j--) {
						char temp1 = arr[j];
						arr[j] = arr[i];
						arr[i] = temp1;
					}
				}
			}
		}
	}
		
	public static void quickSort(char[] arr, int begin, int end) {
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
				char temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		
		char temp = arr[begin];
		arr[begin] = arr[l];
		arr[l] = temp;
		
		quickSort(arr, begin, l-1);
		quickSort(arr, l+1, end);
	}
}
