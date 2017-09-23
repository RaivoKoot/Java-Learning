package turnManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.ChessMove;
import logic.MoveCalculator;
import logic.NumberBoardManipulator;

public class TurnManager {

	private MoveCalculator mc;
	private NumberBoardManipulator nbm;

	private boolean playersTurn;

	// b pawn, b rook, b bishop, b knight, b queen, b king, w pawn, w rook, w
	// bishop, w knight, w queen, w king
	private final static int[] HEURISTIC_PIECE_VALUES = { 100, 500, 330, 320, 900, 20000, -100, -500, -330, -320, -900,
			-20000 };

	public TurnManager() {
		setPlayersTurn(true);
		mc = new MoveCalculator();
		nbm = new NumberBoardManipulator();

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

	public int evaluateBoardByPieces(int[] numberBoard, ArrayList<Integer> aiPieceLocations,
			ArrayList<Integer> userPieceLocations) {
		int score = 0;
		try {
			List[] allPieces = { aiPieceLocations, userPieceLocations };

			int type = 100;
			for (int i = 0; i < 2; i++)
				for (int k = 0; k < allPieces[i].size(); k++) {
					type = numberBoard[(int) allPieces[i].get(k)];

					if (type < 0) {
						type *= -1;
						type += 5;
					} else
						type--;

					score += HEURISTIC_PIECE_VALUES[type];
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}

	public ChessMove miniMax(boolean aiTurn, int[] numberBoard, ArrayList<Integer> aiPieceLocations,
			ArrayList<Integer> userPieceLocations, int depth, ChessMove aMove) {

		ChessMove bestMove = null;

		if (depth == 4) {
			int heuristicValue = evaluateBoardByPieces(numberBoard, aiPieceLocations, userPieceLocations);
			aMove.setHeuristicValue(heuristicValue);
			return aMove;
		} else {

			/*
			 * determines whether black or white pieces will be used for this iteration of minimax
			 * post: currentPieceLocations is set to either ai's or user's pieces
			 */
			ArrayList<Integer> currentPieceLocations;
			if (aiTurn)
				currentPieceLocations = aiPieceLocations;
			else
				currentPieceLocations = userPieceLocations;

			/*
			 * generates all possible moves 
			 * post: allPossibleMoves is filled with moves that include their heuristic value
			 */
			ArrayList<ChessMove> allPossibleMoves = new ArrayList<ChessMove>();
			try {
				for (int i = 0; i < currentPieceLocations.size(); i++) {

					int originLocation = currentPieceLocations.get(i);

					ArrayList<Integer> possibleMovesForPiece = mc.generatePossibleMoves(originLocation, numberBoard);

					/*
					 * call miniMax on each move to ultimately get its heuristic value
					 */
					for (int k = 0; k < possibleMovesForPiece.size(); k++) {
						// miniMax
						ChessMove newMove = new ChessMove(originLocation, possibleMovesForPiece.get(k));

						int[] numberBoardCopy = numberBoard.clone();
						nbm.makeMove(newMove, numberBoardCopy);

						ArrayList<Integer> aiPieceLocationsCopy = (ArrayList<Integer>) aiPieceLocations.clone();
						ArrayList<Integer> userPieceLocationsCopy = (ArrayList<Integer>) userPieceLocations.clone();
						if (aiTurn)
							nbm.updatePieceLocations(aiPieceLocationsCopy, userPieceLocationsCopy,
									newMove.getStartingLocation(), newMove.getDestinationLocation());
						else
							nbm.updatePieceLocations(userPieceLocationsCopy, aiPieceLocationsCopy,
									newMove.getStartingLocation(), newMove.getDestinationLocation());

						newMove = miniMax(!aiTurn, numberBoardCopy, aiPieceLocationsCopy, userPieceLocationsCopy,
								depth + 1, newMove);
						allPossibleMoves.add(newMove);
					}
				}

				bestMove = new ChessMove(-1000, -1000);
				if(aiTurn)
					bestMove.setHeuristicValue(-9999999);
				else
					bestMove.setHeuristicValue(1000000);
				/*
				 * choses the move with the best heuristic value
				 * post: bestMove is set to the move with the highest value
				 */
				for (ChessMove move : allPossibleMoves) {
					if (aiTurn) {
						if (move.getHeuristicValue() > bestMove.getHeuristicValue())
							bestMove = move;
					} else if (!aiTurn)
						if (move.getHeuristicValue() < bestMove.getHeuristicValue())
							bestMove = move;

				}

				/*
				 * if the bestMove has the same value as before the move, then all moves have the same value.
				 * Therefore, chose a random move
				 * post: bestMove is set to a random move
				 *
				
				if (bestMove.getHeuristicValue() == evaluateBoardByPieces(numberBoard, aiPieceLocations,
						userPieceLocations)) {
					Random rand = new Random();
					int randomNumber = -1;
				
					randomNumber = rand.nextInt(allPossibleMoves.size());
					bestMove = allPossibleMoves.get(randomNumber);
				}
				
				*/

				if (depth != 0) {
					aMove.setHeuristicValue(bestMove.getHeuristicValue());
					bestMove = aMove;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bestMove;
		}
	}

	/*
	 * [check] make it save piece locations [check]
	 * 
	 * [check] 1. make the minimax algorithm work for both black and white
	 * 
	 * [check] 2. Create an evaluation function that only counts the points you have
	 * by pieces
	 * 
	 * [] 3. integrate the evaluation function so that the algorithm changes from
	 * making random moves to which move will give it the most points. make the
	 * difference between the pieces as good as possible
	 * 
	 * 4. change the algorithm so that it searches at a depth of 2, so that the ai
	 * does not make a move wherafter it's moved piece will be killed unless the
	 * exchange is favorable
	 */
}
