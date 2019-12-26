package lab8;

import java.io.*;
import java.util.*;

public class TheSwordOfDamocles {
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
			int k = in.nextInt();
			
			boolean can = false;
			
			A = new int[k+2][k+2];
			
			ArrayList<edgeB> arr = new ArrayList<>();
			int[][] monster = new int[k][3];
			
			for(int i=0; i<k; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int range = in.nextInt();
				monster[i][0] = x;
				monster[i][1] = y;
				monster[i][2] = range;
				if((x+range >= n || y <= range)) {
					arr.add(new edgeB(0, i+1));
				}
				if((x <= range || y+range >= m)) {
					arr.add(new edgeB(k+1, i+1));
				}
			}
			
			for(int i=0; i<k-1; i++) {
				int x = monster[i][0];
				int y = monster[i][1];
				int range = monster[i][2];
				for(int j=i+1; j<k; j++) {
					int x1 = monster[j][0];
					int y1 = monster[j][1];
					int range1 = monster[j][2];
					if(canReach(x, y, range, x1, y1, range1)) {
						arr.add(new edgeB(i+1,j+1));
					}
				}
			}
			
			createGraph(arr);
				
			can = bfs(0);
				
			if(can) {
				out.println("Yes");
			} else {
				out.println("No");
			}
		}
		out.close();
	}
	
	public static boolean canReach(int x1, int y1, int r1, int x2, int y2, int r2) {
		int r = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
		int range = (r1+r2)*(r1+r2);
		return r <= range;
	}
	
	public static boolean bfs(int v) {
		int verNum = A.length;
		boolean[] visited = new boolean[verNum];
		int[] queue = new int[verNum];
		int front = 0;
		int rear = 0;
		visited[v] = true;
		queue[rear] = v;
		rear++;
		while(front < verNum) {
			int cur = queue[front];
			front++;
			for(int i=0; i<verNum; i++) {
				if(A[cur][i] != 0 && !visited[i]) {
					if(A[cur][A.length-1] != 0) {
						return false;
					}
					queue[rear] = i;
					rear++;
					visited[i] = true;
				}
			}
		}
		return true;
	}
	
	public static void createGraph(ArrayList<edgeB> e) {
		for(int i=0; i<e.size(); i++) {
			int from = e.get(i).from;
			int to = e.get(i).to;
			int value = e.get(i).value;
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
	
	public edgeB(int from, int to) {
		this.from = from;
		this.to = to;
		this.value = 1;
	}
	
	public edgeB(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}

