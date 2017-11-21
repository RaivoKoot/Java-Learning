import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		/*
		 * Punkt p1 = new Punkt(new BigDecimal("-3"), new BigDecimal("2.34")); Punkt p2
		 * = new Punkt(new BigDecimal("4"), new BigDecimal("-3.12"));
		 * 
		 * Gerade g1 = new Gerade(p1, p2);
		 * 
		 * Punkt p3 = new Punkt(new BigDecimal("-4"), new BigDecimal("-10.4")); Punkt p4
		 * = new Punkt(new BigDecimal("-1"), new BigDecimal("-2.6"));
		 * 
		 * Gerade g2 = new Gerade(p3, p4);
		 * 
		 * System.out.println(g1.toString()); System.out.println(g2.toString());
		 * 
		 * Schnittpunkt schnittpunkt =
		 * MathematischeRechnungen.berechneGeradenSchnittpunk(g1, g2); if (schnittpunkt
		 * == null) System.out.println("Kein Schnittpunk"); else
		 * System.out.println(schnittpunkt.toString());
		 * 
		 * System.out.println(g1.liegtPunktAufStrecke(schnittpunkt));
		 * System.out.println(g2.liegtPunktAufStrecke(schnittpunkt));
		 * 
		 * System.out.println("Ergebnis: " + schnittpunkt.istSchnittPunktAufStrecken());
		 * 
		 * int[] superset = { 1, 2, 3 };
		 * 
		 */
		Gerade[] geraden = TextFileExtractor.extractLines("dreiecke1.txt");
		for (int i = 0; i < geraden.length; i++) {
			System.out.println("\nGerade Number " + (i+1) + ": " + geraden[i].toString());
			System.out.println(geraden[i].getGeradenGleichungVektorForm().toString());

		}

		ArrayList<Schnittpunkt> schnittpunkte = new ArrayList<Schnittpunkt>();

		int counter = 0;
		System.out.println("\n\n\n");
		int listLength = geraden.length;
		for (int i = 0; i < 6; i++) {
			for (int j = i+1; j < 6; j++) {
				counter++;
				Gerade g1 = geraden[i];
				Gerade g2 = geraden[j];
				Schnittpunkt sp = MathematischeRechnungen.berechneGeradenSchnittpunk(g1, g2);
				if (sp != null) {
					//System.out.println(sp.getMutterGerade_eins().toString());
					//System.out.println(sp.getMutterGerade_zwei().toString());
					System.out.println("");
					BigDecimal t = MathematischeRechnungen.punktProbe(g1.getGeradenGleichungVektorForm(), sp);
					BigDecimal r = MathematischeRechnungen.punktProbe(g2.getGeradenGleichungVektorForm(), sp);
					boolean punktAufStrecke1 = MathematischeRechnungen.liegtPunktAufStrecke(t);
					boolean punktAufStrecke2 = MathematischeRechnungen.liegtPunktAufStrecke(r);

					if (punktAufStrecke1 == true && punktAufStrecke2 == true)
						schnittpunkte.add(sp);
					else
						System.out.println(sp.toString() +" is not on the line\n\n");
					System.out.println(t);
					System.out.println(r);
				}else
					System.out.println("null\n\n");
			}
		}
		
		System.out.println("COUNTER: "+counter);
		
		for(Schnittpunkt sp: schnittpunkte)
			System.out.println(sp.toString());
	}

	public static ArrayList<Dreieck> getSubsetsSizeThree(ArrayList<Schnittpunkt> schnittpunkte) {
		int listLength = schnittpunkte.size();
		ArrayList<Dreieck> dreiecke = new ArrayList<Dreieck>();

		for (int i = 0; i < listLength; i++)
			for (int j = i; j < listLength; j++)
				for (int k = j; k < listLength; k++) {
					Dreieck dreieck = new Dreieck();
					dreieck.setPunkte(schnittpunkte.get(i), schnittpunkte.get(j), schnittpunkte.get(k));
					dreiecke.add(dreieck);
				}

		return dreiecke;

	}
	
	/*
	 * FEHLER BEI DER SCHNITTPUNKTBERECHNUNG DER HORIZONTALEN GERADE
	 * PROBLEME BEI DER PUNKTPROBE
	 */

}
