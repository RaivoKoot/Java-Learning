package commands;

import data.Card;
import logic.CardOperations;

public class BattleCardsCommand implements ICommand {

	private Card card1;
	private Card card2;

	public BattleCardsCommand(Card card1, Card card2)
	{
		this.card1 = card1;
		this.card2 = card2;
	}

	@Override
	public void execute()
	{
		CardOperations.battle(card1, card2);
	}

}
