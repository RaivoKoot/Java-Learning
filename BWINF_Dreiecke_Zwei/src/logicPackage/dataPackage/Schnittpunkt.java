package logicPackage.dataPackage;

import logicPackage.Gerade;

public class Schnittpunkt extends Punkt {

	private Gerade mutterGerade_eins;
	private Gerade mutterGerade_zwei;

	public Schnittpunkt(Fraction x, Fraction y, Gerade g1, Gerade g2) {
		super(x, y);
		setMutterGerade_eins(g1);
		setMutterGerade_zwei(g2);
	}

	public Gerade getMutterGerade_eins() {
		return mutterGerade_eins;
	}

	public void setMutterGerade_eins(Gerade mutterGerade_eins) {
		this.mutterGerade_eins = mutterGerade_eins;
	}

	public Gerade getMutterGerade_zwei() {
		return mutterGerade_zwei;
	}

	public void setMutterGerade_zwei(Gerade mutterGerade_zwei) {
		this.mutterGerade_zwei = mutterGerade_zwei;
	}

	public boolean istSchnittPunktAufStrecken() {
		boolean ergebnis1 = mutterGerade_eins.liegtPunktAufStrecke(this);
		boolean ergebnis2 = mutterGerade_zwei.liegtPunktAufStrecke(this);

		if (ergebnis1 == true && ergebnis2 == true)
			return true;
		return false;
	}

}
