package lab5;

import java.util.Scanner;

public class MatchingProblem {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			String str1 = in.next();
			String str2 = in.next();
			
			boolean wildCardCheck = false;
			int wildCardIndex = -1;
			boolean matchCheck = false;
			
			char[] sArr = str1.toCharArray();
//			char[] tArr = str2.toCharArray();
			for(int i=0; i<n; i++) {
				if(sArr[i] == '*') {
					wildCardCheck = true;
					wildCardIndex = i;
					break;
				}
			}
			if(wildCardCheck) {
				if(wildCardIndex == 0) {
					if(n > m+1) {
						
					} else {
						String temp1 = str1.substring(1, n);
						String temp2 = str2.substring(m-n+1,m);
						if(temp1.equals(temp2))
							matchCheck = true;
					}
				} else if(wildCardIndex == n-1) {
					if(wildCardIndex > m) {
						
					} else {
						String temp1 = str1.substring(0, n-1);
						String temp2 = str2.substring(0, n-1);
						if(temp1.equals(temp2))
							matchCheck = true;
					}
				} else {
					if(wildCardIndex+1 > m) {
						
					} else {
						String temp1Pre = str1.substring(0, wildCardIndex);
						String temp1Post = str1.substring(wildCardIndex+1, n);
						String temp2Pre = str2.substring(0, wildCardIndex);
							if(temp1Post.length()+temp2Pre.length()>m) {
								
							} else {
								String temp2Post = str2.substring(m-temp1Post.length(), m);
								if(temp1Pre.equals(temp2Pre) && temp1Post.equals(temp2Post))
									matchCheck = true;
							}
					}
				}
			} else {
				if(str1.equals(str2))
					matchCheck = true;
			}
			if(matchCheck)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
