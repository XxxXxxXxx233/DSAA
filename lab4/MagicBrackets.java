package lab4;

import java.util.Scanner;

public class MagicBrackets {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			String input = in.next();
			String[] arr = new String[n];
			for(int i=0; i<n; i++) {
				arr[i] = input.substring(i, i+1);
			}
			boolean check = true;
			String[] stackArr = new String[n];
			int top = -1;
			if(n == 1 || n % 2 == 1) {
				check = false;
			} else {
				for(int i=0; i<n; i++) {
					if(arr[i].equals("(") || arr[i].equals("{") || arr[i].equals("[")) {
						if(top != n-1) {
							top++;
							stackArr[top] = arr[i]; 
						}
					}
					if(arr[i].equals(")")) {
						if(top == -1) {
							check = false;
							break;
						} else {
							if(stackArr[top].equals("(")) {
								if(top != -1) {
									top--;
								}
							} else {
								check = false;
								break;
							}
						}
					} else if(arr[i].equals("}")) {
						if(top == -1) {
							check = false;
							break;
						} else {
							if(stackArr[top].equals("{")) {
								if(top != -1) {
									top--;
								}
							} else {
								check = false;
								break;
							}
						}
					} else if(arr[i].equals("]")) {
						if(top == -1) {
							check = false;
							break;
						} else {
							if(stackArr[top].equals("[")) {
								if(top != -1) {
									top--;
								}
							} else {
								check = false;
								break;
							}
						}
					}
				}
			}
			if(check) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
