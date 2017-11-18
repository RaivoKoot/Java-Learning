import java.math.BigDecimal;

public class MatheVektor {

	private BigDecimal x;
	private BigDecimal y;

	public MatheVektor(BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2) {
		this.setX(x2.subtract(x1));
		this.setY(y2.subtract(y1));
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public String toString() {
		String string = "vektor:\n" + x + "\n" + y;
		return string;
	}
}
