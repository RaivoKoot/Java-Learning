package logic.tickets;
/*
 * ticket for 3.50 for people 17+
 */
public class AdultTicket extends Ticket{

	public AdultTicket(){
		super();
		super.setPrice(3.50);
		super.setName("17+ Ticket");
	}
	
}
