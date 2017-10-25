package logic;

import java.util.ArrayList;

import data.DataStorage;
import data.PullFromInputFile;

public class Testklasse {

	public static void main(String[] args) {

		PullFromInputFile.collectData();
		// DataStorage.printStudents();

		ArrayList<Student> students = DataStorage.getStudents();

		ArrayList<Room> roomConstellation = RoomCreator.createRoomConstellation(students);

		System.out.println();
		ListPrinter.printRoomList(roomConstellation);
		
	

	}
}
