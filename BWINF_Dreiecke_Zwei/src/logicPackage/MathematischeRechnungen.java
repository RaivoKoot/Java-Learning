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
		if (g1.getSteigung().compareTo(g2.getSteigung()) == 0) {

			//System.out.println("***************");
			//System.out.println("Kein Schnittpunkt zwischen");
			//System.out.println(g1.toString());
			//System.out.println(g2.toString());
		//	System.out.println("***************");
			return null;
		}

		Fraction x;

		/*
		 * beide Geraden normale Funktionen, dann geraden gleich stellen und Schnittpunk
		 * finden
		 */

		Gerade einsetzungGerade = g2;

		if (g1.isAFunction() && g2.isAFunction()) {
			Gleichung geradenGleichGestellt = new Gleichung(g1.getYSchnittpunkt(), g2.getYSchnittpunkt(),
					g1.getSteigung(), g2.getSteigung());
			x = loeseGleichung(geradenGleichGestellt);

			/*
			 * eine der Geraden ist parallel zur y-Achse und keine Funktion. Schnittpunkt
			 * muss also anders berechnet werden
			 */
		} else {
			Fraction knownVariable = null;

			 if (g1.isVertikalStrecke()) {
				knownVariable = g1.getPunkt_Eins().getX();
			} else {
				knownVariable = g2.getPunkt_Eins().getX();
				einsetzungGerade = g1;
			}

			x = knownVariable;
		}
		Fraction y;
		
		/*
		if (g1.isHorizontalStrecke() || g2.isHorizontalStrecke()) {
			y = getYOfX(x, einsetzungGerade, false);
			return new Schnittpunkt(y, x, g1, g2);
		}
		*/

		y = getYOfX(x, einsetzungGerade, true);

		return new Schnittpunkt(x, y, g1, g2);
	}

	public static Fraction getYOfX(Fraction x, Gerade g1, boolean findingY) {
		Fraction y;

		if (g1.isAFunction()) {
			if (findingY) {
				y = x.multiply(g1.getSteigung());
				y = y.add(g1.getYSchnittpunkt());
			} else {
				y = x.subtractBy(g1.getYSchnittpunkt());
				y = y.divideBy(g1.getSteigung());
			}
		} else 
			y = g1.getPunkt_Eins().getX();

		return y;

	}

	/*
	 * public static Fraction getYOfX(Fraction x, Gerade g1, boolean findingX) {
	 * Fraction y;
	 * 
	 * if (g1.isAFunction()) { if (findingX) { y = x.multiply(g1.getSteigung()); y =
	 * y.add(g1.getYSchnittpunkt()); } else { y =
	 * x.subtractBy(g1.getYSchnittpunkt()); y = y.divideBy(g1.getSteigung()); } }
	 * else if (g1.isHorizontalStrecke()) y = g1.getPunkt_Eins().getX(); else y =
	 * g1.getPunkt_Eins().getY();
	 * 
	 * return y;
	 * 
	 * }
	 */

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

	/*
	 * public static BigDecimal loeseGleichungsSystemZweiVariablen(GeradenGleichung
	 * g1, GeradenGleichung g2) { BigDecimal parameterSResultat; BigDecimal
	 * parameterRResultat;
	 * 
	 * // nach parameter 1 loesen Gleichung gleichungI = new
	 * Gleichung(g1.getOrtsvektor().getX(), g1.getRichtungsVektor().getX(),
	 * g2.getOrtsvektor().getX(), g2.getRichtungsVektor().getX());
	 * 
	 * Gleichung gleichungII = new Gleichung(g1.getOrtsvektor().getY(),
	 * g1.getRichtungsVektor().getY(), g2.getOrtsvektor().getY(),
	 * g2.getRichtungsVektor().getY());
	 * 
	 * BigDecimal[] parameterS = loeseGleichungZweiVariablen(gleichungI);
	 * 
	 * // mit parameter 1 parameter 2 loesen
	 * 
	 * return null; }
	 * 
	 * public static BigDecimal[] loeseGleichungZweiVariablen(Gleichung gleichung) {
	 * 
	 * BigDecimal gleichungLinkeNummer = gleichung.getGleichungLinkeNummer();
	 * BigDecimal gleichungRechteNummer = gleichung.getGleichungRechteNummer();
	 * 
	 * BigDecimal gleichungLinksR = gleichung.getGleichungLinkesX(); BigDecimal
	 * gleichungRechtsS = gleichung.getGleichungRechtesX();
	 * 
	 * // bringt beide nummern auf die linke seite der gleichung BigDecimal summandB
	 * = bringeAufEineSeite(gleichungLinkeNummer, gleichungRechteNummer);
	 * 
	 * summandB.divide(gleichungRechtsS, 2, RoundingMode.HALF_UP);
	 * gleichungLinksR.divide(gleichungRechtsS, 2, RoundingMode.HALF_UP);
	 * 
	 * // variable auf der rechten seite s = summandB + gleichungLinksR BigDecimal[]
	 * rIstGleich = { summandB, gleichungLinksR };
	 * 
	 * return rIstGleich; }
	 */
	public static boolean liegtPunktAufStrecke(Fraction t) {
		// wenn t zwischen 0 und 1 ist
		if (t.compareTo(new Fraction(0,0)) >= 0 && t.compareTo(new Fraction(1)) <= 0) {
			return true;
		}

		//System.out.println(t.compareTo(new Fraction(0,0)));
		//System.out.println(t.compareTo(new Fraction(1)));
		//System.out.println(t.toString()+" ist out of Bounds");
		return false;
	}

	public static Fraction punktProbe(GeradenGleichung g0, Punkt p) {
		//System.out.println("********\nSchnittpunkt: " + p.toString());
		Gleichung gleichung1 = new Gleichung(p.getX(), g0.getOrtsvektor().getX(), new Fraction(0),
				g0.getRichtungsVektor().getX());
		Gleichung gleichung2 = new Gleichung(p.getY(), g0.getOrtsvektor().getY(), new Fraction(0),
				g0.getRichtungsVektor().getY());

		Fraction t1 = loeseGleichung(gleichung1);
		Fraction t2 = loeseGleichung(gleichung2);

		//System.out.println("Geradengleichung: " + g0.toString());
		//System.out.println("Gleichung I: " + gleichung1.toString());
		//System.out.println("t1: " + t1);
		//System.out.println("Gleichung II: " + gleichung2.toString());
	//	System.out.println("t2: " + t2);

		if (t1.compareTo(t2) == 0)
			return t1;
		else if (t1.compareTo(new Fraction(0)) == 0)
			return t2;
		else if (t2.compareTo(new Fraction(0)) == 0)
			return t1;

		return new Fraction(-10);
	}

	public static Punkt tEinsetzenInVektorGleichung(Fraction t, GeradenGleichung g) {
		Fraction x = g.getOrtsvektor().getX().add(t.multiply(g.getRichtungsVektor().getX()));
		Fraction y = g.getOrtsvektor().getY().add(t.multiply(g.getRichtungsVektor().getY()));
		Punkt punkt = new Punkt(x, y);

		return punkt;
	}

}
