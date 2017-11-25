package logic;

import java.util.ArrayList;

public class RoomCreator {

	private static ArrayList<Room> roomList;

	public static ArrayList<Room> createRoomConstellation(ArrayList<Student> students) {
		roomList = new ArrayList<Room>();
		ArrayList<Room> failure = new ArrayList<Room>();

		for (Student currentStudent : students) {

			ArrayList<Student> loveList = currentStudent.getLoveList();
			for (Student currentLove : loveList) {

				if (currentLove.hates(currentStudent)) {
					// System.out.println("currentLove hates student");
					return failure;
				}

				/*
				 * love has no room yet
				 */
				if (null == currentLove.getRoom()) {

					// student does not have room either
					if (null == currentStudent.getRoom()) {
						createNewRoom(currentLove, currentStudent);
						// System.out.println("\n\nstudent " + currentStudent.getName() + " and love "
						// + currentLove.getName() + " do not have a room yet. created new room with
						// both");
					}

					// student has a room already
					else {
						// if student's room does not want love
						if (!currentStudent.getRoom().isCompatibleWithStudent(currentLove)) {
							// System.out.println("\n\nstudents room does not want love");
							return failure;
						}
						// else add love to student's room
						currentStudent.getRoom().addStudent(currentLove, true);
						// System.out.println("\n\nadded love " + currentLove.getName() + " to student "
						// + currentStudent.getName() + "'s room. Her room contains:");
						// for (Student temp3 : currentStudent.getRoom().getOccupants())
						// System.out.print(temp3.getName() + ", ");
					}

				}
				/*
				 * love has a room already
				 */
				else {
					// student has no room
					if (null == currentStudent.getRoom()) {
						// love's room does not want student
						if (!currentLove.getRoom().isCompatibleWithStudent(currentStudent)) {
							// System.out.println("love's room does not want student");
							return failure;
						}
						// else add student to love's room
						currentLove.getRoom().addStudent(currentStudent, true);
					}
					// student has a room too
					else {
						// rooms are not compatible
						if (!studentsRoomsAreCompatible(currentLove, currentStudent)) {
							// ListPrinter.printRoomList(roomList);
							// System.out.println("Student: " + currentStudent.getName());
							// System.out.println("Love: " + currentLove.getName());
							// System.out.println("room of love and student are not compatible");
							return failure;
						}

						else if (!studentsHaveSameRoomAlready(currentLove, currentStudent)) {

							// rooms are compatible
							// merge the two's rooms and remove the old rooms
							// System.out.println("\n\nMERGING ROOMS");
							// System.out.print("ROOM 1 CONTAINS: ");
							// for (Student temp3 : currentStudent.getRoom().getOccupants())
							// System.out.print(temp3.getName() + ", ");
							// System.out.print("\nROOM 2 CONTAINS: ");
							// for (Student temp3 : currentLove.getRoom().getOccupants())
							// System.out.print(temp3.getName() + ", ");
							removeStudentsRoomsFromList(currentLove, currentStudent);
							Room mergedRoom = mergeStudentsRooms(currentLove, currentStudent);
							addRoom(mergedRoom);

							// System.out.println("\nNEW ROOM CONTAINS: ");
							// for (Student temp3 : mergedRoom.getOccupants())
							// System.out.print(temp3.getName() + ", ");
						}
					}
				}

			}
		}

		ArrayList<Room> roomsWithoutLoves = new ArrayList<Room>();
		for (Student temp : students) {
			if (null != temp.getRoom())
				continue;

			boolean noRoomYet = true;
			for (Room tempRoom : roomsWithoutLoves) {
				if (noRoomYet && tempRoom.getOccupants().size() < 6 && tempRoom.isCompatibleWithStudent(temp)) {
					tempRoom.addStudent(temp, true);
					noRoomYet = false;
				}
			}

			if (noRoomYet) {
				Room newRoom = new Room();
				newRoom.addStudent(temp, true);

				roomsWithoutLoves.add(newRoom);
				roomList.add(newRoom);
			}

		}

		return roomList;
	}

	public static boolean studentsHaveSameRoomAlready(Student student1, Student student2) {
		Room room1 = student1.getRoom();
		Room room2 = student2.getRoom();

		if (room1 == room2)
			return true;
		return false;
	}

	public static void removeStudentsRoomsFromList(Student student1, Student student2) {
		Room room1 = student1.getRoom();
		Room room2 = student2.getRoom();

		removeRoomFromList(room1);
		removeRoomFromList(room2);
	}

	public static boolean studentsRoomsAreCompatible(Student student1, Student student2) {
		if (student1.getRoom().isCompatibleWith(student2.getRoom(), true))
			return true;
		return false;
	}

	public static void createNewRoom(Student student1, Student student2) {
		Room newRoom = new Room();
		newRoom.addStudent(student1, true);
		newRoom.addStudent(student2, true);

		addRoom(newRoom);
	}

	public static void removeRoomFromList(Room room) {
		roomList.remove(room);
	}

	public static void addRoom(Room room) {
		roomList.add(room);
	}

	public static Room mergeStudentsRooms(Student student1, Student student2) {
		Room room1 = student1.getRoom();
		Room room2 = student2.getRoom();

		Room mergedRoom = mergeRooms(room1, room2);

		return mergedRoom;
	}

	public static Room mergeRooms(Room room1, Room room2) {

		Room newRoom = new Room();

		Room[] rooms = { room1, room2 };

		// add all students from room1 and room2 to the new room
		for (int i = 0; i < rooms.length; i++) {
			ArrayList<Student> occupants = rooms[i].getOccupants();

			for (Student temp : occupants)
				newRoom.addStudent(temp, false);

		}

		// add the hates from each room to the new room
		newRoom.addHates(room1.getHateList());
		newRoom.addHates(room2.getHateList());

		return newRoom;

	}

}
