package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class CutTheStick {
	public static int sum = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int number = in.nextInt();
			ArrayList<treeNodeStick> list = new ArrayList<>();
			for(int i=0; i<number; i++) {
				list.add(new treeNodeStick(in.nextInt()));
			}
			treeNodeStick root = huffmanTree(list);
			preorderprint(root);
			System.out.println(sum);
			sum = 0;
		}
	}
	
	public static treeNodeStick huffmanTree(ArrayList<treeNodeStick> list) {
		while(list.size() > 1) {
			quickSort(list, 0, list.size()-1);
			treeNodeStick l = list.get(0);
			treeNodeStick r = list.get(1);
			treeNodeStick parent = new treeNodeStick(true, l.value + r.value);
			parent.left = l;
			parent.right = r;
			list.remove(0);
			list.remove(0);
			list.add(parent);
		}
		return list.get(0);
	}
	
	public static void preorderprint(treeNodeStick root) {
		if(root.isSum) {
			sum += root.value;
		}
		if(root.left != null) {
			preorderprint(root.left);
		}
		if(root.right != null) {
			preorderprint(root.right);
		}
	}
	
	public static void quickSort(ArrayList<treeNodeStick> arr, int begin, int end) {
		if(begin > end)
			return;
		int l = begin;
		int r = end;
		int pivot = arr.get(l).value;
		while(l<r) {
			while(l<r && arr.get(r).value > pivot)
				r--;
			while(l<r && arr.get(l).value <= pivot)
				l++;
			if(l<r) {
				treeNodeStick temp = arr.get(r);
				arr.set(r, arr.get(l));
				arr.set(l, temp);
			}
		}
		
		treeNodeStick temp = arr.get(begin);
		arr.set(begin, arr.get(l));
		arr.set(l, temp);
		
		quickSort(arr, begin, l-1);
		quickSort(arr, l+1, end);
	}
	
}

class treeNodeStick{
	int value;
	boolean isSum;
	treeNodeStick left;
	treeNodeStick right;
	
	treeNodeStick(int val) {
		this.isSum = false;
		this.value = val;
	}
	treeNodeStick(boolean check, int val) {
		this.isSum = check;
		this.value = val;
	}
}
