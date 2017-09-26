package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

	@FXML
	ChoiceBox<Integer> choiceBoxDepth;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBoxDepth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));

	}

	public void btnStartPressed() {
		int chosenDifficulty = 0;
		if (choiceBoxDepth.getValue() != null)
			chosenDifficulty = choiceBoxDepth.getValue();

		if (chosenDifficulty > 0 && chosenDifficulty < 6) {
			visualBoard.getChildren().clear();
			logic = new Logic(visualBoard, moveHighlighter, chosenDifficulty);
		}

	}
}
