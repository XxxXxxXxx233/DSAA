package lab7;

import java.util.Scanner;

public class PetAdoption {
	private static treeNodeF root = null;
	private static long ans = 0;
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
		System.out.println(ans);
	}
	
	
	
	public static void findClosest(int num) {
		treeNodeF close = closest(root, num);
		ans += Math.abs(num - close.value);
		delete(close.value);
	}
	
	public static treeNodeF closest(treeNodeF root, int num) {
		if(root == null || root.value == num)
			return root;
		if(num > root.value) {
			treeNodeF right = closest(root.right, num);
			if(right != null) {
				if(Math.abs(num - root.value) < Math.abs(num - right.value)) {
					return root;
				} else if(Math.abs(num - root.value) == Math.abs(num - right.value)) {
					if(root.value < right.value) {
						return root;
					} else {
						return right;
					}
				} else {
					return right;
				}
			} else {
				return root;
			}
		} else {
			treeNodeF left = closest(root.left, num);
			if(left != null) {
				if(Math.abs(num - root.value) < Math.abs(num - left.value)) {
					return root;
				} else if(Math.abs(num - root.value) == Math.abs(num - left.value)) {
					if(root.value < left.value) {
						return root;
					} else {
						return left;
					}
				} else {
					return left;
				}
			} else {
				return root;
			}
		}
	}
	
	public static void insertion(int num) {
		treeNodeF tar = new treeNodeF(num);
		if(root == null) {
			root = tar;
			root.height = setHeight(root);
			root.sonAmount = setAmount(root);
		} else {
			treeNodeF temp = root;
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
	
	public static void L_Rotate(treeNodeF parent) {
		treeNodeF R_Child = parent.right;
		treeNodeF RL_Child = R_Child.left;
		treeNodeF upper = parent.parent;
		
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
	
	public static void R_Rotate(treeNodeF parent) {
		treeNodeF L_Child = parent.left;
		treeNodeF LR_Child = L_Child.right;
		treeNodeF upper = parent.parent;
		
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
	
	public static void LR_Rotate(treeNodeF parent) {
		L_Rotate(parent.left);
		R_Rotate(parent);
	}
	
	public static void RL_Rotate(treeNodeF parent) {
		R_Rotate(parent.right);
		L_Rotate(parent);
	}
	
	public static void delete(int num) {
		treeNodeF tar = findPos(num);
		if(tar != null) {
			treeNodeF parent = tar.parent;
			if(tar.left == null && tar.right == null) {
				delete_leaf(tar);
			} else if(tar.right != null){
				treeNodeF succ = tar.right;
				while(succ.left != null)
					succ = succ.left;
				tar.value = succ.value;
				parent = succ.parent;
				if(succ.left == null && succ.right == null) {
					delete_leaf(succ);
				} else {
					treeNodeF rightChild = succ.right;
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
				treeNodeF leftChild = tar.left;
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
	
	public static void delete_leaf(treeNodeF tar) {
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
	
	public static treeNodeF findPos(int num) {
		treeNodeF cur = root;
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
	
	public static treeNodeF getKth(treeNodeF root, int k) {
		treeNodeF ans = null;
		treeNodeF temp = root;
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
	
	public static int setHeight(treeNodeF tar) {
		return Math.max(getHeight(tar.left), getHeight(tar.right)) + 1;
	}
	
	public static int getHeight(treeNodeF tar) {
		if(tar == null) {
			return -1;
		} else {
			return tar.height;
		}
	}
	
	public static int setAmount(treeNodeF tar) {
		return getAmount(tar.left) + getAmount(tar.right) + 2;
	}
	
	public static int getAmount(treeNodeF tar) {
		if(tar == null) {
			return -1;
		} else {
			return tar.sonAmount;
		}
	}
	
}

class treeNodeF{
	int value;
	int height;
	int sonAmount;
	treeNodeF parent;
	treeNodeF left;
	treeNodeF right;
	public treeNodeF(int val) {
		this.value = val;
	}
}


