package lab6;

import java.util.Scanner;

public class PreInAndPost {
	public static int[] preOrder;
	public static int[] inOrder;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int number = in.nextInt();
			int[] preArr = new int[number];
			int[] inArr = new int[number];
			for(int i=0; i<number; i++) {
				preArr[i] = in.nextInt();
			}
			for(int i=0; i<number; i++) {
				inArr[i] = in.nextInt();
			}
			preOrder = preArr;
			inOrder = inArr;
			findPostOrder(0, 0, number);
			System.out.println();
		}
	}
	public static void findPostOrder(int preBegin, int inBegin, int length) {
		if(length <= 0) {
			return;
		}
		int num = preOrder[preBegin];
		int len = 0;
		int count = 0;
		for(int i=inBegin; count<length; i++) {
			if(inOrder[i] == num) {
				len = count;
				break;
			}
			count++;
		}
		findPostOrder(preBegin + 1, inBegin, len);
		findPostOrder(preBegin + len + 1, inBegin + len + 1, length - len - 1);
		System.out.print(num + " ");
	}
}
