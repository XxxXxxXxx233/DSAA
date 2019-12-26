package lab8;

import java.io.*;
import java.util.*;

public class Palace {
	
	private static int[][] A;
	private static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			A = new int[n][n];
			
			int[][] para = new int[n][3];
			vertexF[] v = new vertexF[n];
			for(int i=0; i<n; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int h = in.nextInt();
				para[i][0] = x;
				para[i][1] = y;
				para[i][2] = h;
				v[i] = new vertexF(h);
			}
			
			ArrayList<edgeF> e = new ArrayList<>();
			for(int j=0; j<n; j++) {
				int x1 = para[j][0];
				int y1 = para[j][1];
				for(int k=0; k<n; k++) {
					if(k == j)
						continue;
					int x2 = para[k][0];
					int y2 = para[k][1];
					if((x1 > x2 && y1 > y2) || (x1 > y2 && y1 > x2)) {
						e.add(new edgeF(j, k));
					}
				}
			}
			createGraph(e, v);
			
			PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o1-o2;
				}
			});
			
			for(int i=0; i<n; i++) {
				if(v[i].in == 0) {
					q.add(i);
					v[i].max = v[i].value;
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
						if(v[i].value + v[pos].max > v[i].max) {
							v[i].max = v[i].value + v[pos].max;
						}
						if(v[i].in == 0) {
							q.add(i);
						}
					}
				}
			}
			int max = 0;
			for(int i=0; i<n; i++) {
				if(v[i].max > max)
					max = v[i].max;
			}
			out.println(max);
		}
		out.close();
	}
	
	public static void createGraph(ArrayList<edgeF> e, vertexF[] v) {
		for(int i=0; i<e.size(); i++) {
			int from = e.get(i).from;
			int to = e.get(i).to;
			int value = 1;
			if(A[from][to] == 0) {
				A[from][to] = value;
				v[from].out++;
				v[to].in++;
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

class vertexF{
	int in;
	int out;
	int value;
	int max;
	
	public vertexF(int value) {
		this.value = value;
	}
}

class edgeF{
	int from;
	int to;
	int value;
	
	public edgeF(int from, int to) {
		this.from = from;
		this.to = to;
		this.value = 1;
	}
	
	public edgeF(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}
