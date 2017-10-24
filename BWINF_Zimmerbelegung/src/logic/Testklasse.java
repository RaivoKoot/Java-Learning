package logic;

import data.DataStorage;
import data.PullFromInputFile;

public class Testklasse {

	public static void main(String[] args) {

		PullFromInputFile.collectData();
		// DataStorage.printStudents();

		Room room1 = new Room();
		room1.addStudent(DataStorage.getStudent("Lea"), true);
		room1.addStudent(DataStorage.getStudent("Hannah"), true);
		room1.addStudent(DataStorage.getStudent("Annika"), true);

		room1.printHates();

	}
}
