package quiz1;


import java.util.Scanner;

public class quickSortQuiz {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = in.nextInt();
		System.out.println("Origin arr : ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		quickSort(arr, 0, n-1);
	}
	
	public static void quickSort(int[] arr, int low, int high) {
		if(low < high) {
			int pi = partition(arr, low, high);
			
			for(int i=0; i<arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			
			quickSort(arr, low, pi-1);
			quickSort(arr, pi+1, high);
		}
	}
	
	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low-1;
		for(int j=low; j<=high-1; j++) {
			if(arr[j] <= pivot) {
				i++;
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		return i+1;
	}
}
