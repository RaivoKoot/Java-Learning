package logic;

import java.util.ArrayList;
import java.util.Arrays;

import test.PossibleCombinationGenerator;

public class TicketCombinationMaker {

	/*
	 * generates all the sums (using only 1, 4, 5 and 6) that equal the groupSize
	 * for example groupSize 6: [1, 1, 4] or [5,1]
	 */
	public static ArrayList<ArrayList<Integer>> getAllSums(int groupSize, int coupons) {
		ArrayList<ArrayList<Integer>> numberCombinations = new ArrayList<ArrayList<Integer>>();

		// get combinations for current groupSize and any possible smaller groupSize
		// after use of coupons
		for (int couponsLeft = coupons; couponsLeft >= 0; couponsLeft--, groupSize--) {
			ArrayList<ArrayList<Integer>> combos = PossibleCombinationGenerator.getSums(groupSize);

			// add an indicator at the start of list whether a coupon is left for 10% off
			/*
			 * for (ArrayList<Integer> temp : combos) { if (couponsLeft > 0) temp.add(0, 1);
			 * else temp.add(0, 0); }
			 */
			numberCombinations.addAll(combos);
		}

		return numberCombinations;
	}

	/*
	 * generates possible binary numbers for specific length and instead of
	 * 0's and 1's a specific digit and -digit
	 * 
	 * input example: length 3, digit 1
	 * 
	 * output example: [1, 1, 1], [-1, 1, 1], [-1, -1, 1], [-1, -1, -1]
	 */
	public static ArrayList<ArrayList<Integer>> getBinaryCombinations(int length, int digit) {

		ArrayList<ArrayList<Integer>> combos = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < length + 1; i++) {
			ArrayList<Integer> newCombo = new ArrayList<Integer>();

			for (int j = 0; j < length; j++) {
				if (j < i)
					newCombo.add(digit);
				else
					newCombo.add(-digit);
			}

			combos.add(newCombo);
		}

		System.out.println("\n");
		printList(combos);

		return combos;
	}

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> test = getAllSums(32, 0);

		printList(test);

		getBinaryCombinations(5, 1);

		/*
		 * 1, 1, 1, 1 0, 1, 1, 1 0, 0, 1, 1 0, 0, 0, 1 0, 0, 0, 0
		 * 
		 * 1, 1, 1 0, 1, 1 0, 0, 1 0, 0, 0
		 * 
		 * 1, 1, 1, 1, 1 0, 1, 1, 1, 1 0, 0, 1, 1, 1 0, 0, 0, 1, 1 0, 0, 0, 0, 1 0, 0,
		 * 0, 0, 0
		 */
	}
	
	public static void printList(ArrayList<ArrayList<Integer>> combos) {
		for (ArrayList<Integer> temp : combos)
			System.out.println(Arrays.toString(temp.toArray()));
	}

}
