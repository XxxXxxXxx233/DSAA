package lab3;

import java.util.Scanner;

public class addPolynomials {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int[][] polyA = new int[1001][2];
			int[][] polyB = new int[1001][2];
			
			int n = in.nextInt();
			for(int i=0; i<n; i++) {
				int co = in.nextInt();
				int exp = in.nextInt();
				polyA[i][0] = co;
				polyA[i][1] = exp;
			}
			int m = in.nextInt();
			for(int i=0; i<m; i++) {
				int co = in.nextInt();
				int exp = in.nextInt();
				polyB[i][0] = co;
				polyB[i][1] = exp;
			}
			int q = in.nextInt();
			for(int i=0; i<q; i++) {
				int exp = in.nextInt();
				int answer = 0;
				for(int j=0; j<1001; j++) {
					if(polyA[j][1] == exp)
						answer += polyA[j][0];
					if(polyB[j][1] == exp)
						answer += polyB[j][0];
				}
				System.out.print(answer + " ");
			}
			System.out.println();
		}
	}
}
