package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

	private char[] moveCombinations;

	private char[][] mazeArray;
	private char[][] mazeArrayCopy;
	private int[] currentLocation;
	private int direction = 1; // 1, 2, 3, 4
	private ArrayList<int[]> locationHistory = new ArrayList<int[]>();

	private static final HashMap<Integer, Character> LOCATION_CHARS;
    static
    {
    	LOCATION_CHARS = new HashMap<Integer, Character>();
    	LOCATION_CHARS.put(1, 'U');
    	LOCATION_CHARS.put(2, 'L');
    	LOCATION_CHARS.put(3, 'D');
    	LOCATION_CHARS.put(4, 'R');
    }
    
    public HashMap<Integer, Character> getLocationChars(){
    	return LOCATION_CHARS;
    }
	
	public char[][] getMazeArray()
	{
		return mazeArray;
	}

	public void setMazeArray(char[][] mazeArray)
	{
		this.mazeArray = mazeArray;
	}

	public int[] getCurrentLocation()
	{
		return currentLocation;
	}

	public void editCurrentLocation(int x, int y)
	{
		currentLocation[0] = x;
		currentLocation[1] = y;
	}

	public int getDirection()
	{
		return direction;
	}

	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	public ArrayList<int[]> getLocationHistory()
	{
		return locationHistory;
	}

	public void addLocationHistory(int x, int y)
	{
		int[] location = { x, y, direction};
		locationHistory.add(location);
	}

	public char getMazeElement(int x, int y)
	{
		return mazeArray[x][y];
	}

	public char[] getMoveCombinations()
	{
		return moveCombinations;
	}

	public void setMoveCombinations(char[] moveCombinations)
	{
		this.moveCombinations = moveCombinations;
	}

	public void setcurrentLocation(int x, int y)
	{
		int[] location = { x, y };
		this.currentLocation = location;
	}

	public char[][] getMazeArrayCopy()
	{
		return mazeArrayCopy;
	}

	public void setMazeArrayCopy(char[][] mazeArrayCopy)
	{
		this.mazeArrayCopy = mazeArrayCopy;
	}

}
