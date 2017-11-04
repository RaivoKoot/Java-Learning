package logic;

import java.util.ArrayList;

import logic.tickets.Ticket;

public class Testklasse {

	public static void main(String[] args) {

	}

	/*
	 * generates all the combinations with the numbers 1, 4, 5 and 6 of which the
	 * sum equals groupSize
	 */


	public static void printTickets(ArrayList<Ticket> tickets) {
		for (Ticket temp : tickets) {
			System.out.println(temp.toString());
		}
	}
}
