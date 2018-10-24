package commands;

import data.Card;
import data.CardFields;
import logic.CardFieldOperations;

public class SummonCreatureCommand implements ICommand {
	private Card card;
	private String lane;
	private CardFields fields;

	public SummonCreatureCommand(Card card, String lane, CardFields fields)
	{
		this.card = card;
		this.lane = lane;
		this.fields = fields;
	}

	@Override
	public void execute()
	{
		CardFieldOperations.putCardOnField(card, lane, fields);

	}

}
