package logic;

import java.util.ArrayList;
import logic.tickets.Ticket;

public class TicketList {
	private ArrayList<Ticket> tickets;
	private boolean hasCoupon;
	private DateInfo dateInfo = null;

	public TicketList() {
		tickets = new ArrayList<Ticket>();
		setHasCoupon(false);
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	public double getPrice() {
		double price = 0;

		for (Ticket temp : tickets)
			price += temp.getActivePrice();

		if (hasCoupon && !dateInfo.isVacation())
			price = price * 0.9;

		return price;
	}

	public void removeTicket(Ticket ticket) {
		tickets.remove(ticket);
	}

	public boolean isHasCoupon() {
		return hasCoupon;
	}

	public void setHasCoupon(boolean hasCoupon) {
		this.hasCoupon = hasCoupon;
	}

	public DateInfo getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(DateInfo dateInfo) {
		this.dateInfo = dateInfo;
	}
}
