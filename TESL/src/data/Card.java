package data;

import javafx.scene.image.Image;
import userInterface.CardInterface;

public class Card {

	private String name = "Raivo Koot";
	private int baseHealth = 7;
	private int basePower = 5;
	private int currentHealth = 7;
	private int currentPower = 5;
	private int magickaCost = 5;
	private Image image = new Image("file:///C:/Users/Raivo%20Koot/Documents/Eclipse%20Workspace/images%20esl/foto.jpg");
	
	private CardInterface ui;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getBaseHealth()
	{
		return baseHealth;
	}

	public void setBaseHealth(int baseHealth)
	{
		this.baseHealth = baseHealth;
	}

	public int getBasePower()
	{
		return basePower;
	}

	public void setBasePower(int basePower)
	{
		this.basePower = basePower;
	}

	public int getCurrentHealth()
	{
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth)
	{
		this.currentHealth = currentHealth;
	}

	public int getCurrentPower()
	{
		return currentPower;
	}

	public void setCurrentPower(int currentPower)
	{
		this.currentPower = currentPower;
	}

	public int getMagickaCost()
	{
		return magickaCost;
	}

	public void setMagickaCost(int magickaCost)
	{
		this.magickaCost = magickaCost;
	}

	public CardInterface getUi()
	{
		return ui;
	}

	public void setUi(CardInterface ui)
	{
		this.ui = ui;
	}

	public Image getImage()
	{
		return image;
	}

	public void setImage(Image image)
	{
		this.image = image;
	}

}
