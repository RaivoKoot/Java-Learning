package logicPackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import logicPackage.dataPackage.Fraction;
import logicPackage.dataPackage.Punkt;

public class TextFileExtractor {

	public static Gerade[] extractLines(String fileURL) {

		Gerade[] geraden = null;

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileURL);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int lineCounter = -1;

			while ((line = bufferedReader.readLine()) != null) {
				if (lineCounter == -1) {
					geraden = new Gerade[Integer.parseInt(line)];
				} else {
					Gerade gerade = dissectLine(line);
					geraden[lineCounter] = gerade;
				}

				lineCounter++;
			}

			if (geraden[geraden.length - 1] == null) {
				System.out.println("Error reading lines from text file: not as many lines recorded as existent!");
				bufferedReader.close();
				return null;
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileURL + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileURL + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		return geraden;

	}

	public static Gerade dissectLine(String line) {
		String xyCoordinates[] = line.split(" "); // turn String into List\
		
		Fraction x1 = new Fraction((int)Double.parseDouble(xyCoordinates[0]));
		Fraction y1 = new Fraction((int)Double.parseDouble(xyCoordinates[1]));
		Fraction x2 = new Fraction((int)Double.parseDouble(xyCoordinates[2]));
		Fraction y2 = new Fraction((int)Double.parseDouble(xyCoordinates[3]));

		Punkt p1 = new Punkt(x1, y1);
		Punkt p2 = new Punkt(x2, y2);
		
		System.out.println(p1.toString()+p2.toString());

		Gerade gerade = new Gerade(p1, p2);
		return gerade;

	}

}
