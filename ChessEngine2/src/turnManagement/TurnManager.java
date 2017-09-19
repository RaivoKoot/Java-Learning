package turnManagement;

import java.util.ArrayList;
import java.util.Random;

import logic.MoveCalculator;

public class TurnManager {

	private MoveCalculator mc;

	private boolean playersTurn;

	public TurnManager() {
		setPlayersTurn(true);
		mc = new MoveCalculator();
	}

	public void playerTurnMade() {
		setPlayersTurn(false);
	}

	public boolean isPlayersTurn() {
		return playersTurn;
	}

	public void setPlayersTurn(boolean playersTurn) {
		this.playersTurn = playersTurn;
	}

	public int[] miniMax(int[] numberBoard) {
		int[] moveInformation;

		ArrayList<ArrayList<Integer>> allPossibleMoves = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> pieceLocations = new ArrayList<Integer>();

		for (int i = 10; i < 100; i++) {
			if (numberBoard[i] > 0 && numberBoard[i] != 100) {

				ArrayList<Integer> possibleMoves = mc.generatePossibleMoves(i, numberBoard);
				if (possibleMoves.size() != 0) {
					allPossibleMoves.add(possibleMoves);
					pieceLocations.add(i);
				}
			}
			if (allPossibleMoves.size() > 16)
				i = 100;
		}

		Random rand = new Random();
		int randomPiece = -1;
		int randomMove = -1;

		try {

			randomPiece = rand.nextInt(pieceLocations.size());
			randomMove = rand.nextInt(allPossibleMoves.get(randomPiece).size());
			System.out.println("Size of List" + allPossibleMoves.get(randomPiece).size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Piece List Element index: " + randomPiece);
		System.out.println("Piece location: " + pieceLocations.get(randomPiece));
		System.out.println("Piece type: " + numberBoard[pieceLocations.get(randomPiece)]);
		System.out.println("Random Move List Element index: " + randomMove);
		System.out.println("RandomMove: " + allPossibleMoves.get(randomPiece).get(randomMove));

		moveInformation = new int[] { pieceLocations.get(randomPiece),
				allPossibleMoves.get(randomPiece).get(randomMove) };

		return moveInformation;
	}
	
	/*
	 * 1. make the minimax algorithm work for both black and white
	 * 
	 * 2. Create an evaluation function that only counts the points you have by pieces
	 * 
	 * 3. integrate the evaluation function so that the algorithm changes from making random moves
	 *    to which move will give it the most points. make the difference between the pieces as good as possible
	 *    
	 * 4. change the algorithm so that it searches at a depth of 2, so that the ai does not make a move wherafter 
	 *    it's moved piece will be killed unless the exchange is favorable
	 */
}
