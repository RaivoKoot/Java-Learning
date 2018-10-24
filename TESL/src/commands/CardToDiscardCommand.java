package commands;

import data.Card;
import data.CardFields;
import logic.CardFieldOperations;

public class CardToDiscardCommand implements ICommand {

	private Card card;
	private CardFields fields;

	public CardToDiscardCommand(Card card, CardFields fields)
	{
		this.card = card;
		this.fields = fields;
	}

	@Override
	public void execute()
	{
		CardFieldOperations.CardToDiscard(card, fields);

	}

}
