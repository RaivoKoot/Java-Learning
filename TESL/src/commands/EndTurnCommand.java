package commands;

import data.Player;
import logic.CommandInvoker;

public class EndTurnCommand implements ICommand {

	private Player player;

	public EndTurnCommand(Player player)
	{
		this.player = player;
	}

	@Override
	public void execute()
	{
		CommandInvoker.gameHoster.setCurrentPlayer(CommandInvoker.gameHoster.getOtherPlayer());
		CommandInvoker.gameHoster.setOtherPlayer(player);

	}

}
