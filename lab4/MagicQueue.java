package lab4;

import java.util.Scanner;

public class MagicQueue {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int front = 0;
		int rear = 0;
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			String operation = in.next();
			switch(operation) {
			case "E":
				if(rear < n) {
					arr[rear] = in.nextInt();
					rear++;
				}
				break;
			case "D":
				if(front < rear) {
					front++;
				}
				break;
			case "A":
				System.out.println(arr[front]);
				break;
			}
		}
		for(int i=front; i<rear; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
