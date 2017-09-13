package logic;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

public class DragAndDropFeature {

	public void setDragDetection(GridPane visualBoard) {
		ObservableList<Node> children = visualBoard.getChildren();

		for (Node temp : children) {
			initializeDragDetection(temp);
			initializeDragEntered(temp);
			initializeDragOver(temp);
			initializeDragDropped(temp);
		}
	}

	public void initializeDragDetection(Node node) {
		node.setOnDragDetected(event -> {

			FigureView x = (FigureView) event.getSource();

			if (x.getType() == 0 || x.getType() == 100)
				event.consume();
			else {

				Dragboard db = x.startDragAndDrop(TransferMode.MOVE);

				ClipboardContent content = new ClipboardContent();

				content.putImage(x.getImage());

				db.setContent(content);
			}

		});
	}

	public void initializeDragDropped(Node node) {
		node.setOnDragDropped(event -> {
			FigureView x = (FigureView) event.getSource();

			Dragboard db = event.getDragboard();
			System.out.println("Droppevent");
		});
	}

	public void initializeDragEntered(Node node) {
		node.setOnDragEntered(event -> {
			System.out.println("Drag entered");
		});
	}

	public void initializeDragOver(Node node) {
		node.setOnDragOver(event -> {
			event.acceptTransferModes(TransferMode.ANY);
			event.consume();
			System.out.println("Drag over");
		});
	}

}
