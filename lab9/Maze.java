package lab9;

import java.io.*;
import java.util.*;

public class Maze {
	
	private static topNodeC[] A;
	private static revtopNodeC[] revA;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		A = new topNodeC[n];
		revA = new revtopNodeC[n];
		
		for(int i=0; i<n; i++) {
			A[i] = new topNodeC(i, null);
			revA[i] = new revtopNodeC(i, null);
		}
			
		for(int i=0; i<m; i++) {
			int from = in.nextInt() - 1;
			int to = in.nextInt() - 1;
			int value = 1;
			edgeNodeC edge = new edgeNodeC(to, null, value);
			edgeNodeC temp = A[from].adj;
			A[from].size++;
			if(temp == null) {
				A[from].adj = edge;
			} else {
				while(temp.next != null) {
					temp = temp.next;
				}
				temp.next = edge;
			}
			
			revedgeNodeC edge2 = new revedgeNodeC(from, null, value);
			revedgeNodeC temp2 = revA[to].adj;
			revA[to].size++;
			if(temp2 == null) {
				revA[to].adj = edge2;
			} else {
				while(temp2.prev != null) {
					temp2 = temp2.prev;
				}
				temp2.prev = edge2;
			}
		}
		
/*		for(int i=0; i<A.length; i++) {
			out.print("List of " + (A[i].num) + " with size " + A[i].size + " is: ");
			edgeNodeC cur = A[i].adj;
			while(cur != null) {
				out.print((cur.vertexNo) + " ");
				cur = cur.next;
			}
			out.println();
		}
		
		for(int i=0; i<A.length; i++) {
			out.print("List of " + (A[i].num) + " with size " + A[i].size + " is: ");
			revedgeNodeC cur = revA[i].adj;
			while(cur != null) {
				out.print((cur.vertexNo) + " ");
				cur = cur.prev;
			}
			out.println();
		}*/
		
		if(dfs(0) && revdfs(0))
			out.println("Bravo");
		else
			out.println("ovarB");
		out.close();
	}
	
	public static boolean dfs(int value) {
		int count = 0;
		int length = A.length;
		boolean[] visited = new boolean[length];
		int top = -1;
		int[] stack = new int[length];
		top++;
		stack[top] = value;
		count++;
		visited[value] = true;
		while(top != -1) {
			int cur = stack[top];
			topNodeC temp = A[cur];
			edgeNodeC e = temp.adj;
			boolean hasNext = false;
			while(e != null) {
				if(!visited[e.vertexNo]) {
					top++;
					stack[top] = e.vertexNo;
					count++;
					visited[e.vertexNo] = true;
					hasNext = true;
					break;
				}
				e = e.next;
			}
			if(!hasNext) {
				top--;
			}
		}
		if(count == length) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean revdfs(int value) {
		int count = 0;
		int length = revA.length;
		boolean[] visited = new boolean[length];
		int top = -1;
		int[] stack = new int[length];
		top++;
		stack[top] = value;
		count++;
		visited[value] = true;
		while(top != -1) {
			int cur = stack[top];
			revtopNodeC temp = revA[cur];
			revedgeNodeC e = temp.adj;
			boolean hasNext = false;
			while(e != null) {
				if(!visited[e.vertexNo]) {
					top++;
					stack[top] = e.vertexNo;
					count++;
					visited[e.vertexNo] = true;
					hasNext = true;
					break;
				}
				e = e.prev;
			}
			if(!hasNext) {
				top--;
			}
		}
		if(count == length) {
			return true;
		} else {
			return false;
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

class topNodeC{
	int num;
	int size;
	edgeNodeC adj;
	
	public topNodeC(int num, edgeNodeC adj) {
		this.num = num;
		this.adj = adj;
	}
}

class revtopNodeC{
	int num;
	int size;
	revedgeNodeC adj;
	
	public revtopNodeC(int num, revedgeNodeC adj) {
		this.num = num;
		this.adj = adj;
	}
}

class edgeNodeC{
	int vertexNo;
	edgeNodeC next;
	int value;
	
	public edgeNodeC(int vertexNo, edgeNodeC next, int value) {
		this.vertexNo = vertexNo;
		this.next = next;
		this.value = value;
	}
}

class revedgeNodeC{
	int vertexNo;
	revedgeNodeC prev;
	int value;
	
	public revedgeNodeC(int vertexNo, revedgeNodeC prev, int value) {
		this.vertexNo = vertexNo;
		this.prev = prev;
		this.value = value;
	}
}

