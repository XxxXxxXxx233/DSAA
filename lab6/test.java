package lab6;

public class test {
	public static void main(String[] args) {
		treeNodeTest root = new treeNodeTest(26);
		treeNodeTest left1 = new treeNodeTest(12);
		treeNodeTest right1 = new treeNodeTest(32);
		root.left = left1;
		root.right = right1;
		treeNodeTest left2left = new treeNodeTest(4);
		treeNodeTest left2right = new treeNodeTest(18);
		left1.left = left2left;
		left1.right = left2right;
		treeNodeTest left3left = new treeNodeTest(14);
		treeNodeTest left3right = new treeNodeTest(24);
		left2right.left = left3left;
		left2right.right = left3right;
		
		System.out.print("Preorder : ");
		preorderprint(root);
		System.out.println();
		System.out.print("Postorder : ");
		postorderprint(root);
		System.out.println();
		System.out.print("Inorder : ");
		inorderprint(root);
		System.out.println();
		System.out.print("Using Iterative : ");
		preorderiterative(root);
		System.out.println();
		
		treeNodeTest Croot = new treeNodeTest("-");
		treeNodeTest Cleft1 = new treeNodeTest("+");
		treeNodeTest Cright1 = new treeNodeTest("/");
		Croot.left = Cleft1;
		Croot.right = Cright1;
		treeNodeTest Cleft2left = new treeNodeTest("a");
		treeNodeTest Cleft2right = new treeNodeTest("*");
		Cleft1.left = Cleft2left;
		Cleft1.right = Cleft2right;
		treeNodeTest Cleft3left = new treeNodeTest("b");
		treeNodeTest Cleft3right = new treeNodeTest("c");
		Cleft2right.left = Cleft3left;
		Cleft2right.right = Cleft3right;
		treeNodeTest Cright1left = new treeNodeTest("d");
		treeNodeTest Cright1right = new treeNodeTest("e");
		Cright1.left = Cright1left;
		Cright1.right = Cright1right;
		
		System.out.print("Preorder : ");
		inorderprintchar(Croot);
		System.out.println();
	}
	
	public static void preorderprint(treeNodeTest root) {
		System.out.print(root.value + " ");
		if(root.left != null) {
			preorderprint(root.left);
		}
		if(root.right != null) {
			preorderprint(root.right);
		}
	}
	
	public static void postorderprint(treeNodeTest root) {
		if(root.left != null) {
			postorderprint(root.left);
		}
		if(root.right != null) {
			postorderprint(root.right);
		}
		System.out.print(root.value + " ");
	}
	
	public static void inorderprint(treeNodeTest root) {
		if(root.left != null) {
			inorderprint(root.left);
		}
		System.out.print(root.value + " ");
		if(root.right != null) {
			inorderprint(root.right);
		}
	}
	
	public static void preorderiterative(treeNodeTest root) {
		int top = -1;
		treeNodeTest[] arr = new treeNodeTest[10000];
		top++;
		arr[top] = root;
		while(top != -1) {
			treeNodeTest node = arr[top];
			System.out.print(node.value + " ");
			top--;
			if(node.right != null) {
				top++;
				arr[top] = node.right;
			}
			if(node.left != null) {
				top++;
				arr[top] = node.left;
			}
		}
	}
	
	public static void inorderprintchar(treeNodeTest root) {
		if(root.left != null) {
			System.out.print("(");
			inorderprintchar(root.left);
		}
		System.out.print(root.str);
		if(root.right != null) {
			inorderprintchar(root.right);
			System.out.print(")");
		}
	}
	
}

class treeNodeTest{
	int value;
	String str;
	treeNodeTest left;
	treeNodeTest right;
	
	treeNodeTest(int val) {
		this.value = val;
	}
	treeNodeTest(String s) {
		this.str = s;
	}
}
