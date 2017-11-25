package logic;

import java.util.ArrayList;
import java.util.Arrays;

import logic.DataEncapsulation.DateInfo;
import logic.DataEncapsulation.TicketList;

public class TicketCombinationMaker {

	/*
	 * generates all the sums (using only 1, 4, 5 and 6) that equal the groupSize
	 * for example groupSize 6: [1, 1, 4] or [5,1]
	 */
	public static ArrayList<TicketList> getAllPossibleTicketCombinations(int groupSize, int coupons) {
		ArrayList<TicketList> ticketCombinations = new ArrayList<TicketList>();

		// get combinations for current groupSize and any possible smaller groupSize
		// after use of coupons
		for (int couponsLeft = coupons; couponsLeft >= 0 && (couponsLeft == coupons || !DateInfo.isVacation); couponsLeft--, groupSize--) {
			ArrayList<int[]> combinationsForCurrentGroupSize = PartitionFinder.getAllPartitions(groupSize);

			ArrayList<TicketList> ticketLists = new ArrayList<TicketList>();

			for (int[] temp : combinationsForCurrentGroupSize) {
				boolean hasCouponLeft = false;
				if (couponsLeft > 0)
					hasCouponLeft = true;
				TicketList newTicketList = new TicketList(temp, hasCouponLeft, (coupons - couponsLeft));
				ticketLists.add(newTicketList);
			}

			ticketCombinations.addAll(ticketLists);

			// add an indicator at the start of list whether a coupon is left for 10% off
			/*
			 * for (ArrayList<Integer> temp : combos) { if (couponsLeft > 0) temp.add(0, 1);
			 * else temp.add(0, 0); }
			 */

		}

		return ticketCombinations;
	}

	public static void main(String[] args) {


	}

	public static void printList(ArrayList<ArrayList<Integer>> combos) {
		for (ArrayList<Integer> temp : combos)
			System.out.println(Arrays.toString(temp.toArray()));
	}

}
