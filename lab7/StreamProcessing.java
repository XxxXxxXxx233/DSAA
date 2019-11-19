package lab7;

import java.util.*;

public class StreamProcessing {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int second = in.nextInt();
		int k = in.nextInt();
		long last_ans = in.nextLong();;
		boolean print = false;
		ArrayList<Long> list = new ArrayList<>();
		list.add((long)-1);
		
		for(int i=1; i<k+1 && i<second+1; i++) {
			list.add(calc((long)i+last_ans));
			insertion(list, list.size()-1);
			if(i % 100 == 0) {
				last_ans = list.get(1);
				System.out.print(last_ans  + " ");
				print = true;
			}
		}
		
		for(int i=k+1; i<second+1; i++) {
			list.add(calc((long)i+last_ans));
			insertion(list, list.size()-1);
			delete_min(list);
			if(i % 100 == 0) {
				last_ans = list.get(1);
				System.out.print(last_ans  + " ");
				print = true;
			}
		}
		if(print)
			System.out.println();
	}
	
	public static long calc(long time) {
		long ans = time;
		while(time > 0) {
			ans += time % 10;
			time = time / 10;
		}
		return ans;
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
