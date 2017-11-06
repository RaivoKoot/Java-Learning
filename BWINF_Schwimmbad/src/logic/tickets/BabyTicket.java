package logic.tickets;

public class BabyTicket extends Ticket {

	public BabyTicket() {
		super();
		super.setPrice(0.00);
		super.setName("Free Baby Ticket");

		super.setRequiredPeople(new int[] { 1 });
	}

}
