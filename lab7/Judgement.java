package lab7;

import java.util.Scanner;

public class Judgement {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			treeNodeB[] arr = new treeNodeB[n+1];
			for(int i=1; i<n+1; i++) {
				arr[i] = new treeNodeB(in.nextInt());
			}
			boolean binaryCheck = true;
			for(int i=0; i<n-1; i++) {
				treeNodeB parent = arr[in.nextInt()];
				treeNodeB child = arr[in.nextInt()];
				if(parent.left != null && parent.right != null) {
					binaryCheck = false;
					break;
				}
				if(parent.left != null) {
					parent.right = child;
				} else {
					parent.left = child;
				}
				child.parent = parent;
			}
			
			if(!binaryCheck) {		//Not a binary tree
				System.out.println("Case #" + (t+1) + ": NO");
				continue;
			}
			
			treeNodeB root = null;
			for(int i=1; i<n+1; i++) {
				if(arr[i].parent == null) {
					root = arr[i];
					break;
				}
			}
			boolean check = isBinaryHeap(root, n);
			System.out.print("Case #" + (t+1) + ": ");
			if(check)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	public static boolean isBinaryHeap(treeNodeB root, int n) {
		if(root == null) {
			return false;
		}
		
		treeNodeB[] que = new treeNodeB[n];
		int head = 0;
		int tail = 0;
		que[tail] = root;
		tail++;
		boolean leftMost = false;
		int sit = 0;
		while(head != n-1) {
			treeNodeB parent = que[head];
			head++;
			if(sit == 0) {
				if(parent.left != null) {
					if(parent.left.value > parent.value)
						sit = 1;
					else if(parent.left.value < parent.value)
						sit = 2;
				}
				if(parent.right != null) {
					if(sit == 1) {
						if(parent.right.value < parent.value)
							return false;
					} else if(sit == 2) {
						if(parent.right.value > parent.value)
							return false;
					} else {
						if(parent.right.value > parent.value)
							sit = 1;
						else if(parent.right.value < parent.value)
							sit = 2;
					}
				}
			}
			if(parent.left != null) {
				if(!leftMost) {
					que[tail] = parent.left;
					if(!compare(que[tail], parent, sit))
						return false;
					tail++;
				} else {
					return false;
				}
			} else {
				leftMost = true;
			}
			if(parent.right != null) {
				if(!leftMost) {
					que[tail] = parent.right;
					if(!compare(que[tail], parent, sit))
						return false;
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
	
	public static boolean compare(treeNodeB child, treeNodeB parent, int sit) {
		switch(sit) {
		case 1:
			return child.value >= parent.value;
		case 2:
			return child.value <= parent.value;
		default:
			return child.value == parent.value;
		}
	}
}

class treeNodeB{
	int value;
	treeNodeB parent;
	treeNodeB left;
	treeNodeB right;
	public treeNodeB(int val) {
		this.value = val;
	}
}