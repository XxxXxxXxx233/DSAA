package lab8;

import java.io.*;
import java.util.*;

public class SoldiersByList {
	
	private static topNodeE[] A;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			
			A = new topNodeE[n];
			for(int i=0; i<n; i++) {
				A[i] = new topNodeE(i, null);
			}
			
			vertexE[] v = new vertexE[n];
			for(int i=0; i<n; i++) {
				v[i] = new vertexE(i);
			}
			
			for(int i=0; i<m; i++) {
				int from = in.nextInt() - 1;
				int to = in.nextInt() - 1;
				
				int change = from;
				from = to;
				to = change;
				
				edgeNodeE edge = new edgeNodeE(to, null, 1);
				edgeNodeE temp = A[from].adj;
				if(temp == null) {
					A[from].adj = edge;
					A[from].size++;
					v[edge.vertexNo].in++;
				} else {
					boolean duplicate = false;
					if(temp.vertexNo == edge.vertexNo) {
						duplicate = true;
					}
					while(temp.next != null) {
						temp = temp.next;
						if(temp.vertexNo == edge.vertexNo) {
							duplicate = true;
						}
					}
					if(!duplicate) {
						temp.next = edge;
						A[from].size++;
						v[edge.vertexNo].in++;
					}
				}
			}
			
/*			System.out.println("Bulit");
			for(int i=0; i<A.length; i++) {
				out.print("List of " + (A[i].num+1) + " with size " + A[i].size + " is: ");
				edgeNodeE cur = A[i].adj;
				while(cur != null) {
					out.print((cur.vertexNo+1) + " ");
					cur = cur.next;
				}
				out.println();
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
				}
			}
			
			int[] ans = new int[n];
			int count = 0;
			
			while(!q.isEmpty()) {
				int top = q.poll();	//biggest in the queue
				ans[count] = top;
				count++;
				edgeNodeE cur = A[top].adj; 
				while(cur != null) {
					int num = cur.vertexNo;
					v[num].in--;
					if(v[num].in == 0) {
						q.add(cur.vertexNo);
					}
					cur = cur.next;
				}
			}
			
			for(int i=n-1; i>=0; i--) {
				out.print((ans[i]+1) + " ");
			}
			out.println();
		}
		
		out.close();
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

class vertexE{
	int in;
	int out;
	int value;
	
	public vertexE(int value) {
		this.value = value;
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
	
	public edgeNodeE(int vertexNo, edgeNodeE next, int value) {
		this.vertexNo = vertexNo;
		this.next = next;
		this.value = value;
	}
}