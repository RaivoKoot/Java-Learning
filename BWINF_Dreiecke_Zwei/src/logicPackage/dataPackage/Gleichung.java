package logicPackage.dataPackage;

public class Gleichung {

	private Fraction gleichungLinkeNummer;
	private Fraction gleichungRechteNummer;

	private Fraction gleichungLinkesX;
	private Fraction gleichungRechtesX;

	public Gleichung(Fraction linkesB, Fraction rechtesB, Fraction linkesX, Fraction rechtesX) {
		this.setGleichungLinkeNummer(linkesB);
		this.setGleichungRechteNummer(rechtesB);

		this.setGleichungLinkesX(linkesX);
		this.setGleichungRechtesX(rechtesX);
	}

	public Fraction getGleichungLinkeNummer() {
		return gleichungLinkeNummer;
	}

	public void setGleichungLinkeNummer(Fraction gleichungLinkeNummer) {
		this.gleichungLinkeNummer = gleichungLinkeNummer;
	}

	public Fraction getGleichungLinkesX() {
		return gleichungLinkesX;
	}

	public void setGleichungLinkesX(Fraction gleichungLinkesX) {
		this.gleichungLinkesX = gleichungLinkesX;
	}

	public Fraction getGleichungRechteNummer() {
		return gleichungRechteNummer;
	}

	public void setGleichungRechteNummer(Fraction gleichungRechteNummer) {
		this.gleichungRechteNummer = gleichungRechteNummer;
	}

	public Fraction getGleichungRechtesX() {
		return gleichungRechtesX;
	}

	public void setGleichungRechtesX(Fraction gleichungRechtesX) {
		this.gleichungRechtesX = gleichungRechtesX;
	}

	public String toString() {
		String string = gleichungLinkeNummer + "+" + gleichungLinkesX + "x = " + gleichungRechteNummer + "+"
				+ gleichungRechtesX+"x";
		return string;
	}

}
