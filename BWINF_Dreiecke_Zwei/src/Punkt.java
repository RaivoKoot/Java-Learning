import java.math.BigDecimal;

public class Punkt {

	BigDecimal x;
	BigDecimal y;

	public Punkt(BigDecimal x, BigDecimal y) {
		this.x = x;
		this.y = y;
	}

	public BigDecimal getX() {
		return x;
	}

	public BigDecimal getY() {
		return y;
	}

	public String toString() {
		String string = "(" + x + "|" + y + ")";
		return string;
	}
}
