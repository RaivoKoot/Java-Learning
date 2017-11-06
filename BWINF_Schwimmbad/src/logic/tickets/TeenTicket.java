package logic.tickets;

/*
 * ticket for 2.50 for kids from the age of 4 to 16
 */
public class TeenTicket extends Ticket {

	public TeenTicket(boolean isWeekday) {
		super();
		if (isWeekday)
			super.setPrice(2.50 * 0.9);
		else
			super.setPrice(2.50);

		super.setName("Teen Ticket");
		super.setTicketType(1);
		super.setRequiredPeople(new int[] { 2 });
	}
}
