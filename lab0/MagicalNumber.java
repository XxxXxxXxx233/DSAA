package lab0;

import java.util.Scanner;

public class MagicalNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			String number = in.next();
			int length = number.length();
			int symmetryNumber = 0;
			if(length == 1) {
				symmetryNumber = Integer.parseInt(number) + 1;
			} else if(length == 2) {
				for(int i=0; i<=Integer.parseInt(number); i++) {
					if(isSymmetry(i))
						symmetryNumber++;
				}
			} else {
				for(int i=1; i<length; i++) {
					symmetryNumber += findSymmetryInDifferentLength(length-i);
				}
				symmetryNumber += findSymmetryInSameLength(number);
			}
			System.out.println(symmetryNumber);
		}
	}
	
	public static int findSymmetryInDifferentLength(int length) {
		int count = 0;
		if(length < 2) {
			count = 10;
		} else if(length < 3) {
			count = 9;
		} else if(length % 2 == 1) {
			count = (int)(9 * Math.pow(10, (length-1) / 2));
		} else {
			count = (int)(9 * Math.pow(10, length/2 - 1));
		}
		return count;
	}
	
	public static int findSymmetryInSameLength(String number) {
		int count = 0;
		int length = number.length();
		String[] word = new String[length];
		for(int i=0; i<length; i++)
			word[i] = number.substring(i, i+1);
		int checkNumber = length / 2 + length % 2;
		for(int i=0; i<checkNumber; i++) {
			if(i==0) {
				if(Integer.parseInt(word[i]) > 1)
					count += (int)(Math.pow(10, checkNumber - (i + 1))) * (Integer.parseInt(word[i])-1);
			} else if (i == checkNumber - 1) {
				if(length % 2 == 1) {
					long tou = Long.parseLong(number.substring(0, length/2));
					long wei = Long.parseLong(number.substring(length/2 + 1, length));
					if(reverse(tou) > wei)
						count += Integer.parseInt(word[i]);
					else
						count += Integer.parseInt(word[i]) + 1;
				} else {
					long tou = Long.parseLong(number.substring(0, length/2));
					long wei = Long.parseLong(number.substring(length/2, length));
					if(reverse(tou) > wei)
						count += Integer.parseInt(word[i]);
					else
						count += Integer.parseInt(word[i]) + 1;
				}
			} else {
				count += (int)(Math.pow(10, checkNumber - (i + 1))) * (Integer.parseInt(word[i]));
			}
		}
		return count;
	}
	
	public static boolean isSymmetry(long m) {
		long origin = m;
		long reverse = 0;
		while(m!=0) {
			reverse = reverse*10 + m%10;
			m = m/10;
		}
		return reverse == origin;
	}
	
	public static long reverse(long m) {
		long reverse = 0;
		while(m!=0) {
			reverse = reverse*10 + m%10;
			m = m/10;
		}
		return reverse;
	}
}
