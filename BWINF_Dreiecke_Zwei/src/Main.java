import java.math.BigDecimal;

public class Main {

	public static void main(String[] args) {
		Punkt p1 = new Punkt(new BigDecimal("-4"), new BigDecimal("0"));
		Punkt p2 = new Punkt(new BigDecimal("5"), new BigDecimal("9"));

		Gerade g1 = new Gerade(p1, p2);
		
		Punkt p3 = new Punkt(new BigDecimal("2"), new BigDecimal("-10"));
		Punkt p4 = new Punkt(new BigDecimal("-4"), new BigDecimal("8"));

		Gerade g2 = new Gerade(p3, p4);
		
		System.out.println(g1.toString());
		System.out.println(g2.toString());

		Punkt schnittpunkt = Rechnungen.berechneGeradenSchnittpunk(g1, g2);
		if (schnittpunkt == null)
			System.out.println("Kein Schnittpunk");
		else
			System.out.println(schnittpunkt.toString());
		
		System.out.println(g1.liegtPunktAufStrecke(schnittpunkt));
		System.out.println(g2.liegtPunktAufStrecke(schnittpunkt));
		
	}

}
