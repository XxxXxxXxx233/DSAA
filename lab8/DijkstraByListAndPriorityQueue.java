package lab8;

import java.io.*;
import java.util.*;

public class DijkstraByListAndPriorityQueue {
	
	private static topNodeD[] A;
	private static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		A = new topNodeD[n];
		for(int i=0; i<n; i++) {
			A[i] = new topNodeD(i, null);
		}
		
		boolean[] visited = new boolean[n];
		
		for(int i=0; i<m; i++) {
			int from = in.nextInt() - 1;
			int to = in.nextInt() - 1;
			int value = in.nextInt();
			edgeNodeD edge = new edgeNodeD(to, null, value);
			edgeNodeD temp = A[from].adj;
			A[from].size++;
			if(temp == null) {
				A[from].adj = edge;
			} else {
				while(temp.next != null) {
					temp = temp.next;
				}
				temp.next = edge;
			}
		}
		
/*		for(int i=0; i<A.length; i++) {
			out.print("List of " + (A[i].num) + " with size " + A[i].size + " is: ");
			edgeNodeD cur = A[i].adj;
			while(cur != null) {
				out.print((cur.vertexNo) + " ");
				cur = cur.next;
			}
			out.println();
		}*/
		
		int begin = in.nextInt() - 1;
		int target = in.nextInt() - 1;
		long[] answer = dijkstra(begin);
		
		if(answer[target] == MAX) {
			out.println(-1);
		} else {
			out.println(answer[target]);
		}
		
/*		for(int i=0; i<answer.length; i++) {
			out.print(answer[i] + " ");
		}
		out.println();*/
		
		out.close();
	}
	
	public static long[] dijkstra(int begin) {
		int length = A.length;
		long[] ans = new long[length];
		boolean[] visited = new boolean[length];
		
		ans[begin] = 0;
		for(int i=0; i<length; i++) {
			if(i != begin)
				ans[i] = MAX;
		}
		
		PriorityQueue<priorityNode> q = new PriorityQueue<>();
		q.add(new priorityNode(begin, ans[begin]));
		
		while(!q.isEmpty()) {
			priorityNode temp = q.poll();
			int No = temp.No;
			if(!visited[No]) {
				visited[No] = true;
				edgeNodeD cur = A[No].adj;
				while(cur != null) {
					int vertexNo = cur.vertexNo;
					if(!visited[vertexNo] && ans[No] + cur.value < ans[vertexNo]) {
						ans[vertexNo] = ans[No] + cur.value;
						q.add(new priorityNode(vertexNo, ans[vertexNo]));
					}
					cur = cur.next;
				}
			}
		}
		return ans;
	}
	
	static class priorityNode implements Comparable<priorityNode> {
		int No;
		long distance;
		
		public priorityNode(int No, long distance) {
			this.No = No;
			this.distance = distance;
		}

		@Override
		public int compareTo(priorityNode o) {
			if(distance > o.distance)
				return 1;
			else if(distance < o.distance)
				return -1;
			return 0;
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

class topNodeD{
	int num;
	int size;
	edgeNodeD adj;
	
	public topNodeD(int num, edgeNodeD adj) {
		this.num = num;
		this.adj = adj;
	}
}

class edgeNodeD{
	int vertexNo;
	edgeNodeD next;
	int value;
	
	public edgeNodeD(int vertexNo, edgeNodeD next, int value) {
		this.vertexNo = vertexNo;
		this.next = next;
		this.value = value;
	}
}
