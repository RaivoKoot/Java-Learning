package logic;

import java.util.ArrayList;

import logic.tickets.Ticket;

public class Testklasse {

	public static void main(String[] args) {
		DateInfo.isWeekend = false;
		DateInfo.isWeekDay = false;
		DateInfo.isVacation = false;

		PersonList people = new PersonList();
		Person person1 = new Person(200);
		Person person2 = new Person(200);
		Person person3 = new Person(16);
		Person person4 = new Person(16);
		Person person5 = new Person(16);
		
		people.addPerson(person1);
		people.addPerson(person2);
		people.addPerson(person3);
		people.addPerson(person4);
		people.addPerson(person5);

		ArrayList<TicketList> ticketCombos = TicketCombinationMaker.getAllPossibleTicketCombinations(5, 1);

		for (TicketList temp : ticketCombos) {
			System.out.println("\n");
			System.out.println("\n"+TicketListValidator.isTicketListValid(people, temp));
			printTickets(temp.getTickets());
		}
	}

	public static void printTickets(ArrayList<Ticket> tickets) {
		for (Ticket temp : tickets) {
			System.out.println(temp.toString());
		}
	}
}
