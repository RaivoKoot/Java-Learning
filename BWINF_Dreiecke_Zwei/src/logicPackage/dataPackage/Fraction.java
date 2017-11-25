package logicPackage.dataPackage;

public class Fraction implements Comparable<Fraction> {
	private int numerator;
	private int denominator;

	public Fraction(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}

	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public Fraction multiply(Fraction factor) {
		int newNumerator = numerator * factor.getNumerator();
		int newDenominator = denominator * factor.getDenominator();

		Fraction product = new Fraction(newNumerator, newDenominator);
		product = scaleDownFraction(product);
		product = normalize(product);

		return product;

	}

	public Fraction divideBy(Fraction divisor) {
		Fraction reciprocal = divisor.getReciprocal();
		Fraction quotient = this.multiply(reciprocal);
		quotient = scaleDownFraction(quotient);
		quotient = normalize(quotient);

		return quotient;
	}

	public Fraction getReciprocal() {
		Fraction reciprocal = new Fraction(denominator, numerator);
		return reciprocal;
	}

	public Fraction subtractBy(Fraction subtrahend) {
		if (denominator == 0)
			return subtrahend;
		else if (subtrahend.getDenominator() == 0)
			return this;

		Fraction[] scaledFractions = makeSameDenominator(this, subtrahend);
		Fraction scaledMinuend = scaledFractions[0];
		Fraction scaledSubtrahend = scaledFractions[1];

		// subtracts the subtrahend from the minuend
		int differenceNumerator = scaledMinuend.getNumerator() - scaledSubtrahend.getNumerator();

		int lowestCommonDenominator = scaledMinuend.getDenominator();
		Fraction difference = new Fraction(differenceNumerator, lowestCommonDenominator);
		difference = scaleDownFraction(difference);
		difference = normalize(difference);

		return difference;

	}

	public Fraction add(Fraction addend) {
		if (denominator == 0)
			return addend;
		else if (addend.getDenominator() == 0)
			return this;

		Fraction[] scaledFractions = makeSameDenominator(this, addend);
		Fraction scaledAddend1 = scaledFractions[0];
		Fraction scaledAddend2 = scaledFractions[1];

		int sumNumerator = scaledAddend1.getNumerator() + scaledAddend2.getNumerator();
		int lowestCommonDenominator = scaledAddend1.getDenominator();

		Fraction sum = new Fraction(sumNumerator, lowestCommonDenominator);
		sum = scaleDownFraction(sum);
		sum = normalize(sum);

		return sum;

	}

	public Fraction scaleDownFraction(Fraction fr) {
		if (fr.getNumerator() == 0 || fr.getDenominator() == 0)
			return new Fraction(0, 0);
		int greatestCommonDenonominator = gcd(fr.getDenominator(), fr.getNumerator());

		int newNumerator = fr.getNumerator() / greatestCommonDenonominator;
		int newDenominator = fr.getDenominator() / greatestCommonDenonominator;

		return new Fraction(newNumerator, newDenominator);
	}

	/*
	 * returns the two fractions scaled to the same denominator
	 * 
	 */
	public Fraction[] makeSameDenominator(Fraction fr1, Fraction fr2) {
		// finds the lowest common denominator
		int lowestCommonDenominator = fr1.lowestCommonDenominator(fr2);
		// finds the numbers by which each fraction must be scaled so that both have the
		// same denominator

		int scalingFraction1 = lowestCommonDenominator / fr1.getDenominator();
		int scalingFraction2 = lowestCommonDenominator / fr2.getDenominator();

		// scales the fractions so that both have the same denominator
		Fraction scaledMinuend = fr1.scale(scalingFraction1);
		Fraction scaledSubtrahend = fr2.scale(scalingFraction2);

		return new Fraction[] { scaledMinuend, scaledSubtrahend };
	}

	public Fraction scale(int scale) {
		int scaledNumerator = numerator * scale;
		int scaledDenominator = denominator * scale;

		Fraction scaledFraction = new Fraction(scaledNumerator, scaledDenominator);

		return scaledFraction;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public int lowestCommonDenominator(Fraction otherFraction) {
		int denominator1 = denominator;
		int denominator2 = otherFraction.getDenominator();

		int lcd = lcd(denominator1, denominator2);

		return lcd;

	}

	/*
	 * greatest common denominator of two numbers
	 */
	public static int gcd(int m, int n) {
		if (n == 0)
			return m;
		else
			return gcd(n, m % n);
	}

	/*
	 * lowest common denominator of two numbers
	 */
	public static int lcd(int m, int n) {
		int o = gcd(m, n);
		int p = (m * n) / o;
		return p;
	}

	public String toString() {
		String string = "[" + numerator + "/" + denominator + "]";
		return string;
	}

	public Fraction normalize(Fraction fr) {
		Fraction normalized = fr;
		if (numerator < 0 && denominator < 0)
			normalized = fr.multiply(new Fraction(-1, -1));

		return normalized;
	}

	public int isPositiveZeroOrNegative(int switchValue) {
		int product = numerator * denominator;

		if (product > 0)
			return -1 * switchValue;
		else if (product < 0)
			return 1 * switchValue;

		//System.out.println("\t\t\tNUMBERS ARE EQUAL");
		return 0;
	}

	@Override
	public int compareTo(Fraction otherFraction) {
		if (denominator == 0)
			return otherFraction.isPositiveZeroOrNegative(1);
		else if (otherFraction.getDenominator() == 0)
			return isPositiveZeroOrNegative(-1);

		Fraction[] scaledFractions = makeSameDenominator(this, otherFraction);
		Fraction fr1 = scaledFractions[0];
		Fraction fr2 = scaledFractions[1];

		return Integer.compare(fr1.numerator, fr2.getNumerator());
	}

	public boolean equals(Fraction fr) {
		if (numerator == fr.getNumerator() && denominator == fr.getDenominator())
			return true;
		return false;
	}
}
