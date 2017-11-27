package logicPackage;

import java.math.BigDecimal;
import java.math.RoundingMode;

import logicPackage.dataPackage.Fraction;
import logicPackage.dataPackage.Gleichung;
import logicPackage.dataPackage.Punkt;
import logicPackage.dataPackage.Schnittpunkt;

public final class MathematischeRechnungen {

	public static Fraction berechneSteigung(Punkt p1, Punkt p2) {
		Fraction deltaY = p1.getY().subtractBy(p2.getY());
		Fraction deltaX = p1.getX().subtractBy(p2.getX());

		if (deltaX.compareTo(new Fraction(0)) == 0)
			return new Fraction(999);

		Fraction steigung = deltaY.divideBy(deltaX);

		return steigung;
	}

	public static Fraction bringeAufEineSeite(Fraction linkerWert, Fraction rechterWert) {

		Fraction aufEineSeiteGebracht = linkerWert.add(rechterWert.multiply(new Fraction(-1)));

		return aufEineSeiteGebracht;
	}

	/*
	 * Finde Schnittpunk zwischen zwei geraden
	 */
	public static Schnittpunkt berechneGeradenSchnittpunk(Gerade g1, Gerade g2) {

		// steigung gleich -> kein Schnittpunkt
		if (g1.getSteigung().compareTo(g2.getSteigung()) == 0)
			return null;

		Fraction xKoordinate;
		Gerade geradeZumXEinsetzen = g2;

		/*
		 * sind beide Geraden Funktionen, dann Geraden gleich stellen und x Koordinate
		 * des Schnittpunkts finden
		 */
		if (g1.isAFunction() && g2.isAFunction()) {
			Gleichung geradenGleichGestellt = new Gleichung(g1.getYSchnittpunkt(), g2.getYSchnittpunkt(),
					g1.getSteigung(), g2.getSteigung());
			xKoordinate = loeseGleichung(geradenGleichGestellt);

			/*
			 * Ansonsten eine der Geraden ist parallel zur y-Achse und keine Funktion.
			 * Schnittpunkt muss also anders berechnet werden
			 */
		} else {

			// wenn g1 parallel zur y-Achse ist
			if (g1.isVertikalStrecke())
				xKoordinate = g1.getPunkt_Eins().getX();

			// ansonsten ist g2 parallel zur y-Achse
			else {
				xKoordinate = g2.getPunkt_Eins().getX();
				geradeZumXEinsetzen = g1;
			}

		}

		Fraction yKoordinate;

		// setze x in die Gleichung ein um y zu bekommen
		yKoordinate = getYOfX(xKoordinate, geradeZumXEinsetzen);

		// erstelle neuen Schnittpunkt dessen Eltern g1 und g2 sind
		return new Schnittpunkt(xKoordinate, yKoordinate, g1, g2);
	}

	public static Fraction getYOfX(Fraction x, Gerade g1) {
		//f(x)
		Fraction y;

		// wenn Gerade g1 eine korrekte Funktion ist
		if (g1.isAFunction()) {
			// y = x*steigung + ySchnittpunkt oder auch b genannt
			y = x.multiply(g1.getSteigung());
			y = y.add(g1.getYSchnittpunkt());

		// ansonsten ist die Gerade parallel zur y-Achse und wir suchen x statt y
		} else
			// bei solch einer Geraden ist x immer gleich also returnen wir x
			y = g1.getPunkt_Eins().getX();

		return y;

	}

	public static Punkt makePunkt(Fraction x, Fraction y) {
		Punkt neuerPunkt = new Punkt(x, y);

		return neuerPunkt;
	}

	public static Fraction loeseGleichung(Gleichung gleichung) {

		Fraction gleichungLinkeNummer = gleichung.getGleichungLinkeNummer();
		Fraction gleichungRechteNummer = gleichung.getGleichungRechteNummer();

		Fraction gleichungLinkesX = gleichung.getGleichungLinkesX();
		Fraction gleichungRechtesX = gleichung.getGleichungRechtesX();

		// bringt beide nummern auf die linke seite der gleichung
		Fraction summandB = bringeAufEineSeite(gleichungLinkeNummer, gleichungRechteNummer);
		// bringt beide x auf die rechte seite der gleichung
		Fraction summandX = bringeAufEineSeite(gleichungRechtesX, gleichungLinkesX);

		Fraction x = summandB;
		if (summandX.compareTo(new Fraction(0)) != 0)
			x = summandB.divideBy(summandX);

		return x;
	}

	public static boolean liegtPunktAufStrecke(Fraction t) {
		// wenn t zwischen 0 und 1 ist
		if (t.compareTo(new Fraction(0, 0)) >= 0 && t.compareTo(new Fraction(1)) <= 0) {
			return true;
		}

		// System.out.println(t.compareTo(new Fraction(0,0)));
		// System.out.println(t.compareTo(new Fraction(1)));
		// System.out.println(t.toString()+" ist out of Bounds");
		return false;
	}

	/*
	 * gibt den Parameter t zuerueck, der in der Gleichung eingesetzt Punkt p ergibt
	 */
	public static Fraction punktProbe(GeradenGleichung g0, Punkt p) {

		// Erstelle das Gleichungssystem aus 2 Gleichungen

		// Gleichung 1 aus der x Zeile
		Gleichung gleichung1 = new Gleichung(p.getX(), g0.getOrtsvektor().getX(), new Fraction(0),
				g0.getRichtungsVektor().getX());

		// Gleichung 2 aus der y Zeile
		Gleichung gleichung2 = new Gleichung(p.getY(), g0.getOrtsvektor().getY(), new Fraction(0),
				g0.getRichtungsVektor().getY());

		// loese nach dem Parameter t auf
		Fraction t1 = loeseGleichung(gleichung1);
		Fraction t2 = loeseGleichung(gleichung2);

		// da wir bereits wissen dass der schnittpunkt auf der geraden liegt und t1 und
		// t2 gleich sein muessen, koennen wir eines der beiden ts ohne weiteres
		// zurueckgeben

		// Es kann jedoch sein, dass einer der beiden Gleichungen kein t mehr enthaelt.
		// Das aeussert sich hier indem t 0 ist (t kann auch wirklich 0 sein). In dem
		// fall das andere t zurueckgeben.
		if (t1.compareTo(new Fraction(0)) == 0)
			return t2;
		else
			return t1;
	}

	public static Punkt tEinsetzenInVektorGleichung(Fraction t, GeradenGleichung g) {
		Fraction x = g.getOrtsvektor().getX().add(t.multiply(g.getRichtungsVektor().getX()));
		Fraction y = g.getOrtsvektor().getY().add(t.multiply(g.getRichtungsVektor().getY()));
		Punkt punkt = new Punkt(x, y);

		return punkt;
	}

}
