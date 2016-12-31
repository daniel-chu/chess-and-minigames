package model.Pieces;

/**
 * Created by danielchu on 12/30/16.
 */

/**
 * Represents the interface for a piece.
 */
public interface IPiece {

  /**
   * Checks if this piece can move to the specified location.
   *
   * @param col   the column to move to
   * @param row   the row to move to
   * @param board the board representing where all pieces are
   * @return if we can move the piece to that spot
   */
  boolean validMove(int col, int row, IPiece[][] board);

  /**
   * Moves the piece to the specified location.
   *
   * @param col the column to move to
   * @param row the row to move to
   */
  void moveTo(int col, int row);

  /**
   * Gets the column this piece is in.
   */
  int getCol();

  /**
   * Gets the row this piece is in.
   */
  int getRow();
}
