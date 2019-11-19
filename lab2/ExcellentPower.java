package lab2;

import java.util.Scanner;

public class ExcellentPower {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int numberOfDouble = in.nextInt();
		int numberOfEqual = in.nextInt();
		long totalAttack = 0;
		long[][] soldier = new long[n][2];
		for(int t=0; t<n; t++) {
			int hp = in.nextInt();
			int attack = in.nextInt();
			soldier[t][0] = hp;
			soldier[t][1] = attack;
			totalAttack += attack;
		}
		
		long powerNumber = 1;
		for(int i=0; i<numberOfDouble; i++)
			powerNumber *= 2;
		
		if(numberOfEqual >= n) {
			for(int i=0; i<n; i++) {
				long diff = soldier[i][0] - soldier[i][1];
				if(diff > 0) {
					soldier[i][1] = soldier[i][0];
					totalAttack += diff;
				}
			}
			
			long diffAfterBiggest = 0;
			for(int i=0; i<n; i++) {
				soldier[i][0] *= powerNumber;
				long temp = soldier[i][0] - soldier[i][1];
				if(temp > diffAfterBiggest){
					diffAfterBiggest = temp;
				}
			}
			if(diffAfterBiggest > 0 && numberOfEqual > 0) {
				totalAttack += diffAfterBiggest;
			}
			
		} else {
			long[][] diffValue = new long[n][2];
			for(int i=0; i<n; i++) {
				diffValue[i][0] = soldier[i][0] - soldier[i][1];
				diffValue[i][1] = i;
			}
			quickSort(diffValue, 0, n-1);
			
			long[][] diffAfter = new long[n][2];
			for(int i=0; i<n; i++) {
				soldier[i][0] *= powerNumber;
				diffAfter[i][0] = soldier[i][0] - soldier[i][1];
				diffAfter[i][1] = i;
			}
			quickSort(diffAfter, 0, n-1);
			
/*			for(int i=0; i<n; i++)
				System.out.println(diffAfter[i][0] + " " + diffAfter[i][1] + " soldier!");
			System.out.println("BiggestDiff after power = " + diffAfter[n-1][0]);*/
			
			if(diffAfter[n-1][0] > 0 && numberOfEqual > 0) {
				totalAttack += diffAfter[n-1][0];
				numberOfEqual--;
			}
			
			for(int i=n-1; i>=0; i--) {
				if(numberOfEqual <= 0)
					break;
				if(diffValue[i][1] != diffAfter[n-1][1] && diffValue[i][0] >= 0) {
//					System.out.println("diffValue[i][0] = " + diffValue[i][0]);
					totalAttack += diffValue[i][0];
					numberOfEqual--;
				}
			}
		}
		
		System.out.println(totalAttack);
	}
	
	public static void quickSort(long[][] arr, int begin, int end) {
		if(begin > end)
			return;
		int l = begin;
		int r = end;
		long pivot = arr[l][0];
		while(l<r) {
			while(l<r && arr[r][0] > pivot)
				r--;
			while(l<r && arr[l][0] <= pivot)
				l++;
			if(l<r) {
				long[] temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		
		long temp[] = arr[begin];
		arr[begin] = arr[l];
		arr[l] = temp;
		
		quickSort(arr, begin, l-1);
		quickSort(arr, l+1, end);
	}
}
