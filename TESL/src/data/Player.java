package data;

import commands.ICommand;
import commands.StartTurnCommand;
import logic.CommandInvoker;

public class Player {

	private CardFields fields;
	private MagickaData magickaData;
	private HealthRuneData healthData;

	//private

	public Player()
	{
		fields = new CardFields();
		magickaData = new MagickaData();
		healthData = new HealthRuneData();
	}

	public void startTurn()
	{
		// send command to invoker
		ICommand startTurnCommand = new StartTurnCommand(this);
		CommandInvoker.runCommand(startTurnCommand);

	}

	public CardFields getFields()
	{
		return fields;
	}

	public void setFields(CardFields fields)
	{
		this.fields = fields;
	}

	public MagickaData getMagickaData()
	{
		return magickaData;
	}

	public void setMagickaData(MagickaData magickaData)
	{
		this.magickaData = magickaData;
	}

	public HealthRuneData getHealthData()
	{
		return healthData;
	}

	public void setHealthData(HealthRuneData healthData)
	{
		this.healthData = healthData;
	}

}
