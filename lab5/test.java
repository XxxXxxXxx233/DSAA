package lab5;

public class test {
	public static void main(String[] args) {
		String t = "aabababababbcbacbacaca";
		int[] arr1 = nextNotInclude(t.toCharArray(), t.length());
		for(int i=0; i<arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		
		int[] arr2 = nextInclude(t.toCharArray(), t.length());
		for(int i=0; i<arr2.length; i++) {
			System.out.print(arr2[i] + " ");
		}
		System.out.println();
	}
	
	public static int[] nextNotInclude(char[] tArr, int tLen) {
		int[] next = new int[tLen];
		int j = 0;
		int k = -1;
		next[0] = -1;
		while(j < tLen - 1) {
			if(k == -1 || tArr[j] == tArr[k]) {
				k++;
				j++;
				next[j] = k;
			} else {
				k = next[k];
			}
		}
		return next;
	}
	
	public static int[] nextInclude(char[] strArr, int strLen) {
		int[] next = new int[strLen];
		next[0] = 0;
		for(int i=1; i<strLen; i++) {
			int position = next[i-1];
			if(strArr[i] == strArr[position]) {
				next[i] = next[i-1] + 1;
			} else {
				while(strArr[i] != strArr[position] && position != 0) {
					position = next[position-1];
					if(strArr[i] == strArr[position]) {
						next[i] = position + 1;
						break;
					}
				}
			}
		}
		return next;
	}
}
