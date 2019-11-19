package lab4;

import java.io.*;
import java.util.*;

public class ExcitingSpider {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int[] arr = new int[n];
			int front = 0;
			int rear = 0;
			while(rear < n) {
				arr[rear] = in.nextInt();
				rear++;
			}
			boolean[] check = new boolean[n+1];
			int[] stackArr = new int[n];
			int[] output = new int[n];
			int top = -1;
			int number = 1;
			int count = 0;
			while(top != n-1) {
				top++;
				stackArr[top] = arr[front];
				check[arr[front]] = true;
				if(stackArr[top] == number) {
					output[count] = number;
					top--;
					count++;
					if(number != n)
						number++;
					while(check[number] != false && number < n) {
						number++;
					}
				}
				if(top != -1) {
					while(number > stackArr[top]) {
						output[count] = stackArr[top];
						top--;
						count++;
						if(top == -1)
							break;
					}
				}
				front++;
				if(front == n)
					break;
			}
			for(int i=0; i<count; i++)
				out.print(output[i] + " ");
			while(top != -1) {
				out.print(stackArr[top] + " ");
				top--;
			}
			out.println();
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
