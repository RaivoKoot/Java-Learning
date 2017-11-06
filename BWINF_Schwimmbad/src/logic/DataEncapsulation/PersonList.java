package logic.DataEncapsulation;

import java.util.ArrayList;

public class PersonList {

	private ArrayList<Person> people;

	public PersonList() {
		people = new ArrayList<Person>();
	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public void removePerson(Person person) {
		people.remove(person);
	}

	public Person getType2() {
		Person person = getPersonByType(2);

		return person;
	}

	public Person getType3() {
		Person person = getPersonByType(3);

		return person;
	}

	private Person getPersonByType(int type) {
		for (Person temp : people) {
			if (temp.getPersonType() == type)
				return temp;
		}

		return null;
	}

	public void setPeoplesList(ArrayList<Person> people) {
		this.people = people;
	}

	public PersonList clone() {
		PersonList clonedPersonList = new PersonList();
		clonedPersonList.setPeoplesList((ArrayList<Person>) people.clone());

		return clonedPersonList;
	}

	public Person getAPerson() {
		Person person = null;
		if (people.size() != 0)
			person = people.get(0);
		return person;
	}

	/*
	 * public void removeAnAdolescent() { for (Person temp : people) if
	 * (temp.getAge() == 17) { people.remove(temp); return; } }
	 */
}
