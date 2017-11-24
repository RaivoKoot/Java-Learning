package logicPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import logicPackage.dataPackage.Dreieck;
import logicPackage.dataPackage.Schnittpunkt;

public class ExportTriangles {

	/*
	 * taken from
	 * https://stackoverflow.com/questions/15754523/how-to-write-text-file-java and
	 * edited to my needs
	 */

	private static String path = "output.txt";
	private static File outputFile;

	public static String createOutputString(ArrayList<Dreieck> dreiecke) {
		int triangleCounter = 1;

		String triangleString = "Number of Triangles: " + dreiecke.size()+"\n\n";

		for (Dreieck temp : dreiecke) {
			Schnittpunkt[] eckPunkte = temp.getEckPunkte();
			String triangleInfo = "\n*********************************************\n";
			triangleInfo += "\t\tDreieck " + triangleCounter;

			triangleInfo += "\np1: " + eckPunkte[0].toString();
			triangleInfo += "\np2: " + eckPunkte[1].toString();
			triangleInfo += "\np3: " + eckPunkte[2].toString();

			triangleInfo += "\n*********************************************\n";

			triangleString += triangleInfo;

			triangleCounter++;
		}

		return triangleString;

	}

	public static void writeToFile(String outputString) {
		BufferedWriter writer = null;

		try {
			// create a temporary file
			outputFile = new File(path);

			writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write(outputString);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}

	}

}
