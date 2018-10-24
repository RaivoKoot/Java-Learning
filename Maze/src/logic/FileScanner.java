package logic;
import java.io.IOException;

import sheffield.*;

public class FileScanner {

	// scans the text file and returns a 2d array containing the maze
	public char[][] readMaze() throws IOException
	{
		EasyReader reader = new EasyReader("maze.txt");
		String line;

		char[][] mazeArray = new char[14][14];

		for (int i = 0; (line = reader.readLine()) != null; i++)
		{

			for (int j = 0; j < line.length(); j++)
			{
				mazeArray[j][i] = line.charAt(j);
			}
		}

		return mazeArray;
	}

	// returns a string that when printed displays the maze correctly
	public String arrayToString(char[][] array)
	{
		String visualization = "";

		for (int i = 0; i < 14; i++)
		{
			for (int j = 0; j < 14; j++)
			{
				visualization += array[j][i];
			}
			visualization += "\n";
		}
		return visualization;
	}
}
