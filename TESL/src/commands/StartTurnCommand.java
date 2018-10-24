package commands;

import data.Player;
import logic.CardFieldOperations;
import logic.MagickaOperations;

public class StartTurnCommand implements ICommand {

	Player player;

	public StartTurnCommand(Player player)
	{
		this.player = player;
	}

	@Override
	public void execute()
	{
		MagickaOperations.increaseMaxMagicka(player.getMagickaData(), 1);
		MagickaOperations.refreshCurrentMagicka(player.getMagickaData());

		CardFieldOperations.drawCard(player.getFields());

	}
}
