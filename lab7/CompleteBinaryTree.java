package lab7;

import java.util.*;

public class CompleteBinaryTree {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			treeNodeA[] arr = new treeNodeA[n+1];
			for(int i=1; i<n+1; i++) {
				arr[i] = new treeNodeA(i);
			}
			for(int i=1; i<n+1; i++) {
				int left = in.nextInt();
				int right = in.nextInt();
				if(left != 0) {
					arr[i].left = arr[left];
					arr[left].parent = arr[i];
				}
				if(right != 0) {
					arr[i].right = arr[right];
					arr[right].parent = arr[i];
				}
			}
			treeNodeA root = null;
			for(int i=1; i<n+1; i++) {
				if(arr[i].parent == null) {
					root = arr[i];
					break;
				}
			}
			boolean check = isCompleteBinaryTree(root, n);
			if(check)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
	
	public static boolean isCompleteBinaryTree(treeNodeA root, int n) {
		if(root == null) {
			return false;
		}
		treeNodeA[] que = new treeNodeA[n];
		int head = 0;
		int tail = 0;
		que[tail] = root;
		tail++;
		boolean leftMost = false;
		while(head != n-1) {
			treeNodeA node = que[head];
			head++;
			if(node.left != null) {		//when child = false return false;
				if(!leftMost) {
					que[tail] = node.left;
					tail++;
				} else {
					return false;
				}
			} else {
				leftMost = true;
			}
			if(node.right != null) {		//when child = false return false;
				if(!leftMost) {
					que[tail] = node.right;
					tail++;
				} else {
					return false;
				}
			} else {
				leftMost = true;
			}
		}
		return true;
	}
}

class treeNodeA{
	int value;
	treeNodeA parent;
	treeNodeA left;
	treeNodeA right;
	public treeNodeA(int val) {
		this.value = val;
	}
}
