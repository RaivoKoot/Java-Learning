import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathematischeRechnungen {

	public static BigDecimal berechneSteigung(Punkt p1, Punkt p2) {
		BigDecimal deltaY = p1.getY().subtract(p2.getY());
		BigDecimal deltaX = p1.getX().subtract(p2.getX());

		if (deltaX.compareTo(new BigDecimal("0")) == 0) {
			return new BigDecimal("0");
		} else if (deltaY.compareTo(new BigDecimal("0")) == 0) {
			return new BigDecimal("999999");
		}
		BigDecimal steigung = deltaY.divide(deltaX, 30, RoundingMode.HALF_DOWN);

		return steigung;
	}

	public static BigDecimal bringeAufEineSeite(BigDecimal linkerWert, BigDecimal rechterWert) {
		BigDecimal aufEineSeiteGebracht = linkerWert.add(rechterWert.multiply(new BigDecimal("-1")));

		return aufEineSeiteGebracht;
	}

	/*
	 * Finde Schnittpunk zwischen zwei geraden
	 */
	public static Schnittpunkt berechneGeradenSchnittpunk(Gerade g1, Gerade g2) {

		// steigung gleich -> kein Schnittpunkt
		if (g1.getSteigung().compareTo(g2.getSteigung()) == 0 || (g1.isHorizontalStrecke() && g2.isHorizontalStrecke())
				|| (g2.isVertikalStrecke() && g1.isVertikalStrecke())) {

			System.out.println("***************");
			System.out.println("Kein Schnittpunkt zwischen");
			System.out.println(g1.toString());
			System.out.println(g2.toString());
			System.out.println("***************");
			return null;
		}

		BigDecimal x;

		/*
		 * beide Geraden normale Funktionen, dann geraden gleich stellen und Schnittpunk
		 * finden
		 */

		Gerade einsetzungGerade = g2;

		if (g1.isAFunction() && g2.isAFunction()) {
			Gleichung geradenGleichGestellt = new Gleichung(g1.getYSchnittpunkt(), g2.getYSchnittpunkt(),
					g1.getSteigung(), g2.getSteigung());
			x = loeseGleichung(geradenGleichGestellt);

			/* einer oder beide der Geraden keine Funktion dann spezielle massnahme */
		} else {
			BigDecimal knownVariable = null;

			if (g1.isHorizontalStrecke()) {
				knownVariable = g1.getPunkt_Eins().getY();
				System.out.println(knownVariable.toString());
			} else if (g1.isVertikalStrecke()) {
				knownVariable = g1.getPunkt_Eins().getX();
				System.out.println(knownVariable.toString());
			} else if (g2.isHorizontalStrecke()) {

				knownVariable = g2.getPunkt_Eins().getY();
				einsetzungGerade = g1;
			} else {
				knownVariable = g2.getPunkt_Eins().getX();
				einsetzungGerade = g1;
			}

			x = knownVariable;
		}
		BigDecimal y;
		if (g1.isHorizontalStrecke() || g2.isHorizontalStrecke()) {
			y = getYOfX(x, einsetzungGerade, false);
			return new Schnittpunkt(y.setScale(5, BigDecimal.ROUND_DOWN), x.setScale(5, BigDecimal.ROUND_DOWN), g1, g2);
		} else
			y = getYOfX(x, einsetzungGerade, true);

		return new Schnittpunkt(x.setScale(5, BigDecimal.ROUND_DOWN), y.setScale(5, BigDecimal.ROUND_DOWN), g1, g2);
	}

	public static BigDecimal getYOfX(BigDecimal x, Gerade g1, boolean findingX) {
		BigDecimal y;

		if (g1.isAFunction()) {
			if (findingX) {
				y = x.multiply(g1.getSteigung());
				y = y.add(g1.getYSchnittpunkt());
			} else {
				y = x.subtract(g1.getYSchnittpunkt());
				y = y.divide(g1.getSteigung());
			}
		} else if (g1.isHorizontalStrecke())
			y = g1.getPunkt_Eins().getX();
		else
			y = g1.getPunkt_Eins().getY();

		return y;

	}

	public static Punkt makePunkt(BigDecimal x, BigDecimal y) {
		Punkt neuerPunkt = new Punkt(x, y);

		return neuerPunkt;
	}

	public static BigDecimal loeseGleichung(Gleichung gleichung) {

		BigDecimal gleichungLinkeNummer = gleichung.getGleichungLinkeNummer();
		BigDecimal gleichungRechteNummer = gleichung.getGleichungRechteNummer();

		BigDecimal gleichungLinkesX = gleichung.getGleichungLinkesX();
		BigDecimal gleichungRechtesX = gleichung.getGleichungRechtesX();

		// bringt beide nummern auf die linke seite der gleichung
		BigDecimal summandB = bringeAufEineSeite(gleichungLinkeNummer, gleichungRechteNummer);
		// bringt beide x auf die rechte seite der gleichung
		BigDecimal summandX = bringeAufEineSeite(gleichungRechtesX, gleichungLinkesX);

		BigDecimal x = summandB;
		if (summandX.compareTo(new BigDecimal("0")) != 0)
			x = summandB.divide(summandX, 2, RoundingMode.HALF_UP);

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
	public static boolean liegtPunktAufStrecke(BigDecimal t) {
		// wenn t zwischen 0 und 1 ist
		if (t.compareTo(new BigDecimal("0")) >= 0 && t.compareTo(new BigDecimal("1")) <= 0)
			return true;

		return false;
	}

	public static BigDecimal punktProbe(GeradenGleichung g0, Punkt p) {
		Gleichung gleichung1 = new Gleichung(p.getX(), g0.getOrtsvektor().getX(), new BigDecimal("0"),
				g0.getRichtungsVektor().getX());
		Gleichung gleichung2 = new Gleichung(p.getY(), g0.getOrtsvektor().getY(), new BigDecimal("0"),
				g0.getRichtungsVektor().getY());

		BigDecimal t1 = loeseGleichung(gleichung1);
		BigDecimal t2 = loeseGleichung(gleichung2);

		System.out.println("t1: " + t1);
		System.out.println("t2: " + t2);

		if (t1.compareTo(t2) == 0)
			return t1;

		return new BigDecimal("-10");
	}

	public static Punkt tEinsetzenInVektorGleichung(BigDecimal t, GeradenGleichung g) {
		BigDecimal x = g.getOrtsvektor().getX().add(t.multiply(g.getRichtungsVektor().getX()));
		BigDecimal y = g.getOrtsvektor().getY().add(t.multiply(g.getRichtungsVektor().getY()));
		Punkt punkt = new Punkt(x, y);

		return punkt;
	}

}
