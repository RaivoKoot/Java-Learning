package logic.tickets;

/*
 * ticket for 2.50 for kids from the age of 4 to 16
 */
public class TeenTicket extends Ticket {

	public TeenTicket(){
		super();
		super.setPrice(2.50);
		super.setName("Teen Ticket");
	}
}
