package logic;

import java.util.ArrayList;

import logic.tickets.Ticket;

/*
 * checks if a group fulfills all requirements to buy a list of tickets
 */
public class TicketListValidator {

	public static boolean isTicketListValid(PersonList personList, TicketList tickets) {

		PersonList people = personList.clone();

		for (Ticket temp : tickets.getTickets()) {

			int[] ticketPersonRequirements = temp.getRequiredPeopleTypes();
			//int[] alternateTicketPersonRequirements = temp.getAlternateRequiredPeopleTypes();

			// int[][] requiredPeopleLists = { ticketPersonRequirements,
			// alternateTicketPersonRequirements };

			int amountOfPeopleNeeded = ticketPersonRequirements.length;

			PersonList tempPeople = people.clone();
			
			for (int i = 0; i < amountOfPeopleNeeded; i++) {

				int typeOfPerson = ticketPersonRequirements[i];
				Person personForTicket = null;

				if (typeOfPerson == 2)
					personForTicket = tempPeople.getType2();
				else if (typeOfPerson == 3)
					personForTicket = tempPeople.getType3();
				else if (typeOfPerson == 4)
					personForTicket = tempPeople.getAPerson();

				if (null == personForTicket)
					return false;

				tempPeople.removePerson(personForTicket);
			}

			people = tempPeople;

		}

		return true;
	}

}
