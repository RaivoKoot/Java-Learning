package logic;

import java.util.Arrays;

import data.Data;

public class DataOperations {

	// returns true if the exit has been found
	public static boolean makeMove(char whichWay, Data data)
	{
		if (whichWay == 'F')
			return moveForward(data.getDirection(), data);
		else
			turn(whichWay, data);

		return false;
	}

	// change value of data.direction to represent a turn left or right
	// possible values for direction: {1,2,3,4}
	public static void turn(char whichWay, Data data)
	{
		int increment = 1;

		if (whichWay == 'R')
			increment *= -1;

		int direction = data.getDirection();
		direction += increment;

		if (direction == 5)
			direction = 1;
		if (direction == 0)
			direction = 4;

		data.setDirection(direction);
		System.out.println("turned " + whichWay + ". New Direction: " + data.getDirection());
	}

	// returns the coordinates of the field next(infront) to the current, based on the
	// direction parameter
	public static int[] getForwardLocation(int[] currentLocation, int direction)
	{
		int x = currentLocation[0];
		int y = currentLocation[1];

		// make a change to coordinates of 2d array to either move one element location
		// up left down or right
		switch (direction) {
		case 1:
			y -= 1;
			break;
		case 2:
			x -= 1;
			break;
		case 3:
			y += 1;
			break;
		case 4:
			x += 1;
			break;
		}

		int[] newLocation = { x, y };
		System.out.println("Forward Location: " + Arrays.toString(newLocation));

		return newLocation;
	}

	// returns false if the parameter coordinate contains a wall
	public static boolean isFieldFree(int[] location, Data data)
	{
		char element = data.getMazeElement(location[0], location[1]);
		if (element == '#')
		{
			System.out.println("Field not free");
			return false;
		}

		return true;
	}

	// returns true if the parameter coordinate contains the finish marker
	public static boolean isFieldGoal(int[] location, Data data)
	{
		char element = data.getMazeElement(location[0], location[1]);
		if (element == 'G')
			return true;

		return false;
	}

	// if path is not obstructed, moves data.currentLocation one coordinate forward
	// and records new move in location history
	// returns true if the exit has been found
	public static boolean moveForward(int direction, Data data)
	{
		int[] currentLocation = data.getCurrentLocation();
		int[] newLocation = getForwardLocation(currentLocation, direction);

		if (isFieldGoal(newLocation, data))
			return true;
		if (!isFieldFree(newLocation, data))
			return false;
		
		// make changes in data object
		data.editCurrentLocation(newLocation[0], newLocation[1]);
		data.addLocationHistory(newLocation[0], newLocation[1]);

		return false;
	}

	// converts a String of Left, Right, Front commands into an array containing
	// those characters. "LRL" => {'L','R','L'}
	public static char[] parseMoves(String moves)
	{
		char[] moveCombo = new char[moves.length()];
		for (int i = 0; i < moves.length(); i++)
		{
			moveCombo[i] = moves.charAt(i);
		}

		return moveCombo;
	}

}
