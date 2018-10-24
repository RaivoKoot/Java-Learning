package user_interface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.LogicController;
import logic.DataOperations;

public class UI_Controller implements Initializable {

	@FXML
	private Button btnSave;
	@FXML
	private Button btnRun;
	@FXML
	private Button btnVisualize;

	@FXML
	private TextField fieldX;
	@FXML
	private TextField fieldY;
	@FXML
	private TextField fieldMoves;

	@FXML
	private TextArea display;

	LogicController logic = new LogicController();

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//
	}

	public void saveSettings() throws IOException
	{
		// get location from user input fields
		int x = Integer.parseInt(fieldX.getText());
		int y = Integer.parseInt(fieldY.getText());
		
		// get move sequence from user input field
		char[] moves = DataOperations.parseMoves(fieldMoves.getText());

		// prepare data and logic layer for a fresh run of algorithm
		logic.resetEverything(x, y, moves);
		// 
		displayCurrentLocation(logic.getData().getCurrentLocation(),
				logic.getData().getLocationChars().get(logic.getData().getDirection()));
		logic.getData().addLocationHistory(x, y);
		displayMaze();

	}

	public void runAlgorithm()
	{
		logic.runAlgorithm();
	}

	/*
	 * multithreaded approach to showing the progress of the algorithm in the
	 * ui's display box 
	 */
	public void visualizeResult()
	{
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask()
			{
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception
					{

						ArrayList<int[]> path = logic.getData().getLocationHistory();
						int[] lastLocation = path.get(0);

						// background work
						for (int[] location : path)
						{

							// remove location marker from last location by setting char to ' ' 
							displayCurrentLocation(lastLocation, ' ');
							// get correct location marker based on looking direction
							char locationMarker = logic.getData().getLocationChars().get(location[2]);
							displayCurrentLocation(location, locationMarker);

							lastLocation = location;

							// FX work
							Platform.runLater(new Runnable() {
								@Override
								public void run()
								{
									try
									{
										displayMaze();
									} finally
									{
									}
								}
							});

							Thread.sleep(100);
						}

						// Keep with the background work
						return null;
					}
				};
			}
		};
		service.start();
	}

	public void displayCurrentLocation(int[] location, char newChar)
	{
		// alter element of data.mazeCopy to display current location 
		logic.getData().getMazeArrayCopy()[location[0]][location[1]] = newChar;
	}

	public void displayMaze()
	{
		// get maze data from data 
		char[][] mazeCopy = logic.getData().getMazeArrayCopy();
		
		// convert it to a string
		String stringRepresentation = logic.getScanner().arrayToString(mazeCopy);
		
		// display string in textarea
		display.setText(stringRepresentation);
	}

}
