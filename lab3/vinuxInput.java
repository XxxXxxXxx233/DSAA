package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class vinuxInput {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			String input = in.next();
			String[] arr = new String[n];
			for(int i=0; i<n; i++) {
				arr[i] = input.substring(i, i+1);
			}
			
			NodeVinux head = new NodeVinux(-100);
			head.prev = head;
			NodeVinux cur = head;
			NodeVinux EOL = new NodeVinux(-100);
			EOL.prev = head;
			EOL.next = EOL;
			head.next = EOL;
			NodeVinux NodeVinux = head;
			for(int i=0; i<n; i++) {
				String sit = arr[i];
				switch(sit) {
				case "r":
					i++;
					if(i < n) {
						sit = arr[i];
						int value = Integer.parseInt(sit);
						if(cur.value == -100) {
							NodeVinux c = new NodeVinux(value);
							c.prev = cur;
							c.next = cur.next;
							c.next.prev = c;
							cur.next = c;
							cur = c;
						} else {
							cur.value = value;
						}
					}
					break;
				case "I":
					cur = EOL.prev;
					break;
				case "H":
					if(cur.next != EOL)
						cur = cur.next;
					break;
				case "L":
					cur = cur.prev;
					break;
				case "x":
					if(cur != head) {
						cur.prev.next = cur.next;
						cur.next.prev = cur.prev;
						cur = cur.prev;
					}
					break;
				default:
					NodeVinux c = new NodeVinux(Integer.parseInt(sit));
					c.prev = cur;
					c.next = cur.next;
					cur.next = c;
					c.next.prev = c;
					break;
				}
			}
			NodeVinux = EOL.prev;
			traverse(NodeVinux);
		}
		out.close();
	}
	
	public static void traverse(NodeVinux A) {
		if(A.prev == A) {
			System.out.println();
			return;
		} else {
			System.out.print(A.value);
			traverse(A.prev);
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

class NodeVinux {
	int value;
	int iniPos;
	NodeVinux next;
	NodeVinux prev;
	NodeVinux(int value) {
		this.value = value;
	}
}
