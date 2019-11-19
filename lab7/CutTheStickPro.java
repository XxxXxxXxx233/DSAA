package lab7;

import java.util.*;

public class CutTheStickPro {
	private static long sum = 0;
	private static long parent = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			ArrayList<Long> list = new ArrayList<>();
			list.add((long)-1);
			for(int i=1; i<n+1; i++) {
				list.add(in.nextLong());
				int num = i;
				insertion(list, num);
			}
			huffmanTree(list);
			System.out.println(sum);
			sum = 0;
		}
	}
	
	public static void huffmanTree(ArrayList<Long> list) {
		while(list.size() > 2) {
			delete_min(list);
			delete_min(list);
			list.add(parent);
			insertion(list, list.size()-1);
			parent = 0;
		}
	}
	
	public static void insertion(ArrayList<Long> list, int num) {
		while(num/2 > 0) {
			if(list.get(num/2) >= list.get(num)) {
				long temp = list.get(num/2);
				list.set(num/2, list.get(num));
				list.set(num, temp);
				num = num/2;
			} else {
				break;
			}
		}
	}
	
	public static void delete_min(ArrayList<Long> list) {
		parent += list.get(1);
		sum += list.get(1);
		int rightMost = list.size()-1;
		list.set(1, list.get(rightMost));
		list.remove(rightMost);
		rightMost--;
		int index = 1;
		while(index*2 <= rightMost) {
			if(index*2 == rightMost) {
				if(list.get(index) < list.get(index*2)) {
					break;
				} else {
					long temp = list.get(index);
					list.set(index, list.get(index*2));
					list.set(index*2, temp);
					index = index*2;
				}
			} else {
				long number1 = list.get(index*2);
				long number2 = list.get(index*2+1);
				long smaller = 0;
				int smallerIndex = 0;
				if(number1 > number2) {
					smaller = number2;
					smallerIndex = index*2+1;
				} else {
					smaller = number1;
					smallerIndex = index*2;
				}
				if(list.get(index) < smaller) {
					break;
				} else {
					long temp = list.get(index);
					list.set(index, list.get(smallerIndex));
					list.set(smallerIndex, temp);
					index = smallerIndex;
				}
			}
		}
	}
}
