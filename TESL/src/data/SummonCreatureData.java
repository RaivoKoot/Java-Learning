package data;

public class SummonCreatureData {

	private Player summoningPlayer;
	private String laneSide; // leftLane / rightLane
	private Card card;

	public Player getSummoningPlayer()
	{
		return summoningPlayer;
	}

	public void setSummoningPlayer(Player summoningPlayer)
	{
		this.summoningPlayer = summoningPlayer;
	}

	public String getLaneSide()
	{
		return laneSide;
	}

	public void setLaneSide(String laneSide)
	{
		this.laneSide = laneSide;
	}

	public Card getCard()
	{
		return card;
	}

	public void setCard(Card card)
	{
		this.card = card;
	}
}
