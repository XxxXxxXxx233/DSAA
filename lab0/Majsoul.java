package lab0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Majsoul {
	
	public static String[] majsoulArr = {"W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "Y1", "Y2", "Y3", "Y4", "Y5", "Y6", "Y7", "Y8", "Y9", "E", "S", "W", "N", "B", "F", "Z"};
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			ArrayList<Integer> arr = new ArrayList<>();
			for(int i=0; i<13; i++) {
				String mahjong = in.next();
				for(int j=0; j<majsoulArr.length; j++) {
					if(majsoulArr[j].equals(mahjong)) {
						arr.add(j);
						break;
					}
				}
			}
			Collections.sort(arr);
			for(int k=0; k<arr.size(); k++) {
				System.out.print(majsoulArr[arr.get(k)]+" ");
			}
			System.out.println();
		}
	}
}
