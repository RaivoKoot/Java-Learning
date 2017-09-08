package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import data.FigureView;
import data.MoveCalculator;
import data.NumberBoard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class BoardController implements Initializable {

	@FXML
	GridPane board;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NumberBoard nb = new NumberBoard();
		MoveCalculator mc = new MoveCalculator();

		nb.populateGridPane(board);
		System.out.println("CHILDREN: " + board.getChildren().size());

		FigureView testtest = new FigureView(-5);
		board.add(testtest, 2, 5);

		System.out.println("CHILDREN: " + board.getChildren().size());

		int[] numberBoard = nb.gridpaneToNumberboard(board);
		System.out.print(nb.getString(numberBoard));

		ArrayList<Integer> possibleMoves = mc.generatePossibleMoves(52, numberBoard);

		for (int temp : possibleMoves)
			System.out.println(temp);

	}

}

////////////////////////////// IMPORTANT: Queen moves are not properly generated

////////////////////////////// Note: make sure to clean the getChildren list of
////////////////////////////// the GridPane gameboard when moving or adding
////////////////////////////// pieces. getChildren.remove(figureview object)
