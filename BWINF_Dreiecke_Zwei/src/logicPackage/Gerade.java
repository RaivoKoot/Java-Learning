package logicPackage;

import logicPackage.dataPackage.Fraction;
import logicPackage.dataPackage.MatheVektor;
import logicPackage.dataPackage.Punkt;

public class Gerade {

	private Punkt punkt_eins;
	private Punkt punkt_zwei;

	private GeradenGleichung geradenGleichungVektorForm;

	private Fraction ySchnittpunkt;
	private Fraction steigung;
	private boolean vertikalStrecke;

	public boolean isAFunction() {
		if (vertikalStrecke == false)
			return true;
		return false;
	}

	public Gerade(Fraction steigung, Fraction b) {
		this.steigung = steigung;
		this.ySchnittpunkt = b;
		setVertikalStrecke(false);
	}

	public Gerade(Punkt p1, Punkt p2) {
		punkt_eins = p1;
		punkt_zwei = p2;
		setVertikalStrecke(false);

		steigung = MathematischeRechnungen.berechneSteigung(p1, p2);

		if (steigung.compareTo(new Fraction(999)) == 0)
			setVertikalStrecke(true);
			

		ySchnittpunkt = berechneYSchnittpunk(p1);

		/*
		 * Geradengleichung in vektorieller parameterform herleiten
		 */
		MatheVektor ortsVektor = new MatheVektor(new Fraction(0), new Fraction(0), p1.getX(), p1.getY());
		MatheVektor richtungsVektor = new MatheVektor(p1.getX(), p1.getY(), p2.getX(), p2.getY());

		geradenGleichungVektorForm = new GeradenGleichung();
		geradenGleichungVektorForm.setOrtsvektor(ortsVektor);
		geradenGleichungVektorForm.setRichtungsVektor(richtungsVektor);
	}

	public Fraction berechneYSchnittpunk(Punkt p1) {
		Fraction gleichungRechteSeite = steigung.multiply(p1.getX());
		Fraction gleichungLinkeSeite = p1.getY();

		Fraction ySchnittpunkt;

		ySchnittpunkt = MathematischeRechnungen.bringeAufEineSeite(gleichungLinkeSeite, gleichungRechteSeite);

		return ySchnittpunkt;
	}

	public String toString() {
		String geradenGleichung = "f(x)= ";
		geradenGleichung += steigung + "x + " + ySchnittpunkt;
		geradenGleichung += "\n" + punkt_eins.toString() + " " + punkt_zwei.toString();
		return geradenGleichung;
	}

	public Fraction getSteigung() {
		return steigung;
	}

	public Fraction getYSchnittpunkt() {
		return ySchnittpunkt;
	}

	public boolean liegtPunktAufStrecke(Punkt p1) {

		Fraction t = MathematischeRechnungen.punktProbe(geradenGleichungVektorForm, p1);
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


	public Punkt getPunkt_Eins() {
		return punkt_eins;
	}

	public Punkt getPunkt_Zwei() {
		return punkt_zwei;
	}

}
