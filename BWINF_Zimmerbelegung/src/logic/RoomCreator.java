package logic;

import java.util.ArrayList;

public class RoomCreator {

	// unit test this for bugs
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
