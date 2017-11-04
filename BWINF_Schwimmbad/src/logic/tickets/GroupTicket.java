package logic.tickets;

/*
 * pass
 */
public class GroupTicket extends Ticket{

	public GroupTicket(){
		super();
		super.setPrice(11.00);
		super.setName("Group Ticket");
		super.setValidOnWeekend(false);
	}
}
