package logic;

import data.BoardStorage;
import javafx.scene.layout.GridPane;

public class Logic {

	private BoardStorage data;
	
	private DragAndDropFeature dnd = new DragAndDropFeature();

	private MoveCalculator mc = new MoveCalculator();
	private NumberBoardManipulator nbm = new NumberBoardManipulator();
	private VisualBoardManipulator vbm = new VisualBoardManipulator();

	public Logic(GridPane visualBoard) {
		data = new BoardStorage(visualBoard);

		vbm.populateGridPane(visualBoard, data.getNumberBoard());
		
		dnd.setDragDetection(visualBoard);
	}

	
	
	/*
	 * Drag and drop detection complete. Write logic to make chess piece move to target field
	 * 
	 * 
	 * 
	 */
}
