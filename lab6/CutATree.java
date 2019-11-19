package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class CutATree {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int number = in.nextInt();
			int[][] link = new int[number-1][3];
			
			treeNode[] arr = new treeNode[number+1];
			arr[0] = new treeNode(-1);
			treeNode root = new treeNode(1);
			arr[1] = root;						//set 1 as root
			
			for(int i=0; i<number-1; i++) {
				link[i][0] = in.nextInt();
				link[i][1] = in.nextInt();
				if(link[i][0] > link[i][1]) {	//make sure node1 is smaller
					int temp = link[i][1];
					link[i][1] = link[i][0];
					link[i][0] = temp;
				}
			}
			
			int redCount = 0;
			int blueCount = 0;
			int[] color = new int[number+1];
			for(int i=1; i<number+1; i++) {
				color[i] = in.nextInt();
				if(color[i] == 1) {
					redCount++;
				} else if(color[i] == 2) {
					blueCount++;
				}
			}
			setColor(arr[1], color[1]);
			
			int count = 0;
			while(count < number-1) {
				for(int i=0; i<number - 1; i++) {
					if(link[i][2] == 1)
						continue;
					int node1 = link[i][0];
					int node2 = link[i][1];
					if(arr[node1] == null && arr[node2] != null) {
						treeNode temp = new treeNode(node1);
						arr[node1] = temp;
						setColor(arr[node1], color[node1]);
						link[i][2] = 1;
						arr[node1].parent = arr[node2];
						arr[node2].child.add(arr[node1]);
						count++;
					} else if(arr[node1] != null && arr[node2] == null) {
						treeNode temp = new treeNode(node2);
						arr[node2] = temp;
						setColor(arr[node2], color[node2]);
						link[i][2] = 1;
						arr[node2].parent = arr[node1];
						arr[node1].child.add(arr[node2]);
						count++;
					} else if(arr[node1] == null && arr[node2] == null){
						continue;
					}
				}
			}
			outputTree(root);
/*			for(int i=1; i<number + 1; i++) {
				System.out.print(arr[i].value + " in " + arr[i].color + " has child [");
				for(int j=0; j<arr[i].child.size(); j++) {
					if(j == arr[i].child.size()-1) {
						System.out.print(arr[i].child.get(j).value);
					} else {
						System.out.print(arr[i].child.get(j).value + ", ");
					}
				}
				System.out.println("]");
			}*/
			int answer = 0;
			for(int i=1; i<number+1; i++) {
				if(arr[i].red == redCount && arr[i].blue == 0) {
					answer++;
				} else if(arr[i].blue == blueCount && arr[i].red == 0) {
					answer++;
				}
			}
			System.out.println(answer);
		}
	}
	
	public static void outputTree(treeNode root) {
		if(root != null) {
			if(!root.child.isEmpty()) {
				for(int i=0; i<root.child.size(); i++) {
					outputTree(root.child.get(i));
					root.red += root.child.get(i).red;
					root.blue += root.child.get(i).blue;
				}
			}
			if(root.color.equals("red")) {
				root.red++;
			} else if(root.color.equals("blue")) {
				root.blue++;
			}
//			System.out.println(root.value + " in " + root.color + " " + root.red + " " + root.blue);
		}
	}
	
	public static void setColor(treeNode node, int number) {
		if(number == 0) {
			node.color = "white";
		} else if(number == 1) {
			node.color = "red";
		} else {
			node.color = "blue";
		}
	}
}

class treeNode{
	int value;
	int red;
	int blue;
	String color;
	treeNode parent;
	ArrayList<treeNode> child = new ArrayList<>();
	
	treeNode(int val) {
		this.value = val;
	}
}
