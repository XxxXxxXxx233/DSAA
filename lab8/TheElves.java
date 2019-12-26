package lab8;

import java.io.*;
import java.util.*;

public class TheElves {
	
	private static topNodeI[] A;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			
			int[] a = new int[n];
			int[] b = new int[n];
			for(int i=0; i<n; i++) {
				a[i] = in.nextInt();
				b[i] = in.nextInt();
			}
			
			A = new topNodeI[n];
			for(int i=0; i<n; i++) {
				A[i] = new topNodeI(i, null);
			}
			
			vertexI[] v = new vertexI[n];
			for(int i=0; i<n; i++) {
				v[i] = new vertexI(i);
			}
			
			for(int i=0; i<m; i++) {
				int from = in.nextInt() - 1;
				int to = in.nextInt() - 1;
				v[to].parents.add(v[from]);
				
				edgeNodeI edge = new edgeNodeI(to, null, 1);
				edgeNodeI temp = A[from].adj;
				if(temp == null) {
					A[from].adj = edge;
					A[from].size++;
					v[edge.vertexINo].in++;
				} else {
					boolean duplicate = false;
					if(temp.vertexINo == edge.vertexINo) {
						duplicate = true;
					}
					while(temp.next != null) {
						temp = temp.next;
						if(temp.vertexINo == edge.vertexINo) {
							duplicate = true;
						}
					}
					if(!duplicate) {
						temp.next = edge;
						A[from].size++;
						v[edge.vertexINo].in++;
					}
				}
			}
			
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
			
			int[] topoSort = new int[n];
			int count = 0;
			
			while(!q.isEmpty()) {
				int top = q.poll();	//biggest in the queue
				topoSort[count] = top;
				count++;
				edgeNodeI cur = A[top].adj; 
				while(cur != null) {
					int num = cur.vertexINo;
					v[num].in--;
					if(v[num].in == 0) {
						q.add(cur.vertexINo);
					}
					cur = cur.next;
				}
			}
			
/*			for(int i=0; i<n; i++) {
				out.print((topoSort[i]+1) + " ");
			}
			out.println();*/
			
			long sum = 0;
			for(int i=0; i<n; i++) {
				vertexI cur = v[topoSort[i]];
				for(int j=0; j<cur.parents.size(); j++) {
					vertexI parent = cur.parents.get(j);
					cur.numberOfPath += mod(mod(parent.numberOfPath + mod(a[parent.value])));				}
				sum = mod(mod(cur.numberOfPath) * mod(b[topoSort[i]]) + sum);
			}
			out.println(sum);
		}
		
		out.close();
	}

	public static long mod(long num) {
		long m = 1000000007L;
		return num%m;
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

class vertexI{
	int in;
	int out;
	int value;
	long numberOfPath;
	ArrayList<vertexI> parents = new ArrayList<>();
	
	public vertexI(int value) {
		this.value = value;
	}
}

class topNodeI{
	int num;
	int size;
	edgeNodeI adj;
	
	public topNodeI(int num, edgeNodeI adj) {
		this.num = num;
		this.adj = adj;
	}
}

class edgeNodeI{
	int vertexINo;
	edgeNodeI next;
	int value;
	
	public edgeNodeI(int vertexINo, edgeNodeI next, int value) {
		this.vertexINo = vertexINo;
		this.next = next;
		this.value = value;
	}
}