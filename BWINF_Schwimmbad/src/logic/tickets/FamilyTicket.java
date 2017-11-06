package logic.tickets;

public class FamilyTicket extends Ticket {

	public FamilyTicket() {
		super();
		super.setPrice(8.00);
		super.setName("Family Ticket");
		super.setTicketType(2);

		super.setRequiredPeople(new int[] { 3, 3, 2, 2 });
		super.setAlternateRequiredPeopleTypes(new int[] { 3, 2, 2, 2 });
	}
}
