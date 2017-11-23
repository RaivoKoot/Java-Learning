package logicPackage;
import logicPackage.dataPackage.MatheVektor;

public class GeradenGleichung {

	private MatheVektor ortsVektor;
	private MatheVektor richtungsVektor;

	public MatheVektor getRichtungsVektor() {
		return richtungsVektor;
	}

	public void setRichtungsVektor(MatheVektor richtungsVektor) {
		this.richtungsVektor = richtungsVektor;
	}

	public MatheVektor getOrtsvektor() {
		return ortsVektor;
	}

	public void setOrtsvektor(MatheVektor ortsVektor) {
		this.ortsVektor = ortsVektor;
	}

	public String toString() {
		String string = ortsVektor.toString() + "+r" + richtungsVektor.toString();
		return string;
	}

}
