package test;

import java.util.ArrayList;
import java.util.Arrays;

class PossibleCombinationGenerator {

	/*
	 * Method taken from WWW.StackOverflow.com and edited by me
	 * https://stackoverflow.com/questions/4632322/finding-all-possible-combinations
	 * -of-numbers-to-reach-a-given-sum
	 */
	public static void sum_up_recursive(ArrayList<Integer> numbers, int groupSize, ArrayList<Integer> partial,
			ArrayList<ArrayList<Integer>> combos) {
		int sum = 0;
		for (int x : partial)
			sum += x;

		if (sum == groupSize) {
			if (combos.contains(partial))
				return;
			combos.add(partial);
		}

		if (sum >= groupSize)
			return;

		for (int i = 0; i < numbers.size(); i++) {
			ArrayList<Integer> remaining = new ArrayList<Integer>();
			int n = numbers.get(i);

			for (int j = i + 1; j < numbers.size(); j++)
				remaining.add(numbers.get(j));

			ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);

			partial_rec.add(n);

			sum_up_recursive(remaining, groupSize, partial_rec, combos);
		}
	}

	public static ArrayList<ArrayList<Integer>> getSums(int groupSize) {
		int[] ticketSizes = { 1, 4, 5, 6 };
		ArrayList<Integer> numbers = new ArrayList<Integer>();

		for (int i = 0; i < ticketSizes.length; i++) {
			int timesNumberFitsInGroupSize = groupSize / ticketSizes[i];

			for (int j = 0; j < timesNumberFitsInGroupSize; j++)
				numbers.add(ticketSizes[i]);
		}

		ArrayList<ArrayList<Integer>> combos = new ArrayList<ArrayList<Integer>>();

		sum_up_recursive(numbers, groupSize, new ArrayList<Integer>(), combos);

		return combos;
	}

	public static void printList(ArrayList<ArrayList<Integer>> combos) {
		for (ArrayList<Integer> temp : combos)
			System.out.println(Arrays.toString(temp.toArray()));
	}
}
