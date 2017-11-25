package logic.DataEncapsulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import logic.tickets.Ticket;

public class ExportTickets {

	/*
	 * taken from
	 * https://stackoverflow.com/questions/15754523/how-to-write-text-file-java and
	 * edited to my needs
	 */

	private static String path = "output.txt";
	private static File outputFile;

	public static String createOutputString(TicketList tickets, double price) {

		String outputString = "Number of Tickets: " + tickets.getTickets().size() + "\n";
		outputString += tickets.getCouponsUsedToSkipStudent() + " coupons are used to skip a student";

		if (tickets.usesCouponForTenPercent(DateInfo.isVacation))
			outputString += "\nA coupon is used to get 10 Percent off the entire price";

		outputString += "\n";
		for (Ticket temp : tickets.getTickets()) {
			outputString += "\n" + temp.toString();
		}

		outputString += "\nFINAL PRICE: " + price;

		outputString += "\n\nWARNING: If you have a child with you that is 3 years or younger\n";
		outputString += "\nand you do not have at least one person with a mininum age of 17 years\n";
		outputString += "\nwith you, you will not be able to enter";

		return outputString;

	}

	public static void writeToFile(String outputString) {
		BufferedWriter writer = null;

		try {
			// create a temporary file
			outputFile = new File(path);

			writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write(outputString);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}

	}

}
