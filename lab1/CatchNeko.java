package lab1;

import java.util.Scanner;

public class CatchNeko {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		long xEve = in.nextLong();
		long yEve = in.nextLong();
		long xNeko = in.nextLong();
		long yNeko = in.nextLong();
		int length = in.nextInt();
		String move = in.next();
		String[] arr = new String[length];
		for(int i=0; i<length; i++) {
			arr[i] = move.substring(i, i+1);
		}
		
		long tMax = (long)(2 *1e14);
		long left = 0;
		long right = tMax;
		long t = 0;
		boolean caught = false;
		while(left<=right) {
			t =left + (right - left)/2;
			long distance = distance(xEve, yEve, xNeko, yNeko, arr, t);
			if(t >= distance && (t-1)<distance(xEve, yEve, xNeko, yNeko, arr, t-1)) {
				caught = true;
				break;
			}
			if(t >= distance) {
				right = t - 1;
			} else {
				left = t + 1;
			}
		}
		if(caught)
			System.out.println(t);
		else
			System.out.println(-1);
	}
	
	public static String move(long xNeko, long yNeko, String[] arr, long t) {
		long xOrigin = xNeko;
		long yOrigin = yNeko;
		int length = arr.length;
		long cycle = t / length;
		for(int i=0; i<length; i++) {
			switch(arr[i]) {
			case "U":
				yNeko += 1;
				break;
			case "D":
				yNeko -= 1;
				break;
			case "L":
				xNeko -= 1;
				break;
			case "R":
				xNeko += 1;
				break;
			}
		}
		
		long xDelta = xNeko - xOrigin;
		long yDelta = yNeko - yOrigin;
		
		xNeko = xOrigin + (xDelta * cycle);
		yNeko = yOrigin + (yDelta * cycle);
		
		int rest = (int)(t - (cycle * length));
		for(int i=0; i<rest; i++) {
			switch(arr[i]) {
			case "U":
				yNeko += 1;
				break;
			case "D":
				yNeko -= 1;
				break;
			case "L":
				xNeko -= 1;
				break;
			case "R":
				xNeko += 1;
				break;
			}
		}
		return xNeko + " " + yNeko;
	}
	
	public static long distance(long xEve, long yEve, long xNeko, long yNeko, String[] arr, long t) {
		
		String[] coordinate = move(xNeko, yNeko, arr, t).split(" ");
		xNeko = Long.parseLong(coordinate[0]);
		yNeko = Long.parseLong(coordinate[1]);
		
		return (Math.abs(xNeko - xEve)) + (Math.abs(yNeko - yEve));
	}
}
