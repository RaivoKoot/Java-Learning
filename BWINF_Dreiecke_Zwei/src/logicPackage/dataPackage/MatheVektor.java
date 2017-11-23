package logicPackage.dataPackage;

public class MatheVektor {

	private Fraction x;
	private Fraction y;

	public MatheVektor(Fraction x1, Fraction y1, Fraction x2, Fraction y2) {
		this.setX(x2.subtractBy(x1));
		this.setY(y2.subtractBy(y1));
	}

	public Fraction getX() {
		return x;
	}

	public void setX(Fraction x) {
		this.x = x;
	}

	public Fraction getY() {
		return y;
	}

	public void setY(Fraction y) {
		this.y = y;
	}

	public String toString() {
		String string = "(" + x + "_" + y+")";
		return string;
	}
}
