import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathematischeRechnungen {

	public static BigDecimal berechneSteigung(Punkt p1, Punkt p2) {
		BigDecimal deltaY = p1.getY().subtract(p2.getY());
		BigDecimal deltaX = p1.getX().subtract(p2.getX());

		BigDecimal steigung = deltaY.divide(deltaX, 2, RoundingMode.HALF_UP);

		return steigung;
	}

	public static BigDecimal bringeAufEineSeite(BigDecimal linkerWert, BigDecimal rechterWert) {
		BigDecimal aufEineSeiteGebracht = linkerWert.add(rechterWert.multiply(new BigDecimal("-1")));

		return aufEineSeiteGebracht;
	}

	public static Schnittpunkt berechneGeradenSchnittpunk(Gerade g1, Gerade g2) {

		// steigung gleich -> kein Schnittpunkt
		if (g1.getSteigung().compareTo(g2.getSteigung()) == 0)
			return null;

		Gleichung geradenGleichGestellt = new Gleichung(g1.getYSchnittpunkt(), g2.getYSchnittpunkt(), g1.getSteigung(),
				g2.getSteigung());
		BigDecimal x = loeseNachXAuf(geradenGleichGestellt);
		BigDecimal y = getYOfX(x, g1);

		return new Schnittpunkt(x, y, g1, g2);
	}

	public static BigDecimal getYOfX(BigDecimal x, Gerade g1) {
		BigDecimal y = x.multiply(g1.getSteigung());
		y = y.add(g1.getYSchnittpunkt());

		return y;

	}

	public static Punkt makePunkt(BigDecimal x, BigDecimal y) {
		Punkt neuerPunkt = new Punkt(x, y);

		return neuerPunkt;
	}

	public static BigDecimal loeseNachXAuf(Gleichung gleichung) {

		BigDecimal gleichungLinkeNummer = gleichung.getGleichungLinkeNummer();
		BigDecimal gleichungRechteNummer = gleichung.getGleichungRechteNummer();

		BigDecimal gleichungLinkesX = gleichung.getGleichungLinkesX();
		BigDecimal gleichungRechtesX = gleichung.getGleichungRechtesX();

		BigDecimal summandB = bringeAufEineSeite(gleichungLinkeNummer, gleichungRechteNummer);
		BigDecimal summandX = bringeAufEineSeite(gleichungRechtesX, gleichungLinkesX);

		BigDecimal x = summandB.divide(summandX, 2, RoundingMode.HALF_UP);

		return x;
	}

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

		BigDecimal t1 = loeseNachXAuf(gleichung1);
		BigDecimal t2 = loeseNachXAuf(gleichung2);

		if (t1.compareTo(t2) == 0)
			return t1;

		return new BigDecimal("-1");
	}

}
