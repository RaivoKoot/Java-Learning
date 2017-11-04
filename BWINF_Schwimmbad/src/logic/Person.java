package logic;

import logic.tickets.Ticket;

public class Person {

	private int age;

	private Ticket ticket;

	private boolean hasStandardTicket;

	public Person(int age) {
		this.setAge(age);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public boolean isHasStandardTicket() {
		return hasStandardTicket;
	}

	public void setHasStandardTicket(boolean hasStandardTicket) {
		this.hasStandardTicket = hasStandardTicket;
	}
}
