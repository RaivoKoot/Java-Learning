package logic;

import java.util.ArrayList;

public class PersonList {

	private ArrayList<Person> people;

	public PersonList() {
		people = new ArrayList<Person>();
	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public Person getPersonAtIndex(int index) {
		return people.get(index);
	}

	public int getAgeOfPersonAtIndex(int index) {
		int age = getPersonAtIndex(index).getAge();
		return age;
	}

	public boolean removePerson(int minimumAge) {
		for (Person temp : people)
			if (temp.getAge() >= minimumAge) {
				people.remove(temp);
				return true;
			}
		return false;
	}

	public boolean isAdultLeft() {
		for (Person temp : people)
			if (temp.getAge() >= 18)
				return true;
		return false;
	}

	public boolean isTeenLeft() {
		for (Person temp : people)
			if (temp.getAge() <= 16)
				return true;
		return false;
	}

	public boolean isAdolescentLeft() {
		for (Person temp : people)
			if (temp.getAge() == 17)
				return true;
		return false;
	}

	public void removeAnAdult() {
		for (Person temp : people)
			if (temp.getAge() >= 18) {
				people.remove(temp);
				return;
			}
	}

	public void removeATeen() {
		for (Person temp : people)
			if (temp.getAge() <= 16) {
				people.remove(temp);
				return;
			}
	}

	public void removeAnAdolescent() {
		for (Person temp : people)
			if (temp.getAge() == 17) {
				people.remove(temp);
				return;
			}
	}
}
