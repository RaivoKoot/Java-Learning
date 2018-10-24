package logic;

import data.Player;
import data.PlayerList;

public class GameHoster {
	private Player currentPlayer;
	private Player otherPlayer;
	private PlayerList playerList;
	private boolean gameStarted =  false;

	public GameHoster()
	{
		playerList = new PlayerList();
		currentPlayer = playerList.get(0);
		otherPlayer = playerList.get(1);
		setGameStarted(true);
		
		CommandInvoker.gameHoster = this;
	}

	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	public Player getOtherPlayer()
	{
		return otherPlayer;
	}

	public void setOtherPlayer(Player otherPlayer)
	{
		this.otherPlayer = otherPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer)
	{
		this.currentPlayer = currentPlayer;
	}

	public boolean isGameStarted()
	{
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted)
	{
		this.gameStarted = gameStarted;
	}

}
