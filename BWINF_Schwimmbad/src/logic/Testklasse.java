package logic;

import java.util.ArrayList;

import logic.DataEncapsulation.DateInfo;
import logic.DataEncapsulation.ExportTickets;
import logic.DataEncapsulation.Person;
import logic.DataEncapsulation.PersonList;
import logic.DataEncapsulation.TicketList;
import logic.tickets.Ticket;

public class Testklasse {

	public static void main(String[] args) {
		DateInfo.isWeekend = true;
		DateInfo.isWeekDay = false;
		DateInfo.isVacation = true;

		PersonList people = new PersonList();
		
		int adults = 1;
		int adolescents = 4;

		for (int i = 0; i < adults; i++) {
			people.addPerson(new Person(18));
		}
		
		for (int i = 0; i < adolescents; i++) {
			people.addPerson(new Person(15));
		}

		int groupSize = people.getGroupSize();
		int coupons = 1;

		ArrayList<TicketList> ticketCombos = TicketCombinationMaker.getAllPossibleTicketCombinations(groupSize,
				coupons);

		double cheapestPrice = 99999;
		TicketList cheapestList = null;

		for (TicketList temp : ticketCombos) {
			// System.out.println("\n" + TicketListValidator.isTicketListValid(people,
			// temp));
			if (TicketListValidator.isTicketListValid(people, temp)) {
				System.out.println("\n");
				printTickets(temp.getTickets());
				System.out.println("Price: " + temp.getPrice(DateInfo.isVacation));
				if (temp.getPrice(DateInfo.isVacation) < cheapestPrice) {
					cheapestPrice = temp.getPrice(DateInfo.isVacation);
					cheapestList = temp;
				}
			}
		}
		
		
		String outputString = ExportTickets.createOutputString(cheapestList, cheapestPrice);
		
		//System.out.println(outputString);
		
		ExportTickets.writeToFile(outputString);
		
	}

	public static void printTickets(ArrayList<Ticket> tickets) {
		for (Ticket temp : tickets) {
			System.out.println(temp.toString());
		}
	}
}
