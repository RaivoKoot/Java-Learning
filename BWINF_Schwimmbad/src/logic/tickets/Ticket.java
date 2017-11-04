package logic.tickets;

public class Ticket {

	private double price = -999.999;
	private double weekdayPrice = -999.999;
	private double activePrice = -999.999;

	private String name = "NONAME";

	private boolean validOnWeekend = true;
	private boolean validOnWeekdays = true;
	private boolean validInVacation = true;

	public String toString() {
		String ticketInfo = name + ": " + price;
		return ticketInfo;
	}

	public void activateWeekdayPrice() {
		setActivePrice(weekdayPrice);
	}

	public void activateWeekendPrice() {
		setActivePrice(price);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;

		this.setWeekdayPrice(price * 0.8);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValidInVacation() {
		return validInVacation;
	}

	public void setValidInVacation(boolean validInVacation) {
		this.validInVacation = validInVacation;
	}

	public boolean isValidOnWeekdays() {
		return validOnWeekdays;
	}

	public void setValidOnWeekdays(boolean validOnWeekdays) {
		this.validOnWeekdays = validOnWeekdays;
	}

	public boolean isValidOnWeekend() {
		return validOnWeekend;
	}

	public void setValidOnWeekend(boolean validOnWeekend) {
		this.validOnWeekend = validOnWeekend;
	}

	public double getWeekdayPrice() {
		return weekdayPrice;
	}

	public void setWeekdayPrice(double weekdayPrice) {
		this.weekdayPrice = weekdayPrice;
	}

	public double getActivePrice() {
		return activePrice;
	}

	public void setActivePrice(double activePrice) {
		this.activePrice = activePrice;
	}

}
