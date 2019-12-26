package lab9;

import java.io.*;
import java.util.*;

public class Boom {
	
	private static topNode[] A;
	private static int[] dfn;
	private static int[] low;
	private static int[] after;
	private static boolean[] visited;
	private static Stack<Integer> s = new Stack<>();
	private static int count;
	private static int merge;
	private static topNode[] newGraph;
	private static ArrayList<Integer> scc = new ArrayList<>();
	private static ArrayList<node2> ans = new ArrayList<>();
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int n = in.nextInt();
		
		A = new topNode[n];
		dfn = new int[n];
		low = new int[n];
		after = new int[n];
		visited = new boolean[n];
		newGraph = new topNode[n];
		
		ArrayList<bomb> b = new ArrayList<>();
		for(int i=0; i<n; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int r = in.nextInt();
			int t = in.nextInt();
			b.add(new bomb(x, y, r, t));
		}
		
		for(int i=0; i<n; i++) {
			after[i] = -1;
		}
		
		for(int i=0; i<n; i++) {
			A[i] = new topNode(i, null);
			newGraph[i] = new topNode(i, null);
			newGraph[i].min = b.get(i).time;
		}
		
		for(int j=0; j<n; j++) {
			bomb b1 = b.get(j);
			for(int k=0; k<n; k++) {
				if(k == j)
					continue;
				bomb b2 = b.get(k);
				if(canBeExploded(b1, b2)) {
					int from = j;
					int to = k;
					int value = 1;
					edgeNode edge = new edgeNode(to, null, value);
					edgeNode temp = A[from].adj;
					A[from].size++;
					A[from].out++;
					A[to].in++;
					if(temp == null) {
						A[from].adj = edge;
					} else {
						while(temp.next != null) {
							temp = temp.next;
						}
						temp.next = edge;
					}
				}
			}
		}
		
/*		for(int i=0; i<A.length; i++) {
			out.print("List of " + (A[i].num) + " with size " + A[i].size + " is: ");
			edgeNode cur = A[i].adj;
			while(cur != null) {
				out.print((cur.vertexNo) + " ");
				cur = cur.next;
			}
			out.println();
		}*/
		
		for(int i=0; i<n; i++) {
			if(dfn[i] == 0) {
				tarjan(i);
			}
		}
		
		for(int i=0; i<n; i++) {
			topNode toTop = A[i];
			edgeNode toEdge = toTop.adj;
			while(toEdge != null) {
				if(after[toTop.num] != after[toEdge.vertexNo]) {
					ans.get(after[toEdge.vertexNo]).in++;
				} 			
				toEdge = toEdge.next;
			}
		}
		
		long sum = 0;
		for(int i=0; i<ans.size(); i++) {
			if(ans.get(i).in == 0) {
				sum += (long)ans.get(i).cost;
			}
		}
		out.println(sum);
		
		out.close();
	}
	
	public static void tarjan(int begin) {
		count++;
		dfn[begin] = count;
		low[begin] = count;
		s.add(begin);
		visited[begin] = true;
		topNode top = A[begin];
		edgeNode topAdj = top.adj;
		while(topAdj != null) {
			int num = topAdj.vertexNo;
			if(dfn[num] == 0) {
				tarjan(num);
				if(low[begin] > low[num]) {
					low[begin] = low[num];
				}
			} else if(visited[num]) {
				if(low[begin] > dfn[num]) {
					low[begin] = dfn[num];
				}
			}
			topAdj = topAdj.next;
		}
		
		if(low[begin] == dfn[begin]) {
			int num = s.pop();
			visited[num] = false; 
			after[num] = merge;
			scc.add(num);
			while(num != begin) {
				num = s.pop();
				visited[num] = false;
				after[num] = merge;
				scc.add(num);
			}
			
			int minIndex = -1;
			int min = Integer.MAX_VALUE;
			for(int j=0; j<scc.size(); j++) {
				if(min > newGraph[scc.get(j)].min) {
					min = newGraph[scc.get(j)].min;
					minIndex = scc.get(j);
				}
			}
			if(minIndex != -1) {
				ans.add(new node2(merge, min, 0));
			}
			merge++;
			scc.clear();
		}
	}
	
	public static boolean canBeExploded(bomb b1, bomb b2) {
		long x1 = (long)b1.x;
		long y1 = (long)b1.y;
		long x2 = (long)b2.x;
		long y2 = (long)b2.y;
		double dist = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
		long range = (long)b1.r;
		return range*range >= dist;
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

class topNode{
	int num;
	int size;
	int in;
	int out;
	int min;
	edgeNode adj;
	
	public topNode(int num, edgeNode adj) {
		this.num = num;
		this.adj = adj;
	}
}

class node2{
	int after;
	int cost;
	int in;

	public node2(int after, int cost, int in) {
		this.after = after;
		this.cost = cost;
		this.in = in;
	}
}

class bomb{
	int x;
	int y;
	int r;
	int time;
	
	public bomb(int x, int y, int r, int time) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.time = time;
	}
}

class edgeNode{
	int vertexNo;
	edgeNode next;
	int value;
	
	public edgeNode(int vertexNo, edgeNode next, int value) {
		this.vertexNo = vertexNo;
		this.next = next;
		this.value = value;
	}
}


