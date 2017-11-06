package logic;

import java.util.ArrayList;

import logic.DataEncapsulation.Person;
import logic.DataEncapsulation.PersonList;
import logic.DataEncapsulation.TicketList;
import logic.tickets.Ticket;

/*
 * checks if a group fulfills all requirements to buy a list of tickets
 */
public class TicketListValidator {

	public static boolean isTicketListValid(PersonList personList, TicketList tickets) {

		PersonList people = personList.clone();

		for (Ticket temp : tickets.getTickets()) {

			int[] ticketPersonRequirements = temp.getRequiredPeopleTypes();
			int[] alternateTicketPersonRequirements = temp.getAlternateRequiredPeopleTypes();

			// int[][] requiredPeopleLists = { ticketPersonRequirements,
			// alternateTicketPersonRequirements };

			int[] currentPersonRequirements = ticketPersonRequirements;
			int amountOfPeopleNeeded = -1;
			boolean firstRequirementNotMet = true;

			PersonList tempPeople = people.clone();
			for (int j = 0; j < 2; j++) {

				if (null == currentPersonRequirements)
					break;

				amountOfPeopleNeeded = currentPersonRequirements.length;
				for (int i = 0; i < amountOfPeopleNeeded; i++) {

					int typeOfPerson = currentPersonRequirements[i];
					Person personForTicket = null;

					if (typeOfPerson == 2)
						personForTicket = tempPeople.getType2();
					else if (typeOfPerson == 3)
						personForTicket = tempPeople.getType3();
					else if (typeOfPerson == 4)
						personForTicket = tempPeople.getAPerson();

					if (null == personForTicket) {
						if (null == alternateTicketPersonRequirements || j == 1)
							return false;

						tempPeople = people.clone();
						break;
					}

					tempPeople.removePerson(personForTicket);
				} 
				people = tempPeople;
				
				currentPersonRequirements = alternateTicketPersonRequirements;

			}
		}

		return true;
	}

}
