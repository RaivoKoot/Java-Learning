package userInterface;

import data.Player;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import logic.CommandInvoker;

public class UIOperations {

	public static void highlightNode(LaneHBox node)
	{
		node.setStyle("-fx-background-color: lightyellow");
		node.setHighlighted(true);

	}

	public static void removeHighlightNode(LaneHBox node)
	{
		node.setStyle("-fx-background-color: transparent");
		node.setHighlighted(false);
	}

	public static void setupHoverEntered(CardInterface card)
	{
		card.setOnMouseEntered(event -> {
			Node source = (Node) event.getSource();
			source.setScaleX(1.5);
			source.setScaleY(1.5);
		});
	}

	public static void setupHoverExited(CardInterface card)
	{
		card.setOnMouseExited(event -> {
			Node source = (Node) event.getSource();
			source.setScaleX(1);
			source.setScaleY(1);
		});
	}

	public static void setupOnDragDetected(CardInterface card)
	{
		card.setOnDragDetected(event -> {
			CardInterface source = (CardInterface) event.getSource();

			// check if cardOwner is the current turn owner
			Player cardOwner = source.getCardOwner();
			Player currentPlayer = CommandInvoker.gameHoster.getCurrentPlayer();

			// check for sufficient magicka
			int magickaLeft = cardOwner.getMagickaData().getCurrentMagicka();
			int magickaCostCard = source.getCard().getMagickaCost();

			// do the checks
			if (currentPlayer == cardOwner && magickaLeft >= magickaCostCard)
			{
				// start drag
				source.startFullDrag();

				Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

				ClipboardContent content = new ClipboardContent();

				content.putImage(source.getImage());

				db.setContent(content);

				CommandInvoker.uiController.highlightLanes(source);
			}
		});
	}

	public static void setupOnDragDone(CardInterface card)
	{
		card.setOnDragDone(event -> {
			CommandInvoker.uiController.removeHighlightLanes();
		});
	}

	public static void addDropShadow(Node node)
	{
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		node.setEffect(dropShadow);
	}

	public static void setupDragOver(Node node)
	{
		node.setOnDragOver(event -> {
			LaneHBox source = (LaneHBox) event.getSource();

			if (source.isHighlighted())
			{
				event.acceptTransferModes(TransferMode.ANY);
				event.consume();
			}
		});
	}
}
