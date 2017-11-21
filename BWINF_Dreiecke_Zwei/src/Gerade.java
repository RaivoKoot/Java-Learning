import java.math.BigDecimal;

public class Gerade {

	private Punkt punkt_eins;
	private Punkt punkt_zwei;

	private GeradenGleichung geradenGleichungVektorForm;

	private BigDecimal ySchnittpunkt;
	private BigDecimal steigung;
	private boolean vertikalStrecke;
	private boolean horizontalStrecke;

	public boolean isAFunction() {
		if (vertikalStrecke == false && horizontalStrecke == false)
			return true;
		return false;
	}

	public Gerade(BigDecimal steigung, BigDecimal b) {
		this.steigung = steigung;
		this.ySchnittpunkt = b;
		setVertikalStrecke(false);
		setHorizontalStrecke(false);
	}

	public Gerade(Punkt p1, Punkt p2) {
		punkt_eins = p1;
		punkt_zwei = p2;
		setVertikalStrecke(false);
		setHorizontalStrecke(false);

		steigung = MathematischeRechnungen.berechneSteigung(p1, p2);

		if (steigung.compareTo(new BigDecimal("999999")) == 0)
			setHorizontalStrecke(true);
		else if (steigung.compareTo(new BigDecimal("0")) == 0)
			setVertikalStrecke(true);

		ySchnittpunkt = berechneYSchnittpunk(p1);

		/*
		 * Geradengleichung in vektorieller parameterform herleiten
		 */
		MatheVektor ortsVektor = new MatheVektor(new BigDecimal("0"), new BigDecimal("0"), p1.getX(), p1.getY());
		MatheVektor richtungsVektor = new MatheVektor(p1.getX(), p1.getY(), p2.getX(), p2.getY());

		geradenGleichungVektorForm = new GeradenGleichung();
		geradenGleichungVektorForm.setOrtsvektor(ortsVektor);
		geradenGleichungVektorForm.setRichtungsVektor(richtungsVektor);
	}

	public BigDecimal berechneYSchnittpunk(Punkt p1) {
		BigDecimal gleichungRechteSeite = steigung.multiply(p1.getX());
		BigDecimal gleichungLinkeSeite = p1.getY();

		BigDecimal ySchnittpunkt;

		ySchnittpunkt = MathematischeRechnungen.bringeAufEineSeite(gleichungLinkeSeite, gleichungRechteSeite);

		return ySchnittpunkt;
	}

	public String toString() {
		String geradenGleichung = "f(x)= ";
		geradenGleichung += steigung + "x + " + ySchnittpunkt;
		geradenGleichung += "\n" + punkt_eins.toString() + " " + punkt_zwei.toString();
		return geradenGleichung;
	}

	public BigDecimal getSteigung() {
		return steigung;
	}

	public BigDecimal getYSchnittpunkt() {
		return ySchnittpunkt;
	}

	public boolean liegtPunktAufStrecke(Punkt p1) {

		BigDecimal t = MathematischeRechnungen.punktProbe(geradenGleichungVektorForm, p1);
		boolean ergebnis = MathematischeRechnungen.liegtPunktAufStrecke(t);

		return ergebnis;
	}

	public GeradenGleichung getGeradenGleichungVektorForm() {
		return geradenGleichungVektorForm;
	}

	public void setGeradenGleichungVektorForm(GeradenGleichung geradenGleichungVektorForm) {
		this.geradenGleichungVektorForm = geradenGleichungVektorForm;
	}

	public boolean isVertikalStrecke() {
		return vertikalStrecke;
	}

	public void setVertikalStrecke(boolean vertikalStrecke) {
		this.vertikalStrecke = vertikalStrecke;
	}

	public boolean isHorizontalStrecke() {
		return horizontalStrecke;
	}

	public void setHorizontalStrecke(boolean horizontalStrecke) {
		this.horizontalStrecke = horizontalStrecke;
	}
	
	public Punkt getPunkt_Eins() {
		return punkt_eins;
	}
	
	public Punkt getPunkt_Zwei() {
		return punkt_zwei;
	}

}
