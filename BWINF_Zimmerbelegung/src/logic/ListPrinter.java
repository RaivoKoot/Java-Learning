package logic;

import java.util.ArrayList;

public class ListPrinter {

	public static void printList(ArrayList<Student> students) {
		System.out.print("\n- ");
		for (Student temp : students) {
			System.out.print(temp.getName() + " ");
		}
	}

	public static void printArray(String[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i] + ", ");
	}

	public static void printRoomList(ArrayList<Room> rooms) {
		int roomCounter = 1;
		
		for (Room temp : rooms) {
			System.out.print("\nROOM "+roomCounter+"\n");
			
			ArrayList<Student> roomsOccupants = temp.getOccupants();
			
			System.out.print("\n"+roomsOccupants.size()+" students\n");
			
			for (Student temp2 : roomsOccupants)
				System.out.print(temp2.getName() + ", ");
			
			System.out.print("\n\n");
			roomCounter++;
		}

	}
}
