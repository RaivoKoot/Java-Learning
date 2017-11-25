package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import logic.Room;
import logic.Student;

public class ExportRooms {

	/*
	 * taken from
	 * https://stackoverflow.com/questions/15754523/how-to-write-text-file-java and
	 * edited to my needs
	 */

	private static String path = "output.txt";
	private static File outputFile;

	public static String createOutputString(ArrayList<Room> rooms) {
		int roomCounter = 1;

		String outputString = "Number of Rooms: " + rooms.size()+"\n\n";

		for (Room temp : rooms) {
			ArrayList<Student> students = temp.getOccupants();
			
			String roomInfo = "\n*********************************************\n";
			roomInfo += "\t\tRoom " + roomCounter;
			roomInfo += "\n"+students.size()+" students\n";

			int nameCounter = 0;
			for (Student temp2 : students) {
				if(nameCounter == 6) {
					//roomInfo+="\n";
					nameCounter=0;
				}
				roomInfo += temp2.getName() + ", ";
				
				nameCounter++;
			}
			roomInfo += "\n*********************************************\n";

			outputString += roomInfo;

			roomCounter++;
		}

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
