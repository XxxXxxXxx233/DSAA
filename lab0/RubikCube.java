package lab0;

import java.util.Scanner;

public class RubikCube {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		String answer = "";
		for(int i=0; i<T; i++) {
			for(int j=0; j<9; j++) {
				String temp = in.next().trim();
				if(j == 4) {
					answer = temp;
				}
			}
			System.out.println(answer);
		}
	}
}
