package lab5;

import java.io.*;
import java.util.*;

public class Repeat {
	public static int count = 0;
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		int N = in.nextInt();
		for(int i=0; i<N; i++) {
			String str1 = in.next();
			String str2 = in.next();
			KMPSearch(str2, str1);
		}
		out.println(count);
		out.close();
	}
	
	public static void KMPSearch(String s, String t) {
		int sLen = s.length();
		int tLen = t.length();
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		int i = 0;
		int j = 0;
		int[] next = next(tArr, tLen);
		while(i < sLen && j < tLen) {
			if(j == -1 || sArr[i] == tArr[j]) {
				i++;
				j++;
				if(j >= sLen/3) {
					count++;
					return;
				}
			} else {
				j = next[j];
			}
		}
		if(j == tLen) {
			count++;
		}
	}
	
	
	
	public static int[] next(char[] tArr, int tLen) {
		int[] next = new int[tLen];
		int j = 0;
		int k = -1;
		next[0] = -1;
		while(j < tLen - 1) {
			if(k == -1 || tArr[j] == tArr[k]) {
				k++;
				j++;
				next[j] = k;
			} else {
				k = next[k];
			}
		}
		for(int i=0; i<next.length; i++) {
			System.out.print(next[i] + " ");
		}
		System.out.println();
		return next;
	}
	
	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public char[] nextCharArray() {
			return next().toCharArray();
		}
	}
}
