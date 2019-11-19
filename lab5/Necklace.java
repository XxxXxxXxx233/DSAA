package lab5;

import java.util.Scanner;

public class Necklace {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			String str = in.next();
			int length = str.length();
			int number = 0;
			if(length == 1) {
				number = 1;
			} else if(length == 2){
				if(str.substring(0, 1).equals(str.substring(1, 2))) {
					number = 0;
				} else {
					number = 2;
				}
			} else {
				char[] strArr = str.toCharArray();
				int[] arr = next(strArr, length);
				int specialLen = length - arr[length-1];
				int counter = length / specialLen;
				int rest = length % specialLen;
				if(rest == 0 && counter >= 2) {
					number = 0;
				} else {
					number = specialLen - rest;
				}
			}
			System.out.println(number);
		}
	}
	
	public static int[] next(char[] strArr, int strLen) {
		int[] next = new int[strLen];
		next[0] = 0;
		for(int i=1; i<strLen; i++) {
			int position = next[i-1];
			if(strArr[i] == strArr[position]) {
				next[i] = next[i-1] + 1;
			} else {
				while(strArr[i] != strArr[position] && position != 0) {
					position = next[position-1];
					if(strArr[i] == strArr[position]) {
						next[i] = position + 1;
						break;
					}
				}
			}
		}
		return next;
	}
}
 