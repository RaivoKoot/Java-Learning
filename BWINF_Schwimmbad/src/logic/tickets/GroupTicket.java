package logic.tickets;

import java.util.Arrays;

/*
 * pass
 */
public class GroupTicket extends Ticket {

	public GroupTicket(int ticketSize) {
		super();
		super.setPrice(11.00);
		super.setName("Group Ticket");
		super.setTicketType(3);
		super.setValidOnWeekend(false);

		int[] requiredPeople = new int[ticketSize];
		Arrays.fill(requiredPeople, 4);
		super.setRequiredPeople(requiredPeople);
	}

}
