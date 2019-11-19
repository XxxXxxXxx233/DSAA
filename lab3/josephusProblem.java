package lab3;

import java.io.*;
import java.util.*;

public class josephusProblem {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int targetNumber = in.nextInt();
			NodeD a = new NodeD(-1);
			NodeD head = null;
			NodeD tail = null;
			head = a;
			tail = a;
			head.prev = null;
			tail.next = null;
			
			for(int i=0; i<n; i++) {
				NodeD c = new NodeD(in.nextInt());
				tail.next = c;
				tail.next.iniPos = i;
				c.prev = tail;
				tail = tail.next;
			}
			NodeD cur = head.next;
			int count = 1;
			while(n > 1) {
				boolean lastCheck = false;
				if(targetNumber % n == 0)
					targetNumber = n;
				else if(targetNumber > n)
					targetNumber = targetNumber - targetNumber / n * n;
				if(targetNumber > (n+1)/2) {
					int rest = n - targetNumber;
					while(rest >= 0) {
						if(head.next == cur)
							cur = tail;
						else
							cur = cur.prev;
						rest--;
					}
				} else {
					while(count < targetNumber) {
						if(cur.next == null)
							cur = head.next;
						else
							cur = cur.next;
						count++;
					}
				}
				if(cur.next == null)
					lastCheck = true;
				targetNumber = cur.value;
				if(cur == head.next)
					head = head.next;
				else
					cur.prev.next = cur.next;
				
				if(cur.next == null)
					tail = tail.prev;
				else
					cur.next.prev = cur.prev;
				n--;
				count = 1;
				if(lastCheck) {
					cur = head.next;
				} else {
					cur = cur.next;
				}
				if(n == 1)
					System.out.println(cur.iniPos + 1);
			}
		}
		out.close();
	}
	
    public static void deleteNode(NodeD head, NodeD curNode) {
    	if (null == curNode.next) {
    		NodeD tempNode = head;
    		if (tempNode == curNode) {
    			curNode = null;
    			head = null;
    			tempNode = null;
    		} else {
    			while (tempNode != null && tempNode.next != curNode) {
    				tempNode = tempNode.next;
    			}
    			if (null != tempNode) {
    				tempNode.next = tempNode.next.next;
    				curNode.next = null;
    			}
    		}
    	} else {
    		NodeD nextNode = curNode.next;
    		curNode.value = nextNode.value;
    		curNode.iniPos = nextNode.iniPos;
    		curNode.next = nextNode.next;
    		nextNode.prev = curNode.prev;
    		nextNode.next = null;
    		
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

class NodeD {
	int value;
	NodeD next;
	NodeD prev;
	int iniPos;
	NodeD(int value) {
		this.value = value;
	}
}
