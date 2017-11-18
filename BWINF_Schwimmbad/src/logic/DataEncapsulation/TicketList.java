package logic.DataEncapsulation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import logic.tickets.AdultTicket;
import logic.tickets.FamilyTicket;
import logic.tickets.GroupTicket;
import logic.tickets.TeenTicket;
import logic.tickets.Ticket;

public class TicketList {
	private ArrayList<Ticket> tickets;
	private boolean hasCoupon;

	public TicketList(int[] ticketsAsNumbers, boolean hasCoupon) {
		this.tickets = new ArrayList<Ticket>();
		this.hasCoupon = hasCoupon;

		tickets = createTickets(ticketsAsNumbers, DateInfo.isWeekDay);
	}

	private ArrayList<Ticket> createTickets(int[] ticketsAsNumbers, boolean isWeekday) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		for (int i = 0; i < ticketsAsNumbers.length; i++) {
			Ticket newTicket = null;

			switch (ticketsAsNumbers[i]) {
			case -1:
				newTicket = new TeenTicket(isWeekday);
				break;
			case 1:
				newTicket = new AdultTicket(isWeekday);
				break;
			case -4:
				newTicket = new FamilyTicket();
				break;
			case 4:
			case 5:
			case 6:
				newTicket = new GroupTicket(ticketsAsNumbers[i]);
				break;
			}

			tickets.add(newTicket);
		}

		tickets.sort(null);

		return tickets;
	}

	public double getPrice(boolean isVacation) {
		double price = 0;

		for (Ticket temp : tickets)
			price += temp.getPrice();

		if (hasCoupon && !isVacation)
			price = price * 0.9;

		DecimalFormat twoPlaces = new DecimalFormat("0.00");
		price = Double.parseDouble(twoPlaces.format(price).replaceAll(",", "."));


		return price;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

}
