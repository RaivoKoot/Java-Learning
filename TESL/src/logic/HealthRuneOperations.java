package logic;

import data.HealthRuneData;

public class HealthRuneOperations {

	public static void subtractHealth(HealthRuneData data, int amount)
	{
		data.setHealth(data.getHealth() - amount);
	}
}
