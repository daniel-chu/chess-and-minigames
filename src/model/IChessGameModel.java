package model;

/**
 * Created by danielchu on 12/30/16.
 */

import model.pieces.PieceInfo;

/**
 * Interface for the model for a game of chess.
 */
public interface IChessGameModel {

  /**
   * Makes the piece move from its original location to the target location, if the move is valid.
   *
   * @param fromCol   piece's original column
   * @param fromRow   piece's original row
   * @param targetCol target column
   * @param targetRow target row
   * @throws IllegalArgumentException if this move is not valid
   */
  void movePiece(int fromCol, int fromRow, int targetCol, int targetRow) throws
          IllegalArgumentException;

  /**
   * Tells us the status of the game. 0 if the game is not over, 1 if player 1 has won, and 2 if
   * player 2 has won.
   *
   * @return the code representing if the game has been won yet, and if so, by who.
   */
  int isGameOver();

  /**
   * Gets the player whose turn it currently is.
   *
   * @return the number representing the player whose turn it is (1 or 2)
   */
  int whosTurn();

  /**
   * Gets a 2d array representing the location of all pieces in this game.
   *
   * @return the 2d array representing this game
   */
  PieceInfo[][] getBoard();
}
