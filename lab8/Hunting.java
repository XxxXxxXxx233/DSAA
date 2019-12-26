package lab8;

import java.io.*;
import java.util.*;

public class Hunting {
	
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
			int q = in.nextInt();
			
			A = new int[n][n];
			
			vertexH[] v= new vertexH[n];
			for(int i=0; i<n; i++) {
				v[i] = new vertexH(i);
			}
			
			edge[] e = new edge[m];
			for(int i=0; i<m; i++) {
				int from = in.nextInt() - 1;
				int to = in.nextInt() - 1;
				int value = in.nextInt();
				e[i] = new edge(from, to, value);
				if(v[from].minChild == -1) {
					v[from].minChildEdge = value;
					v[from].minChild = to;
				} else {
					if(value < v[from].minChildEdge) {
						v[from].minChildEdge = value;
						v[from].minChild = to;
					}
				}
				v[from].childEdge.add(e[i]);
			}
			createGraph(e);
			
			for(int i=0; i<n; i++) {
				PriorityQueue<edge> child = v[i].childEdge;
				edge min = child.poll();
				while(!child.isEmpty()) {
					edge secMin = child.poll();
					if(v[min.to].minBro == -1) {
						v[min.to].minBroEdge = secMin.value;
						v[min.to].minBro = secMin.to;
					} else {
						if(secMin.value < v[min.to].minBroEdge) {
							v[min.to].minBroEdge = secMin.value;
							v[min.to].minBro = secMin.to;
						}
					}
					min = secMin;
				}
			}
			
			int max = 0;
			int[] require = new int[q];
			for(int i=0; i<q; i++) {
				int num = in.nextInt();
				require[i] = num;
				if(num > max) {
					max = num;
				}
			}
			
			int[] ans = new int[max];
/*			PriorityQueue<edge> queue = new PriorityQueue<>(new Comparator<edge>() {

				@Override
				public int compare(edge o1, edge o2) {
					return o1.value - o2.value;
				}
				
			});*/
			
			PriorityQueue<edge> queue = new PriorityQueue<>();
			
			
			
			for(int i=0; i<n; i++) {
				out.println("vertexH " + (v[i].value+1) + ": MinChild = " + (v[i].minChild+1) + " MinBro = " + (v[i].minBro+1));
			}
			for(int i=0; i<q; i++) {
				out.println(ans[require[i]-1]);
			}
		}
		
		out.close();
	}
	
	public static void createGraph(edge[] e) {
		for(int i=0; i<e.length; i++) {
			int from = e[i].from;
			int to = e[i].to;
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

class vertexH{
	int in;
	int out;
	int value;
	int minChild;
	int minChildEdge;
	int minBro;
	int minBroEdge;
	PriorityQueue<edge> childEdge = new PriorityQueue<>(new Comparator<edge>() {

		@Override
		public int compare(edge o1, edge o2) {
			return o1.value - o2.value;
		}
		
	});
	
	public vertexH(int value) {
		this.value = value;
		this.minChild = -1;
		this.minBro = -1;
	}
}

class edge{
	int from;
	int to;
	int value;
	
	public edge(int from, int to) {
		this.from = from;
		this.to = to;
		this.value = 1;
	}
	
	public edge(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}

