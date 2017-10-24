package logic;

import java.util.ArrayList;
import java.util.List;

public class Room {

	// all students that shall live in this room
	private ArrayList<Student> occupants;

	// accumulation of the occupant's hateLists
	private ArrayList<Student> hateList;

	public ArrayList<Student> getOccupants() {
		return occupants;
	}

	public ArrayList<Student> getHateList() {
		return hateList;
	}

	public Room() {
		occupants = new ArrayList<Student>();
		hateList = new ArrayList<Student>();
	}

	// adds a student to the room's occupants
	public void addStudent(Student student, boolean addHates) {
		occupants.add(student);

		if (addHates)
			addHates(student.getHateList());
	}

	// returns true if the student is on the room's hate list, false otherwise
	public boolean doesRoomHateStudent(Student student) {
		for (Student temp : hateList)
			if (student == temp)
				return true;

		// student is not in room hate list
		return false;
	}

	// add a list of students to the room's hate list
	public void addHates(ArrayList<Student> studentHates) {
		ArrayList<Student> copyList = new ArrayList<Student>(studentHates);

		copyList.removeAll(hateList);
		hateList.addAll(copyList);
	}

	// debugging purpose
	// print the names of the room's hated students to the console
	public void printHates() {
		System.out.print("\n- ");
		for (Student temp : hateList) {
			System.out.print(temp.getName() + " ");
		}
	}

	public boolean isCompatibleWith(Room otherRoom, boolean firstTime) {

		for (Student temp : occupants)
			if (otherRoom.doesRoomHateStudent(temp))
				return false;

		if (firstTime && !otherRoom.isCompatibleWith(this, false))
			return false;

		return true;

	}

}
