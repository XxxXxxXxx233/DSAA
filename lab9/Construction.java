package lab9;

import java.io.*;
import java.util.*;

public class Construction {
	
	private static int[][] A;
	private static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		A = new int[n][n];
		
		int minFrom = -1;
		int minTo = -1;
		int minValue = MAX;
		edgeB[] arr = new edgeB[m];
		for(int i=0; i<m; i++) {
			int from = in.nextInt() - 1;
			int to = in.nextInt() - 1;
			int value = in.nextInt();
			if(value < minValue) {
				minFrom = from;
				minTo = to;
				minValue = value;
			}
			arr[i] = new edgeB(from, to, value);
		}
		
		createGraph(arr);
		
		for(int j=0; j<n; j++) {
			for(int k=0; k<n; k++) {
				if(A[j][k] == 0) {
					A[j][k] = MAX;
				}
			}
		}
		
		long sum = prim(minFrom, minTo, minValue);
		out.println(sum);
		
		out.close();
	}
	
	public static long prim(int minFrom, int minTo, int minValue) {
		long sum = 0;
		int length = A.length;
		boolean[] visited = new boolean[length];
		int[] dist = new int[length];
		visited[minFrom] = true;
		for(int i=0; i<length; i++) {
			dist[i] = A[minFrom][i];
		}
		while(true) {
			int min = MAX;
			int index = -1;
			for(int j=0; j<length; j++) {
				if(!visited[j] && dist[j] < min) {
					min = dist[j];
					index = j;
				}
			}
			if(index == -1) {
				break;
			} else {
				visited[index] = true;
				sum += min;
				for(int k=0; k<length; k++) {
					if(!visited[k] && dist[k] > A[index][k]) {
						dist[k] = A[index][k];
					}
				}
			}
		}
		
		return sum;
	}
	
	public static void createGraph(edgeB[] e) {
		for(int i=0; i<e.length; i++) {
			int from = e[i].from;
			int to = e[i].to;
			int value = e[i].value;
			A[from][to] = value;
			A[to][from] = value;
		}
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

class edgeB{
	int from;
	int to;
	int value;
	
	public edgeB(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}

