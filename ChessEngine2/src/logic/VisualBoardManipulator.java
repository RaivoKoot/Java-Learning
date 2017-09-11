package logic;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class VisualBoardManipulator {

	// populates a GridPane Board from a populated numberBoard
	public void populateGridPane(GridPane gameBoard, int[] numberBoard) {
		int currentType;

		for (int i = 0; i < 100; i++) {
			currentType = 999; // default
			currentType = numberBoard[i];

			FigureView chessPiece = new FigureView(currentType);
			int[] coordinate = numberToCoordinate(i);

			gameBoard.add(chessPiece, coordinate[0], coordinate[1]);

		}
	}
	
	// translates a location from a numberBoard into a coordinate on a GridPane
	public int[] numberToCoordinate(int number) {
		int[] coordinate = new int[2];
		int row = number / 10;
		int column = number % 10;
		coordinate[0] = column;
		coordinate[1] = row;

		return coordinate;

	}

	// unfinished
	// public void replaceNode(FigureView)

}
