import java.math.BigDecimal;

public class Gleichung {

	private BigDecimal gleichungLinkeNummer;
	private BigDecimal gleichungRechteNummer;

	private BigDecimal gleichungLinkesX;
	private BigDecimal gleichungRechtesX;

	public Gleichung(BigDecimal linkesB, BigDecimal rechtesB, BigDecimal linkesX, BigDecimal rechtesX) {
		this.setGleichungLinkeNummer(linkesB);
		this.setGleichungRechteNummer(rechtesB);

		this.setGleichungLinkesX(linkesX);
		this.setGleichungRechtesX(rechtesX);
	}

	public BigDecimal getGleichungLinkeNummer() {
		return gleichungLinkeNummer;
	}

	public void setGleichungLinkeNummer(BigDecimal gleichungLinkeNummer) {
		this.gleichungLinkeNummer = gleichungLinkeNummer;
	}

	public BigDecimal getGleichungLinkesX() {
		return gleichungLinkesX;
	}

	public void setGleichungLinkesX(BigDecimal gleichungLinkesX) {
		this.gleichungLinkesX = gleichungLinkesX;
	}

	public BigDecimal getGleichungRechteNummer() {
		return gleichungRechteNummer;
	}

	public void setGleichungRechteNummer(BigDecimal gleichungRechteNummer) {
		this.gleichungRechteNummer = gleichungRechteNummer;
	}

	public BigDecimal getGleichungRechtesX() {
		return gleichungRechtesX;
	}

	public void setGleichungRechtesX(BigDecimal gleichungRechtesX) {
		this.gleichungRechtesX = gleichungRechtesX;
	}

}
