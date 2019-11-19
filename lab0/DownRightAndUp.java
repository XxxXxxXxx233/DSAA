package lab0;

import java.util.Scanner;

public class DownRightAndUp {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			String input = in.next();
			boolean isCoordinate = false;
			if(input.contains(","))
				isCoordinate = true;
			if(isCoordinate)
				findNumberFromCoordinate(input);
			else
				findCoordinateFromNumber(input);
		}
	}
	
	public static void findNumberFromCoordinate(String input) {
		String[] coordinate = input.split("\\(|,|\\)");
		int x = Integer.parseInt(coordinate[1]);
		int y = Integer.parseInt(coordinate[2]);
		int bigger = x >= y ? x : y;
		
		int biggestEdge = 2;
		int loopNumber = 1;
		for(int i=0; i<=bigger; i++) {
			if(biggestEdge < bigger) {
				biggestEdge *= 2;
				loopNumber++;
			} else {
				break;
			}
		}
		long saveBiggestEdge = biggestEdge;
//		System.out.println("BiggestEdge " + biggestEdge);
//		System.out.println("loopNumber " + loopNumber);
		
		int[] areaNumber = new int[loopNumber];
		for(int i=0; i<loopNumber; i++) {
//			System.out.println("X = " + x + " and Y = " + y);
			if(biggestEdge == 2) {
				if(x==1 && y==1) {
					areaNumber[i] = 1;
				} else if (x==2 && y==1) {
					areaNumber[i] = 2;
				} else if (x==2 && y==2) {
					areaNumber[i] = 3;
				} else {
					areaNumber[i] = 4;
				}
			} else if (biggestEdge > 2){
				biggestEdge /= 2;
				if (x > biggestEdge && y <= biggestEdge) {
					areaNumber[i] = 2;
				} else if (x > biggestEdge && y > biggestEdge) {
					areaNumber[i] = 3;
				} else if (x <= biggestEdge && y > biggestEdge){
					areaNumber[i] = 4;
				} else {
					areaNumber[i] = 1;
				}
			}
			if(biggestEdge >= 2) {
				switch (areaNumber[i]) {
				case 2:
					x -= biggestEdge;
					break;
				case 3:
					x -= biggestEdge;
					y -= biggestEdge;
					break;
				case 4:
					y -= biggestEdge;
					break;
				}
			}
		}
				
		long number = 0;
		for(int i=0; i<loopNumber; i++) {
			saveBiggestEdge /= 2;
			if(saveBiggestEdge == 1) {
				number += areaNumber[i];
			} else {
				number += saveBiggestEdge * saveBiggestEdge * (areaNumber[i] - 1); 	
			}
		}
		System.out.println(number);
	}
	
	public static void findCoordinateFromNumber(String input) {
		long number = Long.parseLong(input);
		int x = 1;
		int y = 0;
		int upper = 0;
		if(number == 1) {
			upper = 1;
		} else {
			for(int i=0; i<number; i++) {
				if(Math.pow(4, i) > number) {
					upper = i;
					break;
				}
			}
		}
//		System.out.println("Upper " + upper);
		
		while (number>0) {
			long edgeSize = (long)Math.pow(2, (upper));
//			System.out.println("EdgeSize " + edgeSize);
			
			int divideRest = (int)(number / (edgeSize * edgeSize / 4));
//			System.out.println("DivideRest " + divideRest);	
			
			number -=  (edgeSize * edgeSize / 4) * divideRest;
//			System.out.println("Number " + number);
			
			if(number == 0) {
				switch(divideRest) {
				case 0:
					y += edgeSize;
					break;
				case 1:
					y += edgeSize/2;
					break;
				case 2:
					x += edgeSize/2;
					y += edgeSize/2;
					break;
				case 3:
					x += edgeSize/2;
					y += edgeSize;
					break;
				}
			} else {
				switch(divideRest) {
				case 0:
					break;
				case 1:
					x += edgeSize/2;
					break;
				case 2:
					x += edgeSize/2;
					y += edgeSize/2;
					break;
				case 3:
					y += edgeSize/2;
					break;
				}
			}
//			System.out.println("(" + x + "," + y + ")");
			upper--;
		}
		
		System.out.println("(" + x + "," + y + ")");
	}
}
