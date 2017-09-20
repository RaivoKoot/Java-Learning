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
	public Logic(GridPane visualBoard, GridPane moveHighlighter) {
		data = new BoardStorage(visualBoard, moveHighlighter);

		vbm.populateGridPane(visualBoard, data.getNumberBoard());

		setupDragAndDrop(visualBoard);

		turnManager = new TurnManager();
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

			System.out.println(turnManager.isPlayersTurn());
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

			System.out.println("drag detected");

		});
	}

	public void setupDragDropped(Node node) {
		node.setOnDragDropped(event -> {
			FigureView destinationNode = (FigureView) event.getSource();

			System.out.println("Droppevent");

			int dropLocation = destinationNode.getArraylocation();

			if (!availableMoves.contains(dropLocation))
				event.consume();

			else {

				executeAMove(sourcePiece, destinationNode, data.getNumberBoard(), data.getVisualBoard());

				// signal that players turn is finished
				turnManager.setPlayersTurn(false);

				// make AI move
				makeAIMove(data.getNumberBoard(), data.getVisualBoard());

			}
		});
	}

	public void makeAIMove(int[] numberBoard, GridPane visualBoard) {
		ChessMove aiMove = turnManager.miniMax(true, data.getNumberBoard(), data.getAiPieceLocations(),
				data.getUserPieceLocations(), 0, null);

		FigureView movingNode = vbm.getANode(aiMove.getStartingLocation(), visualBoard);
		FigureView destinationNode = vbm.getANode(aiMove.getDestinationLocation(), visualBoard);

		executeAMove(movingNode, destinationNode, numberBoard, visualBoard);

		turnManager.setPlayersTurn(true);

	}

	public void executeAMove(FigureView movingNode, FigureView destinationNode, int[] numberBoard,
			GridPane visualBoard) {

		// save the dragged pieces location before it is moved
		int originLocation = movingNode.getArraylocation();

		// pass on move information for execution
		Node fieldMovedFrom = vbm.makeMove(movingNode, destinationNode, visualBoard);

		// make the new empty origin field go through the drag and drop setup
		makeNodeDraggable(fieldMovedFrom);

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
			System.out.println("Drag over");
		});
	}

	public void setupDragDone(Node node, GridPane moveHighlighter) {
		node.setOnDragDone(event -> {
			System.out.println("DRAG FINISHED");
			vbm.removeHighlights(availableMoves, moveHighlighter);
		});
	}
}
