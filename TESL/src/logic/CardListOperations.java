package logic;

import java.util.Random;

import data.Card;
import data.CardFields;
import data.CardList;

public class CardListOperations {

	public static void moveCardToAList(Card card, String list, CardFields fields)
	{
		removeCardFromOwnList(card, fields);
		fields.getList(list).add(card);
	}

	public static void removeCardFromAList(Card card, CardList list)
	{
		list.remove(card);
	}

	public static void removeCardFromOwnList(Card card, CardFields fields)
	{
		for (CardList list : fields.getValues())
		{

			if (list.contains(card))
			{
				removeCardFromAList(card, list);
				return;
			}

		}

		System.out.println("Something went from removing the card from its list");

	}

	public static Card getRandomCard(CardList list)
	{
		Random random = new Random();
		int index = random.nextInt(list.size());

		Card randomChosenCard = list.get(index);

		return randomChosenCard;
	}

	public static boolean hasSpace(CardList list, String listName)
	{
		// list is a lane
		if (listName.contains("Lane"))
		{

			if (list.size() < 4)
				return true;
		}
		// list is a hand
		else if (listName.equals("hand"))
		{
			if (list.size() < 10)
				return true;
		}
		// list is a deck
		else if (listName.equals("deck"))
		{
			if (list.size() < 50)
				return true;
		}

		return false;
	}
}
