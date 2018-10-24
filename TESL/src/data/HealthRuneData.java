package data;

public class HealthRuneData {

	private int health;
	private int runesLeft;
	
	public HealthRuneData() {
		health = 30;
		runesLeft = 5;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getRunesLeft()
	{
		return runesLeft;
	}

	public void setRunesLeft(int runesLeft)
	{
		this.runesLeft = runesLeft;
	}
	
	
}
