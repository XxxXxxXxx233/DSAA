package lab7;

import java.util.Scanner;

public class NthElementInSlidingWindow {
	private static treeNodeE root = null;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int size = in.nextInt();
		int[] arr = new int[m];
		for(int i=0; i<m; i++) {
			arr[i] = in.nextInt();
		}

		int time = m - size + 1;
		for(int i=0; i<size-1; i++) {
			insertion(arr[i]);
		}
		
		for(int i=0; i<time; i++) {
			int number = in.nextInt();
			insertion(arr[i + size - 1]);
			
			treeNodeE ans = getKth(root, number);
			if(ans != null)
				System.out.println(ans.value);
			
			delete(arr[i]);
		}
	}
	
	public static void insertion(int num) {
		treeNodeE tar = new treeNodeE(num);
		if(root == null) {
			root = tar;
			root.height = setHeight(root);
			root.sonAmount = setAmount(root);
		} else {
			treeNodeE temp = root;
			while(true) {
				if(num < temp.value) {
					if(temp.left != null) {
						temp = temp.left;
					} else {
						temp.left = tar;
						tar.parent = temp;
						break;
					}
				} else if(num > temp.value){
					if(temp.right != null) {
						temp = temp.right;
					} else {
						temp.right = tar;
						tar.parent = temp;
						break;
					}
				}
			}
			while(temp != null) {
				temp.height = setHeight(temp);
				temp.sonAmount = setAmount(temp);
				if(getHeight(temp.right) - getHeight(temp.left) == 2) {
					if(getHeight(temp.right.right) - getHeight(temp.right.left) == 0 || getHeight(temp.right.right) - getHeight(temp.right.left) == 1) {
						L_Rotate(temp);
					} else {
						RL_Rotate(temp);
					}
				} else if(getHeight(temp.right) - getHeight(temp.left) == -2){
					if(getHeight(temp.left.right) - getHeight(temp.left.left) == 0 || getHeight(temp.left.right) - getHeight(temp.left.left) == -1) {
						R_Rotate(temp);
					} else {
						LR_Rotate(temp);
					}
				}
				temp = temp.parent;
			}
		}
	}
	
	public static void L_Rotate(treeNodeE parent) {
		treeNodeE R_Child = parent.right;
		treeNodeE RL_Child = R_Child.left;
		treeNodeE upper = parent.parent;
		
		parent.right = RL_Child;
		if(RL_Child != null) {
			RL_Child.parent = parent;
		}
		
		R_Child.left = parent;
		parent.parent = R_Child;
		if(upper == null) {
			root = R_Child;
			root.parent = null;
		} else {
			if(upper.left == parent) {
				upper.left = R_Child;
			} else {
				upper.right = R_Child;
			}
			R_Child.parent = upper;
		}
		if(RL_Child != null) {
			RL_Child.height = setHeight(RL_Child);
			RL_Child.sonAmount = setAmount(RL_Child);
		}
		parent.height = setHeight(parent);
		parent.sonAmount = setAmount(parent);
		R_Child.height = setHeight(R_Child);
		R_Child.sonAmount = setAmount(R_Child);
	}
	
	public static void R_Rotate(treeNodeE parent) {
		treeNodeE L_Child = parent.left;
		treeNodeE LR_Child = L_Child.right;
		treeNodeE upper = parent.parent;
		
		parent.left = LR_Child;
		if(LR_Child != null) {
			LR_Child.parent = parent;
		}
		
		L_Child.right = parent;
		parent.parent = L_Child;
		if(upper == null) {
			root = L_Child;
			root.parent = null;
		} else {
			if(upper.left == parent) {
				upper.left = L_Child;
			} else {
				upper.right = L_Child;
			}
			L_Child.parent = upper;
		}
		if(LR_Child != null) {
			LR_Child.height = setHeight(LR_Child);	
			LR_Child.sonAmount = setAmount(LR_Child);
		}
		parent.height = setHeight(parent);
		parent.sonAmount = setAmount(parent);
		L_Child.height = setHeight(L_Child);
		L_Child.sonAmount = setAmount(L_Child);
	}
	
	public static void LR_Rotate(treeNodeE parent) {
		L_Rotate(parent.left);
		R_Rotate(parent);
	}
	
	public static void RL_Rotate(treeNodeE parent) {
		R_Rotate(parent.right);
		L_Rotate(parent);
	}
	
	public static void delete(int num) {
		treeNodeE tar = findPos(num);
		if(tar != null) {
			treeNodeE parent = tar.parent;
			if(tar.left == null && tar.right == null) {
				delete_leaf(tar);
			} else if(tar.right != null){
				treeNodeE succ = tar.right;
				while(succ.left != null)
					succ = succ.left;
				tar.value = succ.value;
				parent = succ.parent;
				if(succ.left == null && succ.right == null) {
					delete_leaf(succ);
				} else {
					treeNodeE rightChild = succ.right;
					succ.value = rightChild.value;
					succ.left = rightChild.left;
					if(rightChild.left != null)
						rightChild.left.parent = succ;
					succ.right = rightChild.right;
					if(rightChild.right != null)
						rightChild.right.parent = succ;
					succ.sonAmount--;
					succ.height--;
				}
			} else {
				treeNodeE leftChild = tar.left;
				tar.value = leftChild.value;
				tar.left = leftChild.left;
				if(leftChild.left != null)
					leftChild.left.parent = tar;
				tar.right = leftChild.right;
				if(leftChild.right != null)
					leftChild.right.parent = tar;
				tar.height--;
				tar.sonAmount--;
			}
			
			while(parent != null) {
				parent.height = setHeight(parent);
				parent.sonAmount = setAmount(parent);
				if(getHeight(parent.right) - getHeight(parent.left) >= 2) {
					if(getHeight(parent.right.right) - getHeight(parent.right.left) >= 0) {
						L_Rotate(parent);
					} else {
						RL_Rotate(parent);
					}
				} else if(getHeight(parent.right) - getHeight(parent.left) <= -2) {
					if(getHeight(parent.left.right) - getHeight(parent.left.left) <= 0) {
						R_Rotate(parent);
					} else {
						LR_Rotate(parent);
					}
				}
				parent = parent.parent;
			}
		}
	}
	
	public static void delete_leaf(treeNodeE tar) {
		if(tar.parent == null) {
			tar = null;
			root = null;
		} else {
			if(tar.parent.left == tar) {
				tar.parent.left = null;
			} else {
				tar.parent.right = null;
			}
			tar.parent = null;
		}
	}
	
	public static treeNodeE findPos(int num) {
		treeNodeE cur = root;
		while(true) {
			if(num > cur.value) {
				if(cur.right != null) {
					cur = cur.right;
				} else {
					return null;		//don't find
				}
			} else if(num < cur.value) {
				if(cur.left != null) {
					cur = cur.left;
				} else {
					return null;		//don't find
				}
				
			} else {
				return cur;
			}
		}
	}
	
	public static treeNodeE getKth(treeNodeE root, int k) {
		treeNodeE ans = null;
		treeNodeE temp = root;
		while(true) {
			if(k < getAmount(temp.left) + 2) {
				temp = temp.left;
			} else if(k == getAmount(temp.left) + 2) {
				ans = temp;
				break;
			} else {
				k = k - getAmount(temp.left) - 2;
				temp = temp.right;			
			}
		}
		return ans;
	}
	
	public static int setHeight(treeNodeE tar) {
		return Math.max(getHeight(tar.left), getHeight(tar.right)) + 1;
	}
	
	public static int getHeight(treeNodeE tar) {
		if(tar == null) {
			return -1;
		} else {
			return tar.height;
		}
	}
	
	public static int setAmount(treeNodeE tar) {
		return getAmount(tar.left) + getAmount(tar.right) + 2;
	}
	
	public static int getAmount(treeNodeE tar) {
		if(tar == null) {
			return -1;
		} else {
			return tar.sonAmount;
		}
	}
	
}

class treeNodeE{
	int value;
	int height;
	int sonAmount;
	treeNodeE parent;
	treeNodeE left;
	treeNodeE right;
	public treeNodeE(int val) {
		this.value = val;
	}
}


