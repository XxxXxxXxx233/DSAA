package lab0;

import java.util.ArrayList;
import java.util.Scanner;

public class Sudoku {
	public static String[][] sudoku = new String[9][9];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> rPos = new ArrayList<>();
		ArrayList<Integer> cPos = new ArrayList<>();
		int j=0;
		int k=0;
		while(j<9) {
			while(k<9) {
				String temp = in.next();
				if(!temp.equals("|")) {
					if(temp.equals("x")) {
						rPos.add(j);
						cPos.add(k);
					}
					sudoku[j][k] = temp;
					k++;
				}
			}
			j++;
			k=0;
		}
		
		Boolean check = true;
		int xNumber = rPos.size();
		Boolean solved = true;
		label1:
		while(true) {
			int xFirst = xNumber;
			int number = 0;
			for(int i=0; i<rPos.size(); i++) {
				int row = rPos.get(i);
				int col = cPos.get(i);
				if(sudoku[row][col].equals("x")) {
					check = isSpecialBlank(row, col);
					if(!check)
						number++;
					xNumber = number;
				}
			}
			int xLast = xNumber;
			if(xFirst == xLast) {
				for(int r=0; r<9; r++) {
					for(int c=0; c<9; c++) {
						if(sudoku[r][c].equals("x")) {
							solved = false;
						}
					}
				}
				break label1;
			}
		}
		
		if(solved) {
			for(int i=0; i<9; i++) {
				for(int o=0; o<9; o++) {
					System.out.print(sudoku[i][o]+" ");
					if((o+1)%3==0) {
						System.out.print("|");
						if((o+1)%9!=0)
							System.out.print(" ");
					}
				}
				if((i+1)%3==0)
					System.out.println();
				System.out.println();
			}
		} else {
			System.out.println("The test data is incorrect!");
		}
	}
	
	public static void solveSudoku(int row, int col, int solution) {
		int number = 45;
		switch(solution) {
		case 1:
			for(int i=0; i<9; i++) {
				if(i != col) {
					number -= Integer.parseInt(sudoku[row][i]);
				}
			}
			break;
		case 2:
			for(int i=0; i<9; i++) {
				if(i != row)
					number -= Integer.parseInt(sudoku[i][col]);
			}
			break;
		}
		sudoku[row][col] = number + "";
	}
	
	public static boolean isSpecialBlank(int row, int col) {
		boolean check = true;
		//isRowSpecial
		for(int i=0; i<9; i++) {
			if(sudoku[row][i].equals("x") && i != col) {
				check = false;
				break;
			}
		}
		if(check) {
			solveSudoku(row, col, 1);
			return true;
		}
		check = true;
		//isColSpecial
		for(int i=0; i<9; i++) {
			if(sudoku[i][col].equals("x") && i != row) {
				check = false;
				break;
			}
		}
		if(check) {
			solveSudoku(row, col, 2);
			return true;
		}
		
		return check || isBlockSpecial(row, col);
	}
	
	public static boolean isBlockSpecial(int row, int col) {
		boolean check = true;
		int[] pos = new int[2];
		int xNumber = 0;
		if(row<3) {
			pos[0] = 0;
			if(col<3)
				pos[1] = 0;
			else if (col<6)
				pos[1] = 3;
			else
				pos[1] = 6;
		} else if (row<6) {
			pos[0] = 3;
			if(col<3)
				pos[1] = 0;
			else if (col<6)
				pos[1] = 3;
			else
				pos[1] = 6;
		} else {
			pos[0] = 6;
			if(col<3)
				pos[1] = 0;
			else if (col<6)
				pos[1] = 3;
			else
				pos[1] = 6;
		}
		for(int j=pos[0]; j<pos[0]+3; j++) {
			for(int k=pos[1]; k<pos[1]+3; k++) {
				if(sudoku[j][k].equals("x")) {
					xNumber++;
					if(xNumber > 1)
						check = false;
				}
			}
		}
		if(check) {
			int number = 45;
			for(int j=pos[0]; j<pos[0]+3; j++) {
				for(int k=pos[1]; k<pos[1]+3; k++) {
					if(j != row || k != col) {
						number -= Integer.parseInt(sudoku[j][k]);
					}
				}
			}
			sudoku[row][col] = number + "";
		}
		return check;
	}
}
