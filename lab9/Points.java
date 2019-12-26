package lab9;

import java.io.*;
import java.util.*;

public class Points {
	
	private static topNodeD[] A;
	private static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int n = in.nextInt();
		int m = in.nextInt();
		int size = n*m;
		int[][] co = new int[n][m];
		A = new topNodeD[size];
		for(int i=0; i<size; i++) {
			A[i] = new topNodeD(i, null);
		}
		if(size == 1)
			out.println(0);
		else {
			int maxFrom = -1;
			int maxTo = -1;
			int maxValue = -1;
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					co[j][k] = in.nextInt();
					if(k > 0) {
						int to = j * m + k;
						int from = to - 1;
						int point = co[j][k-1]*co[j][k];
						edgeNodeD edge = new edgeNodeD(to, null, point);
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
						
						edgeNodeD edge2 = new edgeNodeD(from, null, point);
						edgeNodeD temp2 = A[to].adj;
						A[to].size++;
						if(temp2 == null) {
							A[to].adj = edge2;
						} else {
							while(temp2.next != null) {
								temp2 = temp2.next;
							}
							temp2.next = edge2;
						}
						
						if(point > maxValue) {
							maxValue = point;
							maxTo = to;
							maxFrom = from;
						}
					}
					if(j > 0) {
						int to = j * m + k;
						int from = to - m;
						int point = co[j-1][k]*co[j][k];
						edgeNodeD edge = new edgeNodeD(to, null, point);
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
						
						edgeNodeD edge2 = new edgeNodeD(from, null, point);
						edgeNodeD temp2 = A[to].adj;
						A[to].size++;
						if(temp2 == null) {
							A[to].adj = edge2;
						} else {
							while(temp2.next != null) {
								temp2 = temp2.next;
							}
							temp2.next = edge2;
						}
						
						if(point > maxValue) {
							maxValue = point;
							maxTo = to;
							maxFrom = from;
						}
					}
				}
			}
			
/*			System.out.println("Bulit");
			for(int i=0; i<A.length; i++) {
				out.print("List of " + (A[i].num+1) + " with size " + A[i].size + " is: ");
				edgeNodeD cur = A[i].adj;
				while(cur != null) {
					out.print((cur.value) + " ");
					cur = cur.next;
				}
				out.println();
			}*/
			
			long sum = prim(maxFrom, maxTo, maxValue);
			out.println(sum);
		}
		
		out.close();
	}
	
	public static long prim(int maxFrom, int maxTo, int maxValue) {
		long sum = 0;
		int length = A.length;
		boolean[] visited = new boolean[length];
		int[] dist = new int[length];
		
		dist[maxFrom] = maxValue;
		for(int i=0; i<length; i++) {
			if(i != maxFrom)
				dist[i] = -1;
		}
		
		PriorityQueue<priorityNode> q = new PriorityQueue<>();
		q.add(new priorityNode(maxFrom, dist[maxFrom]));
//		visited[maxTo] = true;
		while(!q.isEmpty()) {
			priorityNode temp = q.poll();
			int No = temp.No;
//			System.out.println("No = " + No + " dist = " + temp.distance);
			if(!visited[No]) {
				visited[No] = true;
//				System.out.println("Add " + temp.distance);
				sum += temp.distance;
				edgeNodeD cur = A[No].adj;
				while(cur != null) {
					int vertexNo = cur.vertexNo;
					if(!visited[vertexNo] && cur.value > dist[vertexNo]) {
//						System.out.println("vertexNo = " + vertexNo);
						dist[vertexNo] = cur.value;
						q.add(new priorityNode(vertexNo, dist[vertexNo]));
					}
					cur = cur.next;
				}
			}
		}
		return sum - maxValue;
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
				return -1;
			else if(distance < o.distance)
				return 1;
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

