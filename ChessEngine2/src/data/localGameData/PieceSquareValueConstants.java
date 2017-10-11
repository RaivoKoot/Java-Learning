package data.localGameData;

public class PieceSquareValueConstants {

	/* White Field Points */
	private static final int[] W_PAWN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -50, -50, -50,
			-50, -50, -50, -50, -50, 0, 0, -10, -10, -20, -30, -30, -20, -10, -10, 0, 0, -5, -5, -10, -25, -25, -10, -5,
			-5, 0, 0, 0, 0, 0, -20, -20, 0, 0, 0, 0, 0, -5, 5, 10, 0, 0, 10, 5, -5, 0, 0, -5, -10, -10, 20, 20, -10,
			-10, -5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] W_ROOK = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -5, -10, -10,
			-10, -10, -10, -10, -5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0,
			0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] W_BISHOP = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 10, 10, 10, 10, 10, 10, 20, 0, 0, 10,
			0, 0, 0, 0, 0, 0, 10, 0, 0, 10, 0, -5, -10, -10, -5, 0, 10, 0, 0, 10, -5, -5, -10, -10, -5, -5, 10, 0, 0,
			10, 0, -10, -10, -10, -10, 0, 10, 0, 0, 10, -10, -10, -10, -10, -10, -10, 10, 0, 0, 10, -5, 0, 0, 0, 0, -5,
			10, 0, 0, 20, 10, 10, 10, 10, 10, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] W_KNIGHT = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 40, 30, 30, 30, 30, 40, 50, 0, 0, 40,
			20, 0, 0, 0, 0, 20, 40, 0, 0, 30, 0, -10, -15, -15, -10, 0, 30, 0, 0, 30, -5, -15, -20, -20, -15, -5, 30, 0,
			0, 30, 0, -15, -20, -20, -15, 0, 30, 0, 0, 30, -5, -10, -15, -15, -10, -5, 30, 0, 0, 40, 20, 0, -5, -5, 0,
			20, 40, 0, 0, 50, 40, 30, 30, 30, 30, 40, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] W_QUEEN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 10, 10, 5, 5, 10, 10, 20, 0, 0, 10, 0,
			0, 0, 0, 0, 0, 10, 0, 0, 10, 0, -5, -5, -5, -5, 0, 10, 0, 0, 5, 0, -5, -5, -5, -5, 0, 5, 0, 0, 0, 0, -5, -5,
			-5, -5, 0, 5, 0, 0, 10, -5, -5, -5, -5, -5, 0, 10, 0, 0, 10, 0, -5, 0, 0, 0, 0, 10, 0, 0, 20, 10, 10, 5, 5,
			10, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] W_KING = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 40, 40, 50, 50, 40, 40, 30, 0, 0, 30, 40,
			40, 50, 50, 40, 40, 30, 0, 0, 30, 40, 40, 50, 50, 40, 40, 30, 0, 0, 30, 40, 40, 50, 50, 40, 40, 30, 0, 0,
			20, 30, 30, 40, 40, 30, 30, 20, 0, 0, 10, 20, 20, 20, 20, 20, 20, 10, 0, 0, -20, -20, 0, 0, 0, 0, -20, -20,
			0, 0, -20, -30, -10, 0, 0, -10, -30, -20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[][] WHITE_SQUARE_VALUES = { W_PAWN, W_ROOK, W_BISHOP, W_KNIGHT, W_QUEEN, W_KING };

	/* Black Field Points */
	private static final int[] B_PAWN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 10, 10, -20,
			-20, 10, 10, 5, 0, 0, 5, -5, -10, 0, 0, -10, -5, 5, 0, 0, 0, 0, 0, 20, 20, 0, 0, 0, 0, 0, 5, 5, 10, 25, 25,
			10, 5, 5, 0, 0, 10, 10, 20, 30, 30, 20, 10, 10, 0, 0, 50, 50, 50, 50, 50, 50, 50, 50, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] B_ROOK = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, -5, 0, 0, 0, 0,
			0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0,
			0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, 5, 10, 10, 10, 10, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0 };

	private static final int[] B_BISHOP = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -20, -10, -10, -10, -10, -10, -10, -20, 0,
			0, -10, 5, 0, 0, 0, 0, 5, -10, 0, 0, -10, 10, 10, 10, 10, 10, 10, -10, 0, 0, -10, 0, 10, 10, 10, 10, 0, -10,
			0, 0, -10, 5, 5, 10, 10, 5, 5, -10, 0, 0, -10, 0, 5, 10, 10, 5, 0, -10, 0, 0, -10, 0, 0, 0, 0, 0, 0, -10, 0,
			0, -20, -10, -10, -10, -10, -10, -10, -20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] B_KNIGHT = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -50, -40, -30, -30, -30, -30, -40, -50, 0,
			0, -40, -20, 0, 5, 5, 0, -20, -40, 0, 0, -30, 5, 10, 15, 15, 10, 5, -30, 0, 0, -30, 0, 15, 20, 20, 15, 0,
			-30, 0, 0, -30, 5, 15, 20, 20, 15, 5, -30, 0, 0, -30, 0, 10, 15, 15, 10, 0, -30, 0, 0, -40, -20, 0, 0, 0, 0,
			-20, -40, 0, 0, -50, -40, -30, -30, -30, -30, -40, -50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] B_QUEEN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -20, -10, -10, -5, -5, -10, -10, -20, 0, 0,
			-10, 0, 5, 0, 0, 0, 0, -10, 0, 0, -10, 5, 5, 5, 5, 5, 0, -10, 0, 0, 0, 0, 5, 5, 5, 5, 0, -5, 0, 0, -5, 0, 5,
			5, 5, 5, 0, -5, 0, 0, -10, 0, 5, 5, 5, 5, 0, -10, 0, 0, -10, 0, 0, 0, 0, 0, 0, -10, 0, 0, -20, -10, -10, -5,
			-5, -10, -10, -20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int[] B_KING = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 30, 10, 0, 0, 10, 30, 20, 0, 0, 20, 20,
			0, 0, 0, 0, 20, 20, 0, 0, -10, -20, -20, -20, -20, -20, -20, -10, 0, 0, -20, -30, -30, -40, -40, -30, -30,
			-20, 0, 0, -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, -30,
			-40, -40, -50, -50, -40, -40, -30, 0, 0, -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0 };

	private static final int[][] BLACK_SQUARE_VALUES = { B_PAWN, B_ROOK, B_BISHOP, B_KNIGHT, B_QUEEN, B_KING };

	private static final int[][] SQUARE_VALUES = { B_PAWN, B_ROOK, B_BISHOP, B_KNIGHT, B_QUEEN, B_KING, W_PAWN, W_ROOK,
			W_BISHOP, W_KNIGHT, W_QUEEN, W_KING };

	public static int[][] getWhiteSquareValues() {
		return WHITE_SQUARE_VALUES;
	}

	public static int[][] getBlackSquareValues() {
		return BLACK_SQUARE_VALUES;
	}

	public static int[][] getSquareValues() {
		return SQUARE_VALUES;
	}
	/*
		
		public static void main(String[] args) {
			PieceSquareValueConstants values = new PieceSquareValueConstants();
	
			for (int i = 0; i < 6; i++) {
				int[] array = WHITE_SQUARE_VALUES[i].clone();
	
				values.negateArray(array);
				array = values.reverseArray(array);
				System.out.println(values.getArrayAsString(array));
			}
		}
	
		public int[] addBorder(int[] cellsWithoutBorder) {
			int[] cellsWithBorder = new int[100];
	
			for (int i = 0, k = 0; i < 100; i++) {
				if (i > 88 || i / 10 == 0 || i % 10 == 0 || i % 10 == 9)
					cellsWithBorder[i] = 0;
				else {
					cellsWithBorder[i] = cellsWithoutBorder[k];
					k++;
				}
	
			}
			return cellsWithBorder;
		}
	
		public String getArrayAsString(int[] array) {
			String arrayString = "";
			for (int i = 0; i < 100; i++) {
				if (array[i] >= 0)
					arrayString += " ";
				if (Math.abs(array[i]) < 10)
					arrayString += " ";
				arrayString += array[i] + ", ";
				if (i % 10 == 9)
					arrayString += "\n";
			}
	
			return arrayString;
		}
	
		public void negateArray(int[] array) {
			for (int i = 0; i < array.length; i++) {
				array[i] = array[i] * -1;
			}
		}
		
		public static int[] reverseArray(int[] array) {
		int[] tempArray = array.clone();
		for (int i = 0; i < 50; i++) {
			int oppositeLocation = 99 - (9 - i % 10) - ((i / 10) * 10);

			array[i] = array[oppositeLocation];
			array[oppositeLocation] = tempArray[i];
		}

		return array;
	}
		
		*/

}
