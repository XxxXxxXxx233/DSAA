package lab6;

import java.util.Scanner;

public class FindTheDepth {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int number = in.nextInt();
			treeNodeDepth[] arr = new treeNodeDepth[number+1];
			arr[0] = new treeNodeDepth(-1);
			treeNodeDepth root = new treeNodeDepth(1);
			root.depth = 0;
			arr[1] = root;
			int[][] link = new int[number-1][3];
			for(int i=0; i<number-1; i++) {
				link[i][0] = in.nextInt();
				link[i][1] = in.nextInt();
				if(link[i][0] > link[i][1]) {	//make sure node1 is smaller
					int temp = link[i][1];
					link[i][1] = link[i][0];
					link[i][0] = temp;
				}
			}
			
			int count = 0;
			while(count < number-1) {
				for(int i=0; i<number - 1; i++) {
					if(link[i][2] == 1)
						continue;
					int node1 = link[i][0];
					int node2 = link[i][1];
					if(arr[node1] == null && arr[node2] != null) {
						treeNodeDepth temp = new treeNodeDepth(node1);
						arr[node1] = temp;
						arr[node1].depth = arr[node2].depth + 1;
						link[i][2] = 1;
						count++;
					} else if(arr[node1] != null && arr[node2] == null) {
						treeNodeDepth temp = new treeNodeDepth(node2);
						arr[node2] = temp;
						arr[node2].depth = arr[node1].depth + 1;
						link[i][2] = 1;
						count++;
					} else if(arr[node1] == null && arr[node2] == null){
						continue;
					}
				}
			}
			
			System.out.print(arr[1].depth + " ");
			for(int i=2; i<number+1; i++) {
				System.out.print(arr[i].depth + " ");
			}
			System.out.println();
		}
	}
}

class treeNodeDepth{
	int value;
	int depth;
	treeNodeDepth up;
	treeNodeDepth left;
	treeNodeDepth right;
	
	treeNodeDepth(int val) {
		this.value = val;
	}
}
