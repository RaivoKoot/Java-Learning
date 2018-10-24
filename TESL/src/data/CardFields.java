package data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class CardFields {

	private CardList deck;
	private CardList hand;
	private CardList discardPile;
	private CardList leftLane;
	private CardList rightLane;

	private HashMap<String, CardList> listCollection;

	public CardFields()
	{
		clearLists();
		assignHashMap();

		// for testing purposes
		for (int i = 0; i < 10; i++)
			deck.add(new Card());
	}

	private void clearLists()
	{
		deck = new CardList();
		hand = new CardList();
		discardPile = new CardList();
		leftLane = new CardList();
		rightLane = new CardList();
	}

	private void assignHashMap()
	{
		listCollection = new HashMap<String, CardList>();

		listCollection.put("deck", deck);
		listCollection.put("hand", hand);
		listCollection.put("discardPile", discardPile);
		listCollection.put("leftLane", leftLane);
		listCollection.put("rightLane", rightLane);

	}

	public CardList getList(String key)
	{
		return listCollection.get(key);
	}

	public Set<String> getKeys()
	{
		return listCollection.keySet();
	}

	public Collection<CardList> getValues()
	{
		return listCollection.values();
	}
}
