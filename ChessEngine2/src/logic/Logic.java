package logic;

import java.util.ArrayList;

import data.BoardStorage;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

public class Logic {

	private BoardStorage data;

	private MoveCalculator mc = new MoveCalculator();
	private NumberBoardManipulator nbm = new NumberBoardManipulator();
	private VisualBoardManipulator vbm = new VisualBoardManipulator();

	public Logic(GridPane visualBoard) {
		data = new BoardStorage(visualBoard);

		vbm.populateGridPane(visualBoard, data.getNumberBoard());

		setupDragAndDrop(visualBoard);

		/*
		 * Node test = visualBoard.getChildren().get(25);
		 * visualBoard.getChildren().remove(test);
		 * 
		 * visualBoard.add(test, 5, 3);
		 * System.out.println("Col: "+GridPane.getColumnIndex(test));
		 * System.out.println("Row: "+GridPane.getRowIndex(test));
		 */
	}

	/*
	 * Drag and Drop implementation
	 */

	FigureView sourcePiece;
	ArrayList<Integer> availableMoves;

	int[] destinationLocation;
	int originLocation;

	public void setupDragAndDrop(GridPane visualBoard) {
		ObservableList<Node> children = visualBoard.getChildren();

		for (Node child : children) {
			makeNodeDraggable(child);
		}

	}

	public void makeNodeDraggable(Node node) {
		setupDragDetection(node);
		setupDragOver(node);
		setupDragDropped(node);
	}

	public void setupDragDetection(Node node) {
		node.setOnDragDetected(event -> {

			FigureView originPiece = (FigureView) event.getSource();

			if (originPiece.getType() == 0 || originPiece.getType() == 100)
				event.consume();
			else {

				sourcePiece = originPiece;

				int[] sourceCoordinate = originPiece.getCoordinate();
				originLocation = originPiece.getArraylocation(sourceCoordinate);
				
				System.out.println("Source:"+originLocation);

				availableMoves = mc.generatePossibleMoves(originLocation, data.getNumberBoard());

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

			int[] destinationCoordinate = destinationNode.getCoordinate();

			int destinationArrayLocation = destinationNode.getArraylocation(destinationCoordinate);

			System.out.println(destinationArrayLocation);

			System.out.println("Droppevent");

			if (!availableMoves.contains(destinationArrayLocation))
				event.consume();

			else {
				GridPane visualBoard = data.getVisualBoard();

				// remove origin and destination node
				visualBoard.getChildren().remove(destinationNode);
				visualBoard.getChildren().remove(sourcePiece);

				// create new empty field to put at origin location
				FigureView newOriginField = new FigureView(0);
				makeNodeDraggable(newOriginField);
				
				// put the empty field at the location of the dragged node and the dragged node on the destination field
				visualBoard.add(newOriginField, GridPane.getColumnIndex(sourcePiece), GridPane.getRowIndex(sourcePiece));
				visualBoard.add(sourcePiece, destinationCoordinate[0], destinationCoordinate[1]);
				
				// update the number board of the move
				int[] numberBoard = data.getNumberBoard();
				nbm.makeMove(originLocation, destinationArrayLocation, numberBoard);
		
				
			}
		});
	}

	public void setupDragOver(Node node) {
		node.setOnDragOver(event -> {
			event.acceptTransferModes(TransferMode.ANY);
			event.consume();
			System.out.println("Drag over");
		});
	}
}
