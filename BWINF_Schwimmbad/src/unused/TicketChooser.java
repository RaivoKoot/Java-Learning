package unused;

import java.util.ArrayList;

import logic.Person;
import logic.TicketList;
import logic.tickets.AdultTicket;
import logic.tickets.BabyTicket;
import logic.tickets.TeenTicket;
import logic.tickets.Ticket;

public class TicketChooser {

	public static ArrayList<Ticket> chooseTickets(Person[] people, int coupons, boolean weekday, boolean vacation) {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();

		for (Person temp : people) {
			Ticket newTicket = giveStandardTicket(temp);
			if (weekday)
				newTicket.activateWeekdayPrice();
			ticketList.add(newTicket);
		}

		return ticketList;
	}

	public static TicketList giveSingleTickets(Person[] people, boolean weekday) {
		TicketList ticketList = new TicketList();

		for (Person temp : people) {
			Ticket newTicket = giveStandardTicket(temp);
			
			if (weekday)
				newTicket.activateWeekdayPrice();
			else
				newTicket.activateWeekendPrice();

			ticketList.addTicket(newTicket);
		}

		return ticketList;
	}

	public static Ticket giveStandardTicket(Person person) {
		// person under 4
		if (4 > person.getAge()) {
			Ticket newTicket = new BabyTicket();
			person.setTicket(newTicket);
			return newTicket;
		}

		// person between 4 and 16
		if (16 >= person.getAge()) {
			Ticket newTicket = new TeenTicket();
			person.setTicket(newTicket);
			return newTicket;
		}

		// person is 17+
		Ticket newTicket = new AdultTicket();
		person.setTicket(newTicket);
		return newTicket;

	}
}
