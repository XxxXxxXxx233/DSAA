package lab8;

import java.io.*;
import java.util.*;

public class Template {
	
	private static int[][] A;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			
			A = new int[n][n];
			
			edgeTemp[] e = new edgeTemp[m];
			for(int i=0; i<m; i++) {
				int from = in.nextInt();
				int to = in.nextInt();
				e[i] = new edgeTemp(from, to);
			}
			createGraph(e);
		}
		
		out.close();
	}
	
	public static void createGraph(edgeTemp[] e) {
		for(int i=0; i<e.length; i++) {
			int from = e[i].from - 1;
			int to = e[i].to - 1;
			int value = e[i].value;
			if(A[from][to] == 0) {
				A[from][to] = value;
			}
		}
	}
	
	public static void bfs(int value) {
		int verNum = A.length;
		boolean[] visited = new boolean[verNum];
		int[] queue = new int[verNum];
		int front = 0;
		int rear = 0;
		visited[value] = true;
		queue[rear] = value;
		rear++;
		System.out.println(value);
		while(front < verNum) {
			int cur = queue[front];
			front++;
			for(int i=0; i<verNum; i++) {
				if(A[cur][i] != 0 && !visited[i]) {
					System.out.println(i);
					queue[rear] = i;
					rear++;
					visited[i] = true;
				}
			}
		}
	}
	
	public static void dfs(int value) {
		int length = A.length;
		boolean[] visited = new boolean[length];
		int top = -1;
		int[] stack = new int[length];
		top++;
		stack[top] = value;
		visited[value] = true;
		System.out.print(value+1 + " ");
		while(top != -1) {
			int cur = stack[top];
			boolean hasNext = false;
			for(int i=0; i<length; i++) {
				if(A[cur][i] != 0 && !visited[i]) {
					System.out.print(i+1 + " ");
					top++;
					stack[top] = i;
					visited[i] = true;
					hasNext = true;
					break;
				}
			}
			if(!hasNext) {
				top--;
			}
		}
		System.out.println();
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

class edgeTemp{
	int from;
	int to;
	int value;
	
	public edgeTemp(int from, int to) {
		this.from = from;
		this.to = to;
		this.value = 1;
	}
	
	public edgeTemp(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}
