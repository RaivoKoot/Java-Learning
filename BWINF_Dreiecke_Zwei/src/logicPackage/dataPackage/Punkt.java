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

		double xNummer;
		double yNummer;

		if (x.getDenominator() == 0)
			xNummer = 0;
		else
			xNummer = (double)x.getNumerator() / (double)x.getDenominator();
		if (y.getDenominator() == 0)
			yNummer = 0;
		else
			yNummer = (double)y.getNumerator() / (double)y.getDenominator();

		String yString = String.format("%1$,.2f", yNummer);
		String xString =  String.format("%1$,.2f", xNummer);
		
		String string = "(" + xString + "|" + yString + ")";
		
		string += "\t(" + x + "|" + y + ")";

		return string;
	}

	public boolean equals(Punkt p) {
		if (x.equals(p.getX()) && y.equals(p.getY()))
			return true;
		
		return false;
	}
}
