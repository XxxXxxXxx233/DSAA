package Bouns;

import java.util.Scanner;

public class LanranProwssad {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int num = n-m+1;
		String[] input = new String[num];
		char[] ans = new char[n];
		int[][] arr = new int[n][2*m];
		for(int i=0; i<num; i++) {
			input[i] = in.next();
		}
		for(int i=0; i<n; i++) {
			char max = 0;
			int maxCount = 0;
			int[] count = new int[2000];
			for(int j=0; j<num; j++) {
				char cur = input[j].charAt(i);
				count[cur]++;
				if(count[cur] > maxCount) {
					maxCount = count[cur];
					max = cur;
				}
			}
			ans[i] = max;
		}
		for(int i=0; i<n; i++) {
			System.out.print(ans[i]);
		}
		System.out.println();
	}
}
