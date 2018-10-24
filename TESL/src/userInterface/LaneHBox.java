package userInterface;

import data.Player;
import javafx.scene.layout.HBox;

public class LaneHBox extends HBox {

	private Player owner;
	private boolean highlighted;
	private String laneSide; // leftLane / rightLane
	
	public LaneHBox() {
		super();
		super.setMinHeight(250);
		super.setMinWidth(200);
	}

	public boolean isHighlighted()
	{
		return highlighted;
	}

	public void setHighlighted(boolean highlighted)
	{
		this.highlighted = highlighted;
	}

	public Player getOwner()
	{
		return owner;
	}

	public void setOwner(Player owner)
	{
		this.owner = owner;
	}

	public String getLaneSide()
	{
		return laneSide;
	}

	public void setLaneSide(String laneSide)
	{
		this.laneSide = laneSide;
	}
}
