package lab3;

import java.util.Scanner;

public class eccentricCalculator {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			long biggest = 0;
			String str = m + "";
			long num = m;
			int length = power(n);
			
			NodeE a = new NodeE(-1);
			NodeE head = null;
			NodeE tail = null;
			head = a;
			tail = a;
			
			NodeE j = head;
			NodeE k = head;
			while(true) {
				if(num >= length) {
					str = num + "";
					num = Integer.parseInt(str.substring(0, n));
				} else {
					NodeE c = new NodeE(num);
					tail.next = c;
					tail = tail.next;
					if(num >= biggest)
						biggest = num;
					num = num * num;
					if(k.next.next != null) {
						j = j.next;
						k = k.next.next;
						if(j.value == k.value)
							break;
					}
				}
			}
			System.out.println(biggest);
		}
	}
	
	public static int power(int n) {
		int result = 1;
		for(int i=0; i<n; i++)
			result *= 10;
		return result;
	}
}

class NodeE {
	long value;
	NodeE next;
	NodeE prev;
	
	NodeE(long value) {
		this.value = value;
	}
}
