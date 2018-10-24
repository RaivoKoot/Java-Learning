package logic;

import data.Card;
import data.CardFields;
import data.CardList;

public class CardFieldOperations {

	public static boolean drawCard(CardFields fields)
	{
		CardList deck = fields.getList("deck");
		if (deck.isEmpty())
			return false;

		Card nextCard = deck.get(0);

		CardListOperations.moveCardToAList(nextCard, "hand", fields);

		return true;

	}

	public static void CardToDiscard(Card card, CardFields fields)
	{
		CardListOperations.moveCardToAList(card, "discardPile", fields);
	}

	public static void putCardOnField(Card card, String lane, CardFields fields)
	{
		CardListOperations.moveCardToAList(card, lane, fields);
	}
}
