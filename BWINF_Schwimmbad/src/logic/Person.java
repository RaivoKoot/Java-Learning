package logic;

public class Person {

	private int age;
	private int personType;

	public Person(int age) {
		this.setAge(age);
		setType(age);
	}

	private void setType(int age) {
		if (age < 4)
			setPersonType(1); // baby
		else if (age < 17)
			setPersonType(2); // teenager
		else
			setPersonType(3); // adult

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPersonType() {
		return personType;
	}

	public void setPersonType(int personType) {
		this.personType = personType;
	}
}
