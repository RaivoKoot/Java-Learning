package logic;

import data.Player;
import data.PlayerList;

public class PlayerOperations {

	public static boolean playerAboveZeroHealth(Player player)
	{
		int health = player.getHealthData().getHealth();

		return health > 0;
	}

	public static boolean playersAlive(PlayerList players)
	{
		return playerAboveZeroHealth(players.get(0)) && playerAboveZeroHealth(players.get(1));
	}

}
