package userInterface;

import data.Card;
import data.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CardInterface extends VBox {

	private Label magickaCost;
	private Label name;
	private Label health;
	private Label power;
	private String state; // attackReady, idle
	private String location; // deck, hand, leftLane, rightLane
	private Player cardOwner;
	private Card card;

	public CardInterface(Card card)
	{
		super();
		super.setPrefSize(100, 140);
		super.setMaxSize(100, 140);

		super.setStyle("-fx-border-color: black;-fx-border-width: 3;-fx-background-color:gray");
		this.card = card;
		card.setUi(this);

		// fill labels with text
		magickaCost = new Label(String.valueOf(card.getMagickaCost()));
		name = new Label(card.getName());
		health = new Label(String.valueOf(card.getCurrentHealth()));
		power = new Label(String.valueOf(card.getCurrentPower()));

		// setup banner UI
		HBox banner = new HBox();
		Label spacing = new Label("spacing");
		spacing.setVisible(false);
		banner.getChildren().addAll(magickaCost, spacing, name);

		// setup center UI
		HBox center = new HBox();
		Label spacing2 = new Label("spacing");
		spacing2.setVisible(false);
		center.getChildren().addAll(health, spacing2, power);

		UIOperations.addDropShadow(this);
		UIOperations.setupHoverEntered(this);
		UIOperations.setupHoverExited(this);
		UIOperations.setupOnDragDetected(this);
		UIOperations.setupOnDragDone(this);

		// add banner and center to rootPane
		super.getChildren().addAll(banner, center);
	}

	public void updateValues()
	{
		health.setText(String.valueOf(card.getCurrentHealth()));
		power.setText(String.valueOf(card.getCurrentPower()));
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Player getCardOwner()
	{
		return cardOwner;
	}

	public void setCardOwner(Player cardOwner)
	{
		this.cardOwner = cardOwner;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public Image getImage()
	{
		return card.getImage();
	}
	public Card getCard() {
		return card;
	}

}
