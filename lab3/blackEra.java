package lab3;

import java.util.Scanner;

public class blackEra {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			int M = in.nextInt();
			int[] arr = new int[N];
			int[][] info = new int[N][3];
			
			for(int i=0; i<N; i++) {
				arr[i] = in.nextInt();
			}
			int begin = arr[0];
			for(int i=0; i<N; i++) {
				int pos = arr[i];
				info[pos][0] = i;
				if(pos == 0)
					info[pos][1] = -1;
				else
					info[pos][1] = arr[i-1];
				if(pos == N-1)
					info[pos][2] = -1;
				else
					info[pos][2] = arr[i+1];
			}

			for(int i=0; i<M; i++) {
				int x1 = in.nextInt();
				int y1 = in.nextInt();
				int x2 = in.nextInt();
				int y2 = in.nextInt();
				
				int temp1 = info[x1][1];
				info[info[x2][1]][2] = x1;
				int temp2 = info[x2][1];
				info[x2][1] = info[x1][1];
				info[x1][1] = temp2;
				if(info[x2][1] != -1) {
					info[temp1][2] = x2;
				} else {
					begin = x2;
				}
				
				int temp3 = info[y1][2];
				info[info[y1][2]][1] = y2;
				int temp4 = info[y2][2];
				info[y1][2] = info[y2][2];
				info[y2][2] = temp3;
				if(info[y1][2] != -1)
					info[temp4][1] =  y1;
				
/*				for(int j=0; j<N; j++) {
					System.out.println("The number before and after " + j + " is: " + info[j][1] + " and " + info[j][2]);
				}*/
			}
			
			
			System.out.print(begin + " ");
			for(int i=1; i<N; i++) {
				begin = info[begin][2];
				System.out.print(begin + " ");
			}
			System.out.println();
		}
	}
}
