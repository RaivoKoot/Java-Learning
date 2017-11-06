package logic.tickets;

public class Ticket implements Comparable<Ticket> {

	private double price = -999.999;
	private int[] requiredPeopleTypes = null;
	private int[] alternateRequiredPeopleTypes = null;

	private String name = "NONAME";
	private int ticketType = -1;

	private boolean validOnWeekend = true;
	private boolean validOnWeekdays = true;
	private boolean validInVacation = true;

	public String toString() {
		String ticketInfo = name + ": " + price;
		return ticketInfo;
	}

	public void setRequiredPeople(int[] peopleTypes) {
		requiredPeopleTypes = peopleTypes;
	}

	public int[] getRequiredPeopleTypes() {
		return requiredPeopleTypes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public int[] getAlternateRequiredPeopleTypes() {
		return alternateRequiredPeopleTypes;
	}

	public void setAlternateRequiredPeopleTypes(int[] alternateRequiredPeopleTypes) {
		this.alternateRequiredPeopleTypes = alternateRequiredPeopleTypes;
	}

	public int getTicketType() {
		return ticketType;
	}

	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public int compareTo(Ticket otherTicket) {

		if (otherTicket.getTicketType() > this.ticketType)
			return -1;
		else if (otherTicket.getTicketType() < this.ticketType)
			return 1;
		else
			return 0;
	}
}
