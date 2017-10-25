package logic;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {

	private String name;

	// students that the person wants to be in a room with
	private ArrayList<Student> loveList;
	// students that the person does not want to be in a room with
	private ArrayList<Student> hateList;

	// room that the student shall reside in
	private Room studentsRoom = null;

	public Student() {
		loveList = new ArrayList<Student>();
		hateList = new ArrayList<Student>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// add a student to this student's hatelist
	public void addHate(Student hassPerson) {
		hateList.add(hassPerson);
	}

	// add a student to this student's loveList
	public void addLove(Student liebesPerson) {
		loveList.add(liebesPerson);
	}

	// checks if this student has a given name
	public boolean hasName(String name) {
		if (this.name == name)
			return true;
		return false;
	}

	public void setRoom(Room zimmer) {
		studentsRoom = zimmer;
	}

	public Room getRoom() {
		return studentsRoom;
	}

	public void setLoves(ArrayList<Student> students) {
		loveList = students;
	}

	public void setHates(ArrayList<Student> students) {
		hateList = students;
	}

	public ArrayList<Student> getHateList() {
		return hateList;
	}

	public ArrayList<Student> getLoveList() {
		return loveList;
	}

	@Override
	public int compareTo(Student otherStudent) {
		String otherName = otherStudent.getName();

		return name.compareTo(otherName);
	}

	public boolean hates(Student otherStudent) {
		for (Student temp : hateList)
			if (otherStudent == temp)
				return true;
		return false;
	}

}
