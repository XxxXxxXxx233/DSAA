package Bouns;

import java.util.Scanner;

public class ThreeIsBetterThanTwo {
	
	private static long num = 2147483231;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			long s1 = in.nextInt();
			long s2 = in.nextInt();
			long s3 = in.nextInt();
			long sEnd = in.nextInt();
			long[] arr = new long[3];
			int[] count = new int[3];
			arr[0] = s1;
			arr[1] = s2;
			arr[2] = s3;
			while(arr[0] != sEnd) {
				count[0]++;
				arr[0] = (arr[0]*arr[0])%num;
			}
			while(arr[1] != sEnd) {
				count[1]++;
				arr[1] = (arr[1]*arr[1])%num;
			}
			while(arr[2] != sEnd) {
				count[2]++;
				arr[2] = (arr[2]*arr[2])%num;
			}
			
			if((count[0] == count[1]) && (count[1] == count[2])) {
				System.out.println(sEnd);
				continue;
			}
			
			int max = 0;
			int maxIndex = -1;
			int sec = 0;
			int secIndex = -1;
			int thr = Integer.MAX_VALUE;
			int thrIndex = -1;
			for(int i=0; i<3; i++) {
				if(count[i] > max) {
					max = count[i];
					maxIndex = i;
				}
				if(count[i] <= thr) {
					thr = count[i];
					thrIndex = i;
				}
			}
			for(int i=0; i<3; i++) {
				if(i != maxIndex && i != thrIndex) {
					sec = count[i];
					secIndex = i;
					break;
				}
			}
			
			arr[0] = s1;
			arr[1] = s2;
			arr[2] = s3;
			int tempCount1 = max - thr;
			int tempCount2 = sec - thr;
			for(int i=0; i<tempCount1; i++) {
				arr[maxIndex] = (arr[maxIndex]*arr[maxIndex])%num;
			}
			for(int i=0; i<tempCount2; i++) {
				arr[secIndex] = (arr[secIndex]*arr[secIndex])%num;
			}
			for(int i=0; i<thr; i++) {
				if(arr[0] == arr[1] && arr[1] == arr[2])
					break;
				arr[0] = (arr[0]*arr[0])%num;
				arr[1] = (arr[1]*arr[1])%num;
				arr[2] = (arr[2]*arr[2])%num;
			}
			System.out.println(arr[0]);
		}
	}
}
