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

	public int[] miniMax(boolean aiTurn, int[] numberBoard, ArrayList<Integer> aiPieceLocations,
			ArrayList<Integer> userPieceLocations) {
		int[] moveInformation = new int[2];

		ArrayList<ArrayList<Integer>> allPossibleMoves = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> currentPieceLocations;

		// set white or black pieces to be checked
		if (aiTurn)
			currentPieceLocations = aiPieceLocations;
		else
			currentPieceLocations = userPieceLocations;


		for (int temp : currentPieceLocations)
			System.out.println(temp+", ");
		
		// A List of all the pieces where the pieces that can not move at this
		// points will be removed
		ArrayList<Integer> movablePieces = new ArrayList<Integer>(currentPieceLocations);

		// add the lists of pieces' moves to the allPossibleMoves list if the
		// piece has at least one available move

		try {
			for (int i = 0; i < movablePieces.size(); i++) {

				ArrayList<Integer> possibleMovesForPiece = mc.generatePossibleMoves(movablePieces.get(i), numberBoard);


				if (possibleMovesForPiece.size() != 0)
					allPossibleMoves.add(possibleMovesForPiece);
				else {
					movablePieces.remove(i);
					i--;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Random rand = new Random();
		int randomPiece = -1;
		int randomMove = -1;

		try {

			randomPiece = rand.nextInt(movablePieces.size());
			randomMove = rand.nextInt(allPossibleMoves.get(randomPiece).size());
			System.out.println("Size of List" + allPossibleMoves.get(randomPiece).size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("Piece List Element index: " + randomPiece);
			System.out.println("Piece location: " + movablePieces.get(randomPiece));
			System.out.println("Piece type: " + numberBoard[movablePieces.get(randomPiece)]);
			System.out.println("Random Move List Element index: " + randomMove);
			System.out.println("RandomMove: " + allPossibleMoves.get(randomPiece).get(randomMove));

			moveInformation = new int[] { movablePieces.get(randomPiece),
					allPossibleMoves.get(randomPiece).get(randomMove) };

		} catch (Exception e) {
			e.printStackTrace();
		}

		return moveInformation;
	}

	/*
	 * make it save piece locations
	 * 
	 * 1. make the minimax algorithm work for both black and white
	 * 
	 * 2. Create an evaluation function that only counts the points you have by
	 * pieces
	 * 
	 * 3. integrate the evaluation function so that the algorithm changes from
	 * making random moves to which move will give it the most points. make the
	 * difference between the pieces as good as possible
	 * 
	 * 4. change the algorithm so that it searches at a depth of 2, so that the
	 * ai does not make a move wherafter it's moved piece will be killed unless
	 * the exchange is favorable
	 */
}
