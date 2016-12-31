package model;

/**
 * Created by danielchu on 12/30/16.
 */

import model.Pieces.IPiece;

/**
 * Interface for the model for a game of chess.
 */
public interface IChessGameModel {

  /**
   * Makes a move using the given piece to the given location, if the move is valid.
   */
  void movePiece(IPiece piece, int col, int row);

  /**
   * Checks if the game is over.
   *
   * @return if a player has won or not
   */
  boolean isGameOver();
}
