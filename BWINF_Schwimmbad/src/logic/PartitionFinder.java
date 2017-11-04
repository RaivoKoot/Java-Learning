package logic;

import java.util.ArrayList;
import java.util.Arrays;

public class PartitionFinder {

	public static ArrayList<String> partition(int n) {
		ArrayList<String> partitions = new ArrayList<String>();

		partition(n, 6, "", partitions);

		return partitions;
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
	 * input example: length 3, digit 1
	 * 
	 * output example: [1, 1, 1], [-1, 1, 1], [-1, -1, 1], [-1, -1, -1]
	 */
	public static ArrayList<int[]> getBinaryCombinations(int length, int digit) {

		ArrayList<int[]> combos = new ArrayList<int[]>();

		for (int i = 0; i < length + 1; i++) {
			int[] newCombo = new int[length];

			for (int j = 0; j < length; j++) {
				if (j < i)
					newCombo[j] = digit;
				else
					newCombo[j] = -digit;
			}

			combos.add(newCombo);
		}

		return combos;
	}

	public static void testtest(ArrayList<int[]> partitions) {
		ArrayList<int[]> allPartitions = new ArrayList<int[]>();

		for (int[] temp : partitions) {

			int[] occurence = findRowOfDigit(temp, 1);

			// no change needed
			if (occurence[0] == -1)
				allPartitions.add(temp);

		}

	}

	/*
	 * finds the index of the first occurence of digit and the index of the last
	 * occurence of digit
	 */
	public static int[] findRowOfDigit(int[] numbers, int digit) {
		// index 0 is first occurence of digit
		// index 1 is last occurence of digit
		int[] occurence = { -1, -1 };

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] != digit)
				continue;

			if (occurence[0] == -1)
				occurence[0] = i;

			occurence[1] = i;

		}

		return occurence;
	}

	public static void printList(ArrayList<int[]> combos) {
		for (int[] temp : combos)
			System.out.println(Arrays.toString(temp));
	}

	public static void main(String[] args) {
		int n = 6;

		ArrayList<String> partitions = partition(n);
		ArrayList<int[]> partitionArrays = convertToListOfArrays(partitions);

		printList(partitionArrays);
		System.out.println();

		ArrayList<int[]> test = getBinaryCombinations(4, 4);
		printList(test);
	}
}
