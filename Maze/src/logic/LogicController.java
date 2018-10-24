package logic;

import java.io.IOException;
import java.util.ArrayList;

import data.Data;

public class LogicController {

	private FileScanner scanner;
	private Algorithm algorithm = new Algorithm();
	private Data data;

	public void runAlgorithm()
	{
		algorithm.runAlgorithm(data);
	}

	public ArrayList<int[]> getMoveHistory()
	{
		return data.getLocationHistory();
	}

	// done every time before the algorithm is run
	// sets the starting location and the move combinations
	public void resetEverything(int x, int y, char[] moves) throws IOException
	{
		scanner = new FileScanner();
		data = new Data();
		data.setMazeArray(scanner.readMaze());
		data.setMazeArrayCopy(scanner.readMaze());

		setStartingPosition(x, y);
		setMoveCombination(moves);
	}

	public void setStartingPosition(int x, int y)
	{
		data.setcurrentLocation(x, y);
	}

	public void setMoveCombination(char[] moves)
	{
		data.setMoveCombinations(moves);
	}

	public Data getData()
	{
		return data;
	}

	public FileScanner getScanner()
	{
		return scanner;
	}

}
