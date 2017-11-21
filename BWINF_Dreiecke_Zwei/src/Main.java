import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Punkt p1 = new Punkt(new BigDecimal("-3"), new BigDecimal("2.34"));
		Punkt p2 = new Punkt(new BigDecimal("4"), new BigDecimal("-3.12"));

		Gerade g1 = new Gerade(p1, p2);

		Punkt p3 = new Punkt(new BigDecimal("-4"), new BigDecimal("-10.4"));
		Punkt p4 = new Punkt(new BigDecimal("-1"), new BigDecimal("-2.6"));

		Gerade g2 = new Gerade(p3, p4);

		System.out.println(g1.toString());
		System.out.println(g2.toString());

		Schnittpunkt schnittpunkt = MathematischeRechnungen.berechneGeradenSchnittpunk(g1, g2);
		if (schnittpunkt == null)
			System.out.println("Kein Schnittpunk");
		else
			System.out.println(schnittpunkt.toString());

		System.out.println(g1.liegtPunktAufStrecke(schnittpunkt));
		System.out.println(g2.liegtPunktAufStrecke(schnittpunkt));

		System.out.println("Ergebnis: " + schnittpunkt.istSchnittPunktAufStrecken());

		int[] superset = { 1, 2, 3 };

	}

	public static ArrayList<Dreieck> printSubsets(ArrayList<Schnittpunkt> schnittpunkte) {
		int listLenght = schnittpunkte.size();
		ArrayList<Dreieck> dreiecke = new ArrayList<Dreieck>();

		for (int i = 0; i < listLenght; i++)
			for (int j = i; j < listLenght; j++)
				for (int k = j; k < listLenght; k++) {
					Dreieck dreieck = new Dreieck();
					dreieck.setPunkte(schnittpunkte.get(i), schnittpunkte.get(j), schnittpunkte.get(k));
					dreiecke.add(dreieck);
				}

		return dreiecke;

	}

}
