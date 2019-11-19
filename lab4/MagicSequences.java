package lab4;

import java.util.Scanner;

public class MagicSequences {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = 2000000;
		int front = 0;
		int rear = 0;
		int[][] arr = new int[n][2];	
		int count = 0;
		int num = in.nextInt();
		int answer = 0;
		while(num != -1 && count < m) {
			if(rear < n) {
				arr[rear][0] = num;
				arr[rear][1] = count;
				if(rear != 0) {
					while(num > arr[rear-1][0] && rear > front) {
						arr[rear][0] = 0;
						arr[rear][1] = 0;
						rear--;
						if(rear == 0)
							break;
					}
					arr[rear][0] = num;
					arr[rear][1] = count;
				}
			}
			count++;
			rear++;
			num = in.nextInt();
		}
/*		for(int i=0; i<rear; i++) {
			System.out.print(arr[i][0] + "  ");
		}
		System.out.println();*/
		int biggest = 0;
		int biggestIndex = 0;
		for(int i=0; i<rear; i++) {
			if(arr[i][0] > biggest) {
				biggest = arr[i][0];
				biggestIndex = i;
			}
		}
		int magicCount = 0;
		front = biggestIndex;
		answer ^= biggest;
//		System.out.println("answer = " + answer);
		while(num != -1) {
//			System.out.println("num = " + num + " " + count);
			if(magicCount >= arr[front][1] || arr[front+1][1] - arr[front][1] > m-1) {
				front++;
			}
			if(rear < n) {
				arr[rear][0] = num;
				arr[rear][1] = count;
				if(rear >= 1) {
					while(num > arr[rear-1][0] && rear > front) {
						arr[rear][0] = 0;
						arr[rear][1] = 0;
						rear--;
						if(rear == 0)
							break;
					}
					arr[rear][0] = num;
					arr[rear][1] = count;
				}
//				System.out.println("front = " + front);
//				System.out.println("rear = " + rear);
				magicCount++;
				count++;
				rear++;
/*				for(int i=0; i<rear; i++) {
					System.out.print(arr[i][0] + "  ");
				}
				System.out.println();
				System.out.println("arr[front][0] = " + arr[front][0] + "  " + arr[front][1]);*/
				answer ^= arr[front][0];
			}
//			System.out.println("magicCount = " + magicCount);
			num = in.nextInt();
//			System.out.println();
		}
		System.out.println(answer);
	}
}
