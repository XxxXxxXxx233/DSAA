package lab8;

import java.io.*;
import java.util.*;

public class DefensiveTower {
	
	private static int[][] A;
	private static int count1 = 0;
	private static int count2 = 0;
	private static ArrayList<Integer> list = new ArrayList<>();
	
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
			
			edgeD[] e = new edgeD[m];
			for(int i=0; i<m; i++) {
				int from = in.nextInt();
				int to = in.nextInt();
				e[i] = new edgeD(from, to);
			}
			createGraph(e);
			
			int begin = 0;
			boolean[] printed = bfs(begin);
			String answer = "";
			count2 = A.length - count1;		//false number
			if(count1 > count2) {
				for(int i=0; i<printed.length; i++) {
					if(!printed[i])
						answer += (i+1) + " ";
				}
				out.println(count2);
			} else {
				for(int i=0; i<printed.length; i++) {
					if(printed[i])
						answer += (i+1) + " ";
				}
				out.println(count1);
			}
			out.println(answer);
			
			count1 = 0;
			count2 = 0;
			list.clear();
		}
		
		out.close();
	}
	
	public static void createGraph(edgeD[] e) {
		for(int i=0; i<e.length; i++) {
			int from = e[i].from - 1;
			int to = e[i].to - 1;
			int value = e[i].value;
			A[from][to] = value;
			A[to][from] = value;
		}
	}
	
	public static boolean[] bfs(int value) {
		int verNum = A.length;
		boolean[] visited = new boolean[verNum];
		boolean[] printed = new boolean[verNum];
		int[] queue = new int[verNum];
		int front = 0;
		int rear = 0;
		visited[value] = true;
		queue[rear] = value;
		rear++;
		while(front < verNum) {
			int cur = queue[front];
			front++;
			for(int i=0; i<verNum; i++) {
				if(A[cur][i] != 0 && !visited[i]) {
					if(printed[cur]) {
						
					} else {
						printed[i] = true;
						count1++;
					}
					queue[rear] = i;
					rear++;
					visited[i] = true;
				}
			}
		}
		return printed;
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

class edgeD{
	int from;
	int to;
	int value;
	
	public edgeD(int from, int to) {
		this.from = from;
		this.to = to;
		this.value = 1;
	}
	
	public edgeD(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}