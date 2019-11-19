package lab5;

import java.util.Scanner;

public class Stick {
	public static int base = 131;
	public static int result = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		String t = in.next();
		int sLen = s.length();
		int tLen = t.length();
		if(sLen > tLen) {	//find t in s
			int high = tLen;
			int longer = sLen;
			findStickLevel(t, s, high, longer);
		} else {			//find s in t
			int high = sLen;
			int longer = tLen;
			findStickLevel(s, t, high, longer);
		}
	}
	
	//find s in t
	public static void findStickLevel(String s, String t, int high, int longer) {
		if(judge(s, t, high, longer)) {
			
		} else {
			int left = 0;
			int right = high-1;
			while(left <= right) {
				int center = left + (right - left) / 2;
				if(judge(s, t, center, longer)) {
					left = center + 1;
				} else {
					right = center - 1;
				}

			}
		}
		System.out.println(result);
	}
	
	//find s in t
	public static boolean judge(String s, String t, int high, int longer) {
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		
		long m = 1;
		long sH = 0;
		long tH = 0;
		for(int i=0; i<high; i++) {
			sH = (sH * base + sArr[i]);
			tH = (tH * base + tArr[i]);
			m = (m * base); 
		}
		int length = longer - high + 1;
		long[] hashArr = new long[length];
		hashArr[0] = tH;
		for(int i=1; i<length; i++) {
			hashArr[i] = (hashArr[i-1] * base + tArr[i+high-1] - tArr[i-1] * m);
			
		}

		quickSort(hashArr, 0, length-1);

		boolean check = hashCheck(s, sH, sArr, high, hashArr, m);
		return check;
	}
	
	public static boolean hashCheck(String s, long hash, char[] afterArr, int length, long[] hashArr, long m) {
		int arrLength = afterArr.length;
		int count = 0;
		int repTime = arrLength - length + 1;
		while(count < repTime) {
			int position = binarySort(hash, hashArr);
			if(position > 0) {
				if(hash == hashArr[position-1]) {
					result = length;
					return true;
				}
			}
			if(count < repTime - 1) {
				hash = (hash * base + afterArr[count+length] - afterArr[count] * m);
			}
			count++;
		}
		
		return false;
	}
	
	public static void quickSort(long[] hashArr, int begin, int end) {
		if(begin > end)
			return;
		int l = begin;
		int r = end;
		long pivot = hashArr[l];
		while(l<r) {
			while(l<r && hashArr[r] > pivot)
				r--;
			while(l<r && hashArr[l] <= pivot)
				l++;
			if(l<r) {
				long temp = hashArr[r];
				hashArr[r] = hashArr[l];
				hashArr[l] = temp;
			}
		}
		
		long temp = hashArr[begin];
		hashArr[begin] = hashArr[l];
		hashArr[l] = temp;
		
		quickSort(hashArr, begin, l-1);
		quickSort(hashArr, l+1, end);
	}
	
	public static int binarySort(long value, long[] arr) {
		long temp = value;
		int left = 0;
		int center = 0;
		int right = arr.length - 1;
		while(left <= right) {
			center = (left + right) / 2;
			if(temp < arr[center])
				right = center - 1;
			else
				left = center + 1;
		}
		return left;
	}
}
