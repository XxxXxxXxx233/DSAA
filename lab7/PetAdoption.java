package lab7;

import java.util.Scanner;

public class PetAdoption {
	private static treeNode root = null;
	private static int min = 2147483647;
	private static treeNode closest = null;
	private static int answer = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		boolean isPetTree = false;
		for(int i=0; i<n; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			if(root == null) {
				if(a == 0) {
					isPetTree = true;
				} else if (a == 1) {
					isPetTree = false;
				}
			}
			if(a == 0 && isPetTree) {
				insertion(b);
			} else if(a == 1 && isPetTree) {
				findClosest(b);
			} else if(a == 1 && !isPetTree) {
				insertion(b);
			} else if(a == 0 && !isPetTree) {
				findClosest(b);
			}
		}
		System.out.println(answer);
	}
	
	public static void findClosest(int num) {
		preOrder(num, root);
		if(min != 2147483647) {
			answer += min;
			delete(closest.value);
			min = -1;
			closest = null;
		}
	}
	
	public static void preOrder(int num, treeNode root) {
		if(root == null)
			return;
		if(Math.abs(num-root.value) < min) {
			min = Math.abs(num-root.value);
			closest = root;
		}
		if(root.left != null)
			preOrder(num, root.left);
		if(root.right != null)
			preOrder(num, root.right);
	}
	
	public static void insertion(int num) {
		treeNode tar = new treeNode(num);
		if(root == null) {
			root = tar;
			root.height = setHeight(root);
			root.sonAmount = setAmount(root);
		} else {
			treeNode temp = root;
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
	
	public static void L_Rotate(treeNode parent) {
		treeNode R_Child = parent.right;
		treeNode RL_Child = R_Child.left;
		treeNode upper = parent.parent;
		
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
	
	public static void R_Rotate(treeNode parent) {
		treeNode L_Child = parent.left;
		treeNode LR_Child = L_Child.right;
		treeNode upper = parent.parent;
		
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
	
	public static void LR_Rotate(treeNode parent) {
		L_Rotate(parent.left);
		R_Rotate(parent);
	}
	
	public static void RL_Rotate(treeNode parent) {
		R_Rotate(parent.right);
		L_Rotate(parent);
	}
	
	public static void delete(int num) {
		treeNode tar = findPos(num);
		if(tar != null) {
			treeNode parent = tar.parent;
			if(tar.left == null && tar.right == null) {
				delete_leaf(tar);
			} else if(tar.right != null){
				treeNode succ = tar.right;
				while(succ.left != null)
					succ = succ.left;
				tar.value = succ.value;
				parent = succ.parent;
				if(succ.left == null && succ.right == null) {
					delete_leaf(succ);
				} else {
					treeNode rightChild = succ.right;
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
				treeNode leftChild = tar.left;
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
	
	public static void delete_leaf(treeNode tar) {
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
	
	public static treeNode findPos(int num) {
		treeNode cur = root;
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
	
	public static treeNode getKth(treeNode root, int k) {
		treeNode ans = null;
		treeNode temp = root;
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
	
	public static int setHeight(treeNode tar) {
		return Math.max(getHeight(tar.left), getHeight(tar.right)) + 1;
	}
	
	public static int getHeight(treeNode tar) {
		if(tar == null) {
			return -1;
		} else {
			return tar.height;
		}
	}
	
	public static int setAmount(treeNode tar) {
		return getAmount(tar.left) + getAmount(tar.right) + 2;
	}
	
	public static int getAmount(treeNode tar) {
		if(tar == null) {
			return -1;
		} else {
			return tar.sonAmount;
		}
	}
	
}

class treeNode{
	int value;
	int height;
	int sonAmount;
	treeNode parent;
	treeNode left;
	treeNode right;
	public treeNode(int val) {
		this.value = val;
	}
}


