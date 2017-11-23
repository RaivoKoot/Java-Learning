package logicPackage.dataPackage;

public class Punkt {

	private Fraction x;
	private Fraction y;

	public Punkt(Fraction x, Fraction y) {
		this.x = x;
		this.y = y;
	}

	public Fraction getX() {
		return x;
	}

	public Fraction getY() {
		return y;
	}

	public String toString() {
		String string = "(" + x + "|" + y + ")";

		double xNummer;
		double yNummer;

		if (x.getDenominator() == 0)
			xNummer = 0;
		else
			xNummer = x.getNumerator() / x.getDenominator();
		if (y.getDenominator() == 0)
			yNummer = 0;
		else
			yNummer = y.getNumerator() / y.getDenominator();

		String string2 = "     (" + xNummer + "|" + yNummer + ")";

		string += string2;

		return string;
	}
}
