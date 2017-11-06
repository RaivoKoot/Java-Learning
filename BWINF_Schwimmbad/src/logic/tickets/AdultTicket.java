package logic.tickets;

/*
 * ticket for 3.50 for people 17+
 */
public class AdultTicket extends Ticket {

	public AdultTicket(boolean isWeekday) {
		super();
		if (isWeekday)
			super.setPrice(3.50 * 0.9);
		else
			super.setPrice(3.50);
		
		super.setName("17+ Ticket");
		super.setTicketType(1);
		super.setRequiredPeople(new int[]{3});
	}

}
