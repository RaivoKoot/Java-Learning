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
			System.out.println("index " + i + " " + array[i]);
	}
}
