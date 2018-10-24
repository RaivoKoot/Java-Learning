package logic;

import data.Card;

public class CardOperations {

	public static void applyDamage(Card card, int amount)
	{
		card.setCurrentHealth(card.getCurrentHealth() - amount);
	}

	public static void battle(Card card1, Card card2)
	{
		applyDamage(card1, card2.getCurrentPower());
		applyDamage(card2, card1.getCurrentPower());
	}

	public static void increasePower(Card card, int amount)
	{
		card.setCurrentPower(card.getCurrentPower() + amount);
	}

	public static void resetHealth(Card card)
	{
		card.setCurrentHealth(card.getBaseHealth());
	}

	public static void resetPower(Card card)
	{
		card.setCurrentPower(card.getBasePower());
	}

	public static boolean isCardDead(Card card)
	{
		if (card.getCurrentHealth() <= 0)
			return true;

		return false;
	}

	public static void updateCardInterface(Card card)
	{
		card.getUi().updateValues();
	}
}
