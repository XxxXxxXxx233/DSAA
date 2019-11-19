package lab4;

import java.io.*;
import java.util.*;

public class MagicNumber {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int[] OriginArr = new int[n];
			int[] left = new int[n];
			int[] right = new int[n];
			int front = 0;
			int rear = 0;
			while(rear < n) {
				OriginArr[rear] = in.nextInt();
				left[rear] = -1;
				right[rear] = -1;
				rear++;
			}
			//find right
			int[][] rightStack = new int[n][2];
			int top = -1;
			int count = 0;
			while(count != n) {
				int num = OriginArr[front];
				boolean check = false;
				if(top >= 0 && num > rightStack[top][0])
					check = true;
				while(top >= 0 && num > rightStack[top][0]) {
					top--;
				}
				top++;
				rightStack[top][0] = num;
				rightStack[top][1] = count;
				if(top != 0) {
					if(check) {
						for(int i=0; i<top; i++) {
							int tempRight = right[rightStack[i][1]];
							if(tempRight == -1)
								right[rightStack[i][1]] = count;
							else
								if(num > OriginArr[tempRight])
									right[rightStack[i][1]] = count;
						}
					} else {
						right[rightStack[top-1][1]] = count;
					}
				}
				front++;
				count++;
			}
			
			//find left
			int[][] leftStack = new int[n][2];
			top = -1;
			count = n-1;
			rear--;
			while(count != -1) {
				int num = OriginArr[rear];
				boolean check = false;
				if(top >= 0 && num > leftStack[top][0])
					check = true;
				while(top >= 0 && num > leftStack[top][0]) {
					top--;
				}
				top++;
				leftStack[top][0] = num;
				leftStack[top][1] = count;
				if(top != 0) {
					if(check) {
						for(int i=0; i<top; i++) {
							int templeft = left[leftStack[i][1]];
							if(templeft == -1)
								left[leftStack[i][1]] = count;
							else
								if(num > OriginArr[templeft])
									left[leftStack[i][1]] = count;
						}
					} else {
						left[leftStack[top-1][1]] = count;
					}
				}
				rear--;
				count--;
			}
			
			out.println("Case " + (t+1) + ":");
			for(int i=0; i<n; i++) {
				out.println((left[i]+1) + " " + (right[i]+1));
			}
		}
		out.close();
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
