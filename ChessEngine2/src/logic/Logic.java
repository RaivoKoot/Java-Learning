package logic;

import java.util.ArrayList;
import data.BoardStorage;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import turnManagement.TurnManager;

public class Logic {

	private BoardStorage data;
	private TurnManager turnManager;

	private MoveCalculator mc = new MoveCalculator();
	private NumberBoardManipulator nbm = new NumberBoardManipulator();
	private VisualBoardManipulator vbm = new VisualBoardManipulator();

	/*
	 * constructor
	 */
	public Logic(GridPane visualBoard, GridPane moveHighlighter, int chosenAlgoDepth) {
		data = new BoardStorage(visualBoard, moveHighlighter);

		vbm.populateGridPane(visualBoard, data.getNumberBoard());

		setupDragAndDrop(visualBoard);

		turnManager = new TurnManager(chosenAlgoDepth);
	}

	/*
	 * Drag and Drop implementation
	 */

	FigureView sourcePiece;
	ArrayList<Integer> availableMoves;
	ArrayList<Region> highlightedFields;

	public void setupDragAndDrop(GridPane visualBoard) {
		ObservableList<Node> children = visualBoard.getChildren();

		for (Node child : children) {
			makeNodeDraggable(child);
		}

	}

	public void makeNodeDraggable(Node node) {
		GridPane moveHighlightingPane = data.getMoveHighlighter();
		setupDragDetection(node, moveHighlightingPane);
		setupDragOver(node);
		setupDragDropped(node);
		setupDragDone(node, moveHighlightingPane);
	}

	public void setupDragDetection(Node node, GridPane moveHighlightingPane) {
		node.setOnDragDetected(event -> {

			FigureView originPiece = (FigureView) event.getSource();

			if (originPiece.getType() > -1)
				event.consume();
			else if (turnManager.isPlayersTurn()) {

				sourcePiece = originPiece;

				int originLocation = originPiece.getArraylocation();

				availableMoves = mc.generatePossibleMoves(originLocation, data.getNumberBoard());

				vbm.highlightFields(availableMoves, moveHighlightingPane);

				Dragboard db = originPiece.startDragAndDrop(TransferMode.MOVE);

				ClipboardContent content = new ClipboardContent();

				content.putImage(originPiece.getImage());

				db.setContent(content);
			}

		});
	}

	public void setupDragDropped(Node node) {
		node.setOnDragDropped(event -> {
			FigureView destinationNode = (FigureView) event.getSource();

			int dropLocation = destinationNode.getArraylocation();

			if (!availableMoves.contains(dropLocation))
				event.consume();

			else {

				executeAMove(sourcePiece, destinationNode, data.getNumberBoard(), data.getVisualBoard());

				// signal that players turn is finished
				turnManager.setPlayersTurn(false);

				// make AI move
				makeAIMove(true, data.getNumberBoard(), data.getVisualBoard());
				System.out.println("Calls to Minimax Function:" + turnManager.getCallsToMinimax());
				if (turnManager.getCallsToMinimax() > turnManager.getMaximumCallsToMinimax())
					turnManager.setMaximumCallsToMinimax(turnManager.getCallsToMinimax());
				turnManager.setCallsToMinimax(0);
				System.out
						.println("\nmaximum calls to minimax: " + turnManager.getMaximumCallsToMinimax() / 1000 + "k");
			}
		});
	}

	public void makeAIMove(boolean makeBlackTurn, int[] numberBoard, GridPane visualBoard) {

		ChessMove aiMove = turnManager.miniMax(makeBlackTurn, data.getNumberBoard(), data.getAiPieceLocations(),
				data.getUserPieceLocations(), 0, null, -9999999, 9999999);

		FigureView movingNode = vbm.getANode(aiMove.getStartingLocation(), visualBoard);
		FigureView destinationNode = vbm.getANode(aiMove.getDestinationLocation(), visualBoard);

		executeAMove(movingNode, destinationNode, numberBoard, visualBoard);

		if (!aiMove.isGameOver())
			turnManager.setPlayersTurn(true);
		

	}

	public void executeAMove(FigureView movingNode, FigureView destinationNode, int[] numberBoard,
			GridPane visualBoard) {

		// save the dragged pieces location before it is moved
		int originLocation = movingNode.getArraylocation();

		// pass on move information for execution and return new nodes that need
		// to be set up for drag and drop
		Node[] fieldMovedFromAndNewQueen = vbm.makeMove(movingNode, destinationNode, visualBoard);

		/*
		 * make the field that has been moved from, which is now empty drag and drop compatible
		 * also do this for a new
		 */
		for (Node newNode : fieldMovedFromAndNewQueen) {
			makeNodeDraggable(newNode);
		}

		// get the destination of the drag and drop
		int dropLocation = destinationNode.getArraylocation();

		// synchronize the numberBoard with the new move
		nbm.makeMove(originLocation, dropLocation, data.getNumberBoard());

		if (movingNode.getType() > 0) {
			nbm.updatePieceLocations(data.getAiPieceLocations(), data.getUserPieceLocations(), originLocation,
					dropLocation);
		} else {
			nbm.updatePieceLocations(data.getUserPieceLocations(), data.getAiPieceLocations(), originLocation,
					dropLocation);
		}

	}

	public void setupDragOver(Node node) {
		node.setOnDragOver(event -> {
			event.acceptTransferModes(TransferMode.ANY);
			event.consume();
		});
	}

	public void setupDragDone(Node node, GridPane moveHighlighter) {
		node.setOnDragDone(event -> {

			vbm.removeHighlights(availableMoves, moveHighlighter);
		});
	}
}
