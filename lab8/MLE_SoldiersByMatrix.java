package lab8;

import java.io.*;
import java.util.*;

public class MLE_SoldiersByMatrix {
	
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
			
			vertexEM[] v = new vertexEM[n];
			for(int i=0; i<n; i++) {
				v[i] = new vertexEM(i);
			}
			
			edgeEM[] e = new edgeEM[m];
			for(int i=0; i<m; i++) {
				int from = in.nextInt();
				int to = in.nextInt();
				e[i] = new edgeEM(to, from);
				
			}
			createGraph(e, v);
			
/*			for(int j=0; j<A.length; j++) {
				for(int k=0; k<A.length; k++) {
					System.out.print(A[j][k] + " ");
				}
				System.out.println();
			}*/
			
			PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2-o1;
				}
				
			});
			
			for(int i=0; i<n; i++) {
				if(v[i].in == 0) {
					q.add(i);
//					dfs(i);
				}
			}
			
			int[] ans = new int[n];
			int count = 0;
			
			while(!q.isEmpty()) {
				int pos = q.poll();	//biggest in the queue
				ans[count] = pos;
				count++;
				for(int i=0; i<A.length; i++) {
					if(A[pos][i] != 0) {
						v[i].in--;
						if(v[i].in == 0) {
							q.add(i);
						}
					}
				}
			}
			
			for(int i=n-1; i>=0; i--) {
				out.print((ans[i]+1) + " ");
			}
			out.println();
		}
		
		out.close();
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
	
	public static void createGraph(edgeEM[] e, vertexEM[] v) {
		for(int i=0; i<e.length; i++) {
			int from = e[i].from - 1;
			int to = e[i].to - 1;
			int value = e[i].value;
			if(A[from][to] == 0) {
				A[from][to] = value;
				v[from].out++;
				v[to].in++;
			}
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

class vertexEM{
	int in;
	int out;
	int value;
	
	public vertexEM(int value) {
		this.value = value;
	}
}

class edgeEM{
	int from;
	int to;
	int value;
	
	public edgeEM(int from, int to) {
		this.from = from;
		this.to = to;
		this.value = 1;
	}
	
	public edgeEM(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}