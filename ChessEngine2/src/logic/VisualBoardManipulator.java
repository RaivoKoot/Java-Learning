package logic;

import java.util.ArrayList;

import javafx.collections.ObservableList;
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

	public FigureView getANode(int arrayLocation, GridPane visualBoard) {
		int[] coordinate = numberToCoordinate(arrayLocation);

		ObservableList<Node> children = visualBoard.getChildren();

		for (Node child : children) {
			if (GridPane.getColumnIndex(child) == coordinate[0] && GridPane.getRowIndex(child) == coordinate[1])
				return (FigureView) child;
		}

		return null;
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

	/*
	 * places a node of choice on a new location and vacates its old location
	 */
	public Node[] makeMove(FigureView sourcePiece, FigureView destinationPiece, GridPane visualBoard) {
		// remove origin and destination node
		visualBoard.getChildren().remove(destinationPiece);
		visualBoard.getChildren().remove(sourcePiece);

		// create new empty field to put at origin location
		FigureView fieldLeftFrom = new FigureView(0);

		// put the new empty field object at the location of the dragged node
		// and the
		// dragged node
		// on the destination field
		visualBoard.add(fieldLeftFrom, GridPane.getColumnIndex(sourcePiece), GridPane.getRowIndex(sourcePiece));

		// promotes the piece if it is elligible
		FigureView newQueen = checkForPromotion(sourcePiece, destinationPiece);

		if (newQueen == null)
			// puts the moved piece onto the new GridPane field
			visualBoard.add(sourcePiece, GridPane.getColumnIndex(destinationPiece),
					GridPane.getRowIndex(destinationPiece));
		else
			visualBoard.add(newQueen, GridPane.getColumnIndex(destinationPiece),
					GridPane.getRowIndex(destinationPiece));
		// return the new child that now sits where the piece moved from to set
		// it up
		// with drag and drop
		Node[] newNodes;
		if (newQueen == null) {
			newNodes = new Node[] { fieldLeftFrom };
		} else {
			newNodes = new Node[] { fieldLeftFrom, newQueen };
		}

		return newNodes;
	}

	/*
	 * following two methods are used to highlight the available moves of dragged
	 * piece green
	 */

	private FigureView checkForPromotion(FigureView piece, FigureView destinationNode) {
		/*
		 * checks if a pawn qualifies for promotion
		 * statement the moved piece has the type pawn and is moved onto the last row top or bottom
		 */
		int type = piece.getType();
		int row = GridPane.getRowIndex(destinationNode);
		FigureView newQueen = null;


		if ((type == -1 && row == 1) || (type == 1 && row == 8)) {
			/*
			 * promotes the moved piece into a queen
			 */
			if (type == -1)
				newQueen = new FigureView(-5);
			else
				newQueen = new FigureView(5);
		}

		return newQueen;
	}

	public void highlightFields(ArrayList<Integer> locations, GridPane moveHighlighter) {
		ObservableList<Node> highlightFields = moveHighlighter.getChildren();

		for (int location : locations) {
			highlightFields.get(location).setOpacity(0.7);
		}
	}

	public void removeHighlights(ArrayList<Integer> locations, GridPane moveHighlighter) {
		ObservableList<Node> highlightFields = moveHighlighter.getChildren();

		for (int location : locations) {
			highlightFields.get(location).setOpacity(0.0);
		}
	}

}
