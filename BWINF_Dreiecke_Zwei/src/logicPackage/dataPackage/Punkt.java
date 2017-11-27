package logicPackage.dataPackage;

public class Punkt {

	public static void main(String[] args) {
		Fraction x1 = new Fraction(2, 2);
		Fraction x2 = new Fraction(2, 2);

		Fraction y1 = new Fraction(1, 1);
		Fraction y2 = new Fraction(4, 4);

		Punkt p1 = new Punkt(x1, y1);
		Punkt p2 = new Punkt(x2, y2);
		System.out.println(p1.toString());
		System.out.println(p2.toString());

		System.out.println(p1.equals(p2));
	}

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

		double xNummer;
		double yNummer;

		if (x.getDenominator() == 0)
			xNummer = 0;
		else
			xNummer = (double) x.getNumerator() / (double) x.getDenominator();
		if (y.getDenominator() == 0)
			yNummer = 0;
		else
			yNummer = (double) y.getNumerator() / (double) y.getDenominator();

		String yString = String.format("%1$,.2f", yNummer);
		String xString = String.format("%1$,.2f", xNummer);

		String string = "(" + xString + "|" + yString + ")";

		string += "\t(" + x + "|" + y + ")";

		return string;
	}

	public boolean equals(Punkt p) {
		String string1 = toString();
		String string2 = p.toString();

		string1 = string1.substring(0, string1.indexOf('\t'));
		string2 = string2.substring(0, string2.indexOf('\t'));

		if (string1.equals(string2))
			return true;

		return false;
	}
}
