package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import logic.ListPrinter;
import logic.Student;

public class PullFromInputFile {

	private static String fileName = "zimmerbelegung6.txt";

	private static String line;

	public static void collectData() {
		try {

			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				line.replaceAll(" ", "");

				// Processing line 1
				Student parentStudent = DataStorage.getStudent(line);

				// Processing line 2
				line = bufferedReader.readLine();

				parentStudent.setLoves(extractNamesFromString(line));

				// Processing line 3
				line = bufferedReader.readLine();

				parentStudent.setHates(extractNamesFromString(line));

				// skip empty line
				bufferedReader.readLine();

			}

			bufferedReader.close();
			fileReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Student> extractNamesFromString(String line) {
		line = line.substring(2); // remove the + or -

		String loveArray[] = line.split(" "); // turn String into List
		ListPrinter.printArray(loveArray);
		ArrayList<Student> loveList = new ArrayList<Student>();

		// turn String array into List of Student Objects
		for (int studentCount = 0; studentCount < loveArray.length; studentCount++) {

			Student loveSchuelerin = DataStorage.getStudent(loveArray[studentCount]);
			loveList.add(loveSchuelerin);
		}

		return loveList;
	}

}
