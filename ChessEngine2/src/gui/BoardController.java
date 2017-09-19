package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import logic.FigureView;
import logic.Logic;
import logic.MoveCalculator;
import logic.NumberBoardManipulator;

public class BoardController implements Initializable {

	Logic logic;

	@FXML
	GridPane visualBoard;
	@FXML
	GridPane moveHighlighter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initializeBackgroundBoard(backgroundBoard);

		logic = new Logic(visualBoard, moveHighlighter);

	}
}

////////////////////////////// Note: make sure to clean the getChildren list of
////////////////////////////// the GridPane gameboard when moving or adding
////////////////////////////// pieces. getChildren.remove(figureview object)
