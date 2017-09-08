package data;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class NumberBoard {
	private int[] numberBoard;

	// 0 empty
	// BLACK: 1 pawn, 2 rook, 3 bishop, 4 knight, 5 queen, 6 king
	// WHITE: -1 pawn, -2 rook, -3 bishop, -4 knight, -5 queen, -6 king
	/*
	 * public static void main(String[] args) { NumberBoard board = new
	 * NumberBoard();
	 * 
	 * System.out.println(board.getString(board.getNumberBoard()));
	 * 
	 * }
	 */
	public int[] getNumberBoard() {
		return numberBoard;
	}

	// Initializes an array of 100 ints for the game board
	public NumberBoard() {
		numberBoard = new int[] { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 2, 4, 3, 5, 6, 3, 4, 2, 100,
				100, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 100,
				100, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, -1, -1, -1, -1, -1, -1, -1, -1,
				100, 100, -2, -4, -3, -5, -6, -3, -4, -2, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
	}

	////////////////// IMPORTANT/////////////////////
	// issue where newly added nodes are not properly put in the array because they
	////////////////// are added to the end of the children list and the former node
	////////////////// is not removed
	public int[] gridpaneToNumberboard(GridPane board) {
		int[] arrayBoard = new int[100];

		int index = -1;
		int col = -1;
		int row = -1;
		for (Node temp : board.getChildren()) {
			FigureView child = (FigureView) temp;
			col = GridPane.getColumnIndex(child);
			row = GridPane.getRowIndex(child);

			int[] coordinate = { col, row };
			index = coordinateToNumber(coordinate);

			arrayBoard[index] = child.getType();
			index = -1;
			col = -1;
			row = -1;
		}
		
		return arrayBoard;
	}

	// unfinished
	// public void replaceNode(FigureView)

	// changes type of specific location on number board
	public void makeMove(int originField, int targetField) {
		numberBoard[targetField] = numberBoard[originField];
		numberBoard[originField] = 0;
	}

	// returns the gridpane coordinate that corresponds to the numberboard
	// location
	public int[] numberToCoordinate(int number) {
		int[] coordinate = new int[2];
		int row = number / 10;
		int column = number % 10;
		coordinate[0] = column;
		coordinate[1] = row;

		return coordinate;

	}

	public int coordinateToNumber(int[] coordinate) {
		int number = 0;

		number += coordinate[0];
		number += 10 * coordinate[1];

		return number;
	}

	// populates the gridpane argument from the int array 'numberBoard'
	public void populateGridPane(GridPane gameBoard) {
		int currentType;

		for (int i = 0; i < 100; i++) {
			currentType = 999; // default
			currentType = numberBoard[i];

			FigureView chessPiece = new FigureView(currentType);
			int[] coordinate = numberToCoordinate(i);

			gameBoard.add(chessPiece, coordinate[0], coordinate[1]);
			
		}
	}

	public String printableCoordinate(int[] coordinate) {
		String representation = "";

		representation += "(" + coordinate[0] + "," + coordinate[1] + ")";

		return representation;
	}

	// returns a visualization of the numberboard
	public String getString(int[] numberBoard) {
		String board_representation = "";

		int currentSymbol = 999;

		board_representation += "[ ";

		for (int k = 0; k < 100; k++) {
			currentSymbol = 999;
			currentSymbol = numberBoard[k];

			if (currentSymbol > -1 && currentSymbol != 100)
				board_representation += 0;
			board_representation += currentSymbol + ", ";

			if ((k + 1) % 10 == 0) {
				board_representation += "]\n";
				if (k != 99)
					board_representation += "[ ";
			}
		}

		return board_representation;

	}

	/*
	 * @format:off
	 * [ 00, 01, 02, 03, 04, 05, 06, 07 ]
	 * [ 08, 09, 10, 11, 12, 13, 14, 15 ]
	 * [ 16, 17, 18, 19, 20, 21, 22, 23 ]
	 * [ 24, 25, 26, 27, 28, 29, 30, 31 ]
	 * [ 32, 33, 34, 35, 36, 37, 38, 39 ]
	 * [ 40, 41, 42, 43, 44, 45, 46, 47 ]
	 * [ 48, 49, 50, 51, 52, 53, 54, 55 ]
	 * [ 56, 57, 58, 59, 60, 61, 62, 63 ]
	 * 
	 */
	/*
			0	  1		2	  3		4	  5		6	  7
	0	[ (0,0),(0,1),(0,2),(0,3),(0,4),(0,5),(0,6),(0,7) ]
	
	1	[ (1,0),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7) ]
	
	2	[ (2,0),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7) ]
	
	3	[ (3,0),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7) ]
	
	4	[ (4,0),(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7) ]
	
	5	[ (5,0),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7) ]
	
	6	[ (6,0),(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7) ]
	
	7	[ (7,0),(7,1),(7,2),(7,3),(7,4),(7,5),(7,6),(7,7) ]
	
	*/

}
