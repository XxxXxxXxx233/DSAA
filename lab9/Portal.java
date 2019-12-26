package lab9;

import java.io.*;
import java.util.*;

public class Portal {
	
	private static topNodeE[] A;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int n = in.nextInt();
		int m = in.nextInt();
		int p = in.nextInt();
		int k = in.nextInt();
		
		A = new topNodeE[n];
		for(int i=0; i<n; i++) {
			A[i] = new topNodeE(i, null);
		}
		
		for(int i=0; i<m; i++) {
			int from = in.nextInt() - 1;
			int to = in.nextInt() - 1;
			int value = in.nextInt();
			edgeNodeE edge = new edgeNodeE(to, null, value);
			edgeNodeE temp = A[from].adj;
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
		
		for(int i=0; i<p; i++) {
			int from = in.nextInt() - 1;
			int to = in.nextInt() - 1;
			int value = 0;
			edgeNodeE edge = new edgeNodeE(to, null, value, true);
			edgeNodeE temp = A[from].adj;
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
			out.print("List of " + (A[i].num+1) + " with size " + A[i].size + " is: ");
			edgeNodeE cur = A[i].adj;
			while(cur != null) {
				out.print((cur.vertexNo+1) + " ");
				cur = cur.next;
			}
			out.println();
		}*/
		
		int begin = in.nextInt() - 1;
		int target = in.nextInt() - 1;
		long sum = findMin(begin, target, k);
		out.println(sum);
		out.close();
	}
	
	public static long findMin(int begin, int target, int k) {
		PriorityQueue<priorityNode> q = new PriorityQueue<>();
		topNodeE first = A[begin];
		edgeNodeE firstAdj = first.adj;
		while(firstAdj != null) {
			priorityNode temp = new priorityNode(firstAdj.vertexNo, firstAdj.value);
			if(firstAdj.isPortal) {
				temp.level++;
			}
			q.add(temp);
			firstAdj = firstAdj.next;
		}
		
		while(q != null) {
			priorityNode pNode = q.poll();
			if(pNode.No == target && pNode.level <= k) {
				return pNode.distance;
			}
			topNodeE top =  A[pNode.No];
			edgeNodeE topAdj = top.adj;
			while(topAdj != null) {
				priorityNode temp = new priorityNode(topAdj.vertexNo, topAdj.value + pNode.distance);
				temp.level = pNode.level;
				if(topAdj.isPortal) {
					temp.level++;
				}
				if(temp.level <= k) {
					q.add(temp);
				}
				topAdj = topAdj.next;
			}
		}
		return 0;
	}
	
	static class priorityNode implements Comparable<priorityNode> {
		int No;
		int level;
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

class topNodeE{
	int num;
	int size;
	edgeNodeE adj;
	
	public topNodeE(int num, edgeNodeE adj) {
		this.num = num;
		this.adj = adj;
	}
}

class edgeNodeE{
	int vertexNo;
	edgeNodeE next;
	int value;
	boolean isPortal;
	
	public edgeNodeE(int vertexNo, edgeNodeE next, int value) {
		this.vertexNo = vertexNo;
		this.next = next;
		this.value = value;
		this.isPortal = false;
	}
	
	public edgeNodeE(int vertexNo, edgeNodeE next, int value, boolean isPortal) {
		this.vertexNo = vertexNo;
		this.next = next;
		this.value = value;
		this.isPortal = isPortal;
	}
}
