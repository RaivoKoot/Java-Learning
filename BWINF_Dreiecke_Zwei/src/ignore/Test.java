package ignore;

public class Test {

	public static void main(String[] args) {
		System.out.println(gcd(12,18));

	}

	public static int gcd(int m, int n) {
		if (n == 0)
			return m;
		else
			return gcd(n, m % n);
	}
	
}
