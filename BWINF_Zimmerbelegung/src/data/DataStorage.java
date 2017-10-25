package data;

import java.util.ArrayList;

import logic.Room;
import logic.Student;

public class DataStorage {

	private static ArrayList<Room> roomList = new ArrayList<Room>();
	private static ArrayList<Student> fullListOfStudents = new ArrayList<Student>();

	// adds a student to the full list of students
	public static void addSchuelerin(Student schuelerin) {
		fullListOfStudents.add(schuelerin);
	}

	// checks if a student with the given name already exists
	// if so: return the existent student object
	// otherwise: create a new student object with the given name and return this
	public static Student getStudent(String name) {
		for (Student temp : fullListOfStudents)
			if (temp.getName().equals(name))
				return temp;

		Student newStudent = new Student();
		newStudent.setName(name);
		fullListOfStudents.add(newStudent);

		return newStudent;
	}

	// debugging purpose
	// prints the full list of students to the console in the same
	// way they are presented in the input file
	public static void printStudents() {
		for (Student temp : fullListOfStudents) {

			System.out.print("\n\n" + temp.getName());
			ArrayList<Student> loveList = temp.getLoveList();

			// print loves
			System.out.print("\n+ ");
			for (Student temp2 : loveList) {
				System.out.print(temp2.getName() + " ");
			}

			ArrayList<Student> hateList = temp.getHateList();
			// print hates
			System.out.print("\n- ");
			for (Student temp3 : hateList) {
				System.out.print(temp3.getName() + " ");
			}
		}

	}

	// debugging purpose
	// prints the names of all registered students
	public static void printStudentNames() {
		fullListOfStudents.sort(null);
		for (Student temp : fullListOfStudents)
			System.out.println(temp.getName());
	}

	public static ArrayList<Student> getStudents() {
		return fullListOfStudents;
	}

	public static ArrayList<Room> getRoomList() {
		return roomList;
	}

	public static void setRoomList(ArrayList<Room> roomList) {
		DataStorage.roomList = roomList;
	}
}