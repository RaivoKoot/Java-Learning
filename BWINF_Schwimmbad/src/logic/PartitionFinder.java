package logic;

import java.util.ArrayList;
import java.util.Arrays;

public class PartitionFinder {

	public static ArrayList<int[]> getAllPartitions(int n) {
		ArrayList<String> partitions = new ArrayList<String>();

		partition(n, 6, "", partitions);

		ArrayList<int[]> partitionAsArrays = convertToListOfArrays(partitions);

		ArrayList<int[]> allPartitions = expandPartitions(partitionAsArrays, 1);
		allPartitions = expandPartitions(allPartitions, 4);

		return allPartitions;
	}

	/*
	 * method "partition" taken from study book
	 * https://introcs.cs.princeton.edu/java/23recursion/
	 * 
	 * edited by me to fit my needs
	 */
	public static void partition(int n, int max, String prefix, ArrayList<String> partitions) {
		if (n == 0) {
			partitions.add(prefix);
			return;
		}

		for (int i = Math.min(max, n); i >= 1; i--) {
			if (i == 3 || i == 2)
				i = 1;
			partition(n - i, i, prefix + " " + i, partitions);
		}
	}

	public static ArrayList<int[]> convertToListOfArrays(ArrayList<String> partitions) {
		ArrayList<int[]> partitionArrays = new ArrayList<int[]>();

		for (String temp : partitions) {
			int[] partitionArray = parseIntArray(temp);

			partitionArrays.add(partitionArray);
		}

		return partitionArrays;
	}

	public static int[] parseIntArray(String string) {
		int[] parsedArray = new int[string.length() / 2];

		for (int i = 0, j = 1; j < string.length(); i++, j += 2) {
			parsedArray[i] = Character.getNumericValue(string.charAt(j));
		}

		return parsedArray;
	}

	/*
	 * generates possible binary numbers for specific length and instead of 0's and
	 * 1's a specific digit and -digit
	 * 
	 * input example: length 2, digit 1
	 * 
	 * output example: [1, 1], [-1, 1], [-1, -1]
	 */
	public static ArrayList<int[]> getBinaryCombinations(int length, int digit) {

		ArrayList<int[]> combos = new ArrayList<int[]>();

		for (int i = 0; i < length + 1; i++) {
			int[] newCombo = new int[length];

			for (int j = 0; j < length; j++) {
				if (j < i)
					newCombo[j] = -digit;
				else
					newCombo[j] = digit;
			}

			combos.add(newCombo);
		}

		return combos;
	}

	/*
	 * 
	 * 
	 */
	public static ArrayList<int[]> expandPartitions(ArrayList<int[]> sets, int exchangeDigit) {
		ArrayList<int[]> allSets = new ArrayList<int[]>();

		for (int[] temp : sets) {

			// indicates start and end of subset of digit
			int[] subset = new int[2];
			subset = findSubsetLocation(temp, exchangeDigit);

			// no change needed
			if (subset[0] == -1) {
				allSets.add(temp);
				continue;
			}

			int subsetLength = subset[1] - subset[0] + 1;

			// get subset combinations
			ArrayList<int[]> subsetCombinations = getBinaryCombinations(subsetLength, exchangeDigit);

			// insert the combinations into the original sets
			ArrayList<int[]> newSets = exchangeSubset(temp, subsetCombinations, subset[0]);

			allSets.addAll(newSets);

		}

		return allSets;

	}

	public static ArrayList<int[]> exchangeSubset(int[] set, ArrayList<int[]> subsets, int startIndex) {
		ArrayList<int[]> newSets = new ArrayList<int[]>();

		for (int i = 0; i < subsets.size(); i++) {

			int[] newSet = set.clone();
			int[] subset = subsets.get(i);

			for (int j = 0; j < subset.length; j++)
				newSet[startIndex + j] = subset[j];

			newSets.add(newSet);

		}

		return newSets;
	}

	/*
	 * finds the index of the first occurence of digit and the index of the last
	 * occurence of digit
	 */
	public static int[] findSubsetLocation(int[] set, int digit) {
		// index 0 is first occurence of digit
		// index 1 is last occurence of digit
		int[] subset = { -1, -1 };

		for (int i = 0; i < set.length; i++) {
			if (set[i] != digit)
				continue;

			if (subset[0] == -1)
				subset[0] = i;

			subset[1] = i;

		}

		return subset;
	}

	public static void printList(ArrayList<int[]> combos) {
		for (int[] temp : combos)
			System.out.println(Arrays.toString(temp));
	}

	/*
	 * 
	 * methods to switch 1's and 4's working. next: implement ticketlist creator
	 * 
	 */
	public static void main(String[] args) {
		int n = 8;

		ArrayList<int[]> partitionArrays = getAllPartitions(n);
		
		printList(partitionArrays);
		/*
		 * for (int[] temp : partitionArrays) { int[] occurences = findRowOfDigit(temp,
		 * 4); System.out.println("Occurences: " + Arrays.toString(occurences)); }
		 */
	}
}
