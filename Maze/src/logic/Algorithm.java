package logic;

import data.Data;

public class Algorithm {

	public void runAlgorithm(Data data)
	{
		char[] moveCombinations = data.getMoveCombinations();

		for (int j = 0; j < 50; j++)
			for (int i = 0; i < moveCombinations.length; i++)
			{
				char action = moveCombinations[i];

				DataOperations.makeMove(action, data);
			}
	}

}
