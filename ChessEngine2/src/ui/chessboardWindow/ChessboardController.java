package ui.chessboardWindow;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.Logic;
import ui.Main;

public class ChessboardController implements Initializable {

	Logic logic;

	@FXML
	GridPane visualBoard;
	@FXML
	GridPane moveHighlighter;
	@FXML
	BorderPane rootPane;

	@FXML
	ChoiceBox<Integer> choiceBoxDepth;

	@FXML
	Button leaderboardButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBoxDepth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));

		setPaneBackgroundImage(rootPane);

	}

	public void leaderboardButtonPressed() {
		Main.launchLeaderboardWindow();
	}

	public void setPaneBackgroundImage(Pane pane) {
		BackgroundImage background = new BackgroundImage(
				new Image("file:backgroundImage2.jpg", 1920, 1080, false, true), BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		pane.setBackground(new Background(background));
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
