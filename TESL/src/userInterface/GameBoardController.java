package userInterface;

import java.net.URL;
import java.util.ResourceBundle;

import commands.EndTurnCommand;
import commands.ICommand;
import commands.StartTurnCommand;
import data.Card;
import data.CardList;
import data.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.CommandInvoker;
import logic.GameHoster;

public class GameBoardController implements Initializable {

	private GameHoster gameHoster;

	// @FXML
	private LaneHBox leftLaneTop;
	// @FXML
	private LaneHBox rightLaneTop;
	// @FXML
	private LaneHBox leftLaneBottom;
	// @FXML
	private LaneHBox rightLaneBottom;

	@FXML
	private VBox leftLane;
	@FXML
	private VBox rightLane;

	@FXML
	private HBox topZone;
	@FXML
	private HBox lowerZone;

	@FXML
	private Button endTurnButton;
	@FXML
	private Button startGameButton;

	@FXML
	Label magickaTop;
	@FXML
	Label magickaBottom;

	private Player topPlayer;
	private Player bottomPlayer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		leftLaneTop = new LaneHBox();
		leftLaneBottom = new LaneHBox();

		rightLaneTop = new LaneHBox();
		rightLaneBottom = new LaneHBox();
		
		rightLaneTop.setLaneSide("rightLane");
		rightLaneBottom.setLaneSide("rightLane");
		leftLaneBottom.setLaneSide("leftLane");
		leftLaneTop.setLaneSide("leftLane");

		leftLane.getChildren().addAll(leftLaneTop, leftLaneBottom);
		rightLane.getChildren().addAll(rightLaneTop, rightLaneBottom);

	}

	public void startGameButtonClicked()
	{
		UIOperations.setupDragOver(rightLaneBottom);
		UIOperations.setupDragOver(rightLaneTop);
		UIOperations.setupDragOver(leftLaneBottom);
		UIOperations.setupDragOver(leftLaneTop);

		gameHoster = new GameHoster();
		CommandInvoker.uiController = this;

		bottomPlayer = gameHoster.getCurrentPlayer();
		topPlayer = gameHoster.getOtherPlayer();
		
		leftLaneBottom.setOwner(bottomPlayer);
		rightLaneBottom.setOwner(bottomPlayer);
		leftLaneTop.setOwner(topPlayer);
		rightLaneTop.setOwner(topPlayer);
		startTurn();
	}

	public void endTurnButtonClicked()
	{
		ICommand endTurn = new EndTurnCommand(gameHoster.getCurrentPlayer());
		CommandInvoker.runCommand(endTurn);

		startTurn();
	}

	private void startTurn()
	{
		ICommand startTurn = new StartTurnCommand(gameHoster.getCurrentPlayer());
		CommandInvoker.runCommand(startTurn);
		updateMagickaLabels();
		updateHand();
	}

	private void updateMagickaLabels()
	{
		int currentMagickaTop = topPlayer.getMagickaData().getCurrentMagicka();
		int maxMagickaTop = topPlayer.getMagickaData().getMaxMagicka();

		int currentMagickaBottom = bottomPlayer.getMagickaData().getCurrentMagicka();
		int maxMagickaBottom = bottomPlayer.getMagickaData().getMaxMagicka();

		magickaTop.setText(currentMagickaTop + "/" + maxMagickaTop);
		magickaBottom.setText(currentMagickaBottom + "/" + maxMagickaBottom);
	}

	public void updateHand()
	{
		CardList list = gameHoster.getCurrentPlayer().getFields().getList("hand");
		Card drawnCard = list.get(list.size() - 1);
		CardInterface drawnCardUI = new CardInterface(drawnCard);
		drawnCardUI.setCardOwner(gameHoster.getCurrentPlayer());

		if (gameHoster.getCurrentPlayer() == topPlayer)
			topZone.getChildren().add(drawnCardUI);
		else
			lowerZone.getChildren().add(drawnCardUI);
	}

	public void summonCreature()
	{

	}

	public void highlightLanes(CardInterface card)
	{
		if (card.getCardOwner() == topPlayer)
		{
			UIOperations.highlightNode(leftLaneTop);
			UIOperations.highlightNode(rightLaneTop);
		} else
		{
			UIOperations.highlightNode(leftLaneBottom);
			UIOperations.highlightNode(rightLaneBottom);
		}
	}

	public void removeHighlightLanes()
	{
		UIOperations.removeHighlightNode(leftLaneBottom);
		UIOperations.removeHighlightNode(leftLaneTop);
		UIOperations.removeHighlightNode(rightLaneBottom);
		UIOperations.removeHighlightNode(rightLaneTop);
	}

}
