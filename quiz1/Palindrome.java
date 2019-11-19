package quiz1;


import java.util.Scanner;

public class Palindrome {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Node a = new Node(-1);
		Node head = null;
		Node tail = null;
		head = a;
		tail = a;
		int num = in.nextInt();
		while(num != -1) {
			Node c = new Node(num);
			tail.next = c;
			tail = tail.next;
			num = in.nextInt();
		}
		boolean check = isPalindrome(head);
		System.out.println(check);
	}
	
	public static boolean isPalindrome(Node head) {
		boolean check = true;
		int top = -1;
		int[] arr = new int[10000];
		Node slow = head;
		Node fast = head;
		while(true) {
			slow = slow.next;
			top++;
			arr[top] = slow.value;
			fast = fast.next.next;
			if(fast == null) {
				top--;
				break;
			} else if(fast.next == null) {
				break;
			}
		}
		slow = slow.next;
		while(slow != null) {
			if(slow.value == arr[top]) {
				top--;
				slow = slow.next;
			} else {
				check = false;
				break;
			}
		}
		return check;
	}
}

class Node {
	int value;
	Node next;
	Node(int value) {
		this.value = value;
	}
}
