package logicPackage.dataPackage;

public class Dreieck {

	private Schnittpunkt p1;
	private Schnittpunkt p2;
	private Schnittpunkt p3;
	private Schnittpunkt[] eckPunkte;

	public void setPunkte(Schnittpunkt p1, Schnittpunkt p2, Schnittpunkt p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		eckPunkte = new Schnittpunkt[] { p1, p2, p3 };
	}

	public Schnittpunkt[] getEckPunkte() {
		return eckPunkte;
	}

	public boolean equals(Dreieck dreieck) {
		Schnittpunkt[] dreiecksEckpunkte = dreieck.getEckPunkte();

		if ((p1.equals(dreiecksEckpunkte[0]) || p1.equals(dreiecksEckpunkte[1]) || p1.equals(dreiecksEckpunkte[2]))
				&& (p2.equals(dreiecksEckpunkte[0]) || p2.equals(dreiecksEckpunkte[1])
						|| p2.equals(dreiecksEckpunkte[2]))
				&& (p3.equals(dreiecksEckpunkte[0]) || p3.equals(dreiecksEckpunkte[1])
						|| p3.equals(dreiecksEckpunkte[2])))
			return true;

		return false;
	}

}
