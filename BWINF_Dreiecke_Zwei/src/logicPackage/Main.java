package logicPackage;

import java.util.ArrayList;

import logicPackage.dataPackage.Dreieck;
import logicPackage.dataPackage.Schnittpunkt;

public class Main {

	static final String FILE_NAME = "dreiecke1.txt";

	public static void main(String[] args) {

		Gerade[] geraden = extractLines(FILE_NAME);

		ArrayList<Schnittpunkt> schnittpunkte = getValidIntersects(geraden);
		System.out.println("Schnittpunkte: " + schnittpunkte.size());

		ArrayList<Dreieck> dreiecke = getSubsetsOfThree(schnittpunkte);
		System.out.println(dreiecke.size());

		ArrayList<Dreieck> validDreiecke = getValidDreiecke(dreiecke);

		System.out.println("\n\n" + validDreiecke.size());

		// ExportTriangles.writeToFile();

		String outputString = ExportTriangles.createOutputString(validDreiecke);
		
		System.out.println(outputString);
		
		ExportTriangles.writeToFile(outputString);
	}

	public static Gerade[] extractLines(String fileName) {
		Gerade[] geraden = TextFileExtractor.extractLines(fileName);
		for (int i = 0; i < geraden.length; i++) {
			System.out.println("\nGerade Number " + (i + 1) + ": " + geraden[i].toString());
			System.out.println(geraden[i].getGeradenGleichungVektorForm().toString());

		}

		return geraden;
	}

	public static ArrayList<Schnittpunkt> getValidIntersects(Gerade[] lines) {
		ArrayList<Schnittpunkt> schnittpunkte = new ArrayList<Schnittpunkt>();

		int counter = 0;
		System.out.println("\n\n\n");
		int listLength = lines.length;
		for (int i = 0; i < lines.length; i++) {
			for (int j = i + 1; j < lines.length; j++) {
				counter++;
				Gerade g1 = lines[i];
				Gerade g2 = lines[j];
				Schnittpunkt sp = MathematischeRechnungen.berechneGeradenSchnittpunk(g1, g2);
				if (sp != null) {
					// System.out.println(sp.getMutterGerade_eins().toString());
					// System.out.println(sp.getMutterGerade_zwei().toString());
					System.out.println("\n\n**************************************");
					if (g1.liegtPunktAufStrecke(sp) == true && g2.liegtPunktAufStrecke(sp) == true)
						schnittpunkte.add(sp);
					else
						System.out.println(sp.toString()
								+ " is not on the line !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
				} else
					System.out.println("null\n\n");
			}
		}

		System.out.println("COUNTER: " + counter);

		for (Schnittpunkt sp : schnittpunkte)
			System.out.println(sp.toString());

		return schnittpunkte;
	}

	public static ArrayList<Dreieck> getSubsetsOfThree(ArrayList<Schnittpunkt> schnittpunkte) {
		int listLength = schnittpunkte.size();
		ArrayList<Dreieck> dreiecke = new ArrayList<Dreieck>();

		for (int i = 0; i < listLength; i++)
			for (int j = i + 1; j < listLength; j++)
				for (int k = j + 1; k < listLength; k++) {
					Dreieck dreieck = new Dreieck();
					dreieck.setPunkte(schnittpunkte.get(i), schnittpunkte.get(j), schnittpunkte.get(k));
					dreiecke.add(dreieck);
				}

		return dreiecke;
	}

	public static ArrayList<Dreieck> getValidDreiecke(ArrayList<Dreieck> dreiecke) {
		ArrayList<Dreieck> validDreiecke = new ArrayList<Dreieck>();

		int threes = 0;

		for (Dreieck temp : dreiecke) {
			Schnittpunkt[] eckPunkte = temp.getEckPunkte();
			ArrayList<Gerade> dreiecksSeiten = new ArrayList<Gerade>();

			for (int i = 0; i < 3; i++) {
				Gerade mutterGeradeEins = eckPunkte[i].getMutterGerade_eins();
				Gerade mutterGeradeZwei = eckPunkte[i].getMutterGerade_zwei();

				if (!dreiecksSeiten.contains(mutterGeradeEins))
					dreiecksSeiten.add(mutterGeradeEins);
				if (!dreiecksSeiten.contains(mutterGeradeZwei))
					dreiecksSeiten.add(mutterGeradeZwei);

			}

			if (dreiecksSeiten.size() == 3) {
				threes++;
				validDreiecke.add(temp);
			}
		}
		return validDreiecke;
	}

}
