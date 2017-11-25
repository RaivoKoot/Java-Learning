 package logic;

import java.util.ArrayList;

import data.DataStorage;
import data.ExportRooms;
import data.PullFromInputFile;

public class Testklasse {

	public static void main(String[] args) {

		PullFromInputFile.collectData("zimmerbelegungTest.txt");
		// DataStorage.printStudents();

		ArrayList<Student> students = DataStorage.getStudents();

		ArrayList<Room> roomConstellation = RoomCreator.createRoomConstellation(students);

		String outputString = ExportRooms.createOutputString(roomConstellation);
		System.out.println(outputString);
		
		ExportRooms.writeToFile(outputString);
	

	}
}
