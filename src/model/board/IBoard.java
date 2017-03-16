package model.board;

import java.util.List;

import model.pieces.IPiece;

/**
 * Created by danielchu on 12/30/16.
 */

/**
 * Interface for chess boards.
 */
public interface IBoard {

  /**
   * Adds the piece to the given coordinate.
   *
   * @param piece the piece we are adding
   * @param col   the column we are adding it to
   * @param row   the row we are adding it to
   */
  void addPiece(IPiece piece, int col, int row);

  /**
   * Removes a piece from the given coordinate.
   *
   * @param col the column we are removing the piece from
   * @param row the row we are removing the piece from
   */
  void removePiece(int col, int row);

  /**
   * Gets the piece at the given coordinate.
   *
   * @param col the column we are looking at
   * @param row the row we are looking at
   */
  IPiece getPieceAt(int col, int row);

  /**
   * Move the piece at the start coord to the end coord, and returns a piece that was taken as a
   * result of this move. Returns null if none.
   *
   * @param fromCol   the column the piece we are moving is in
   * @param fromRow   the row the piece we are moving is in
   * @param targetCol the target column
   * @param targetRow the target row
   * @return the Piece that has been taken as a result of this move. null if none
   */
  IPiece movePieceFromTo(int fromCol, int fromRow, int targetCol, int targetRow);

  /**
   * Gets width of the board (how many columns).
   *
   * @return how many columns wide the board is
   */
  int getWidth();

  /**
   * Gets height of the board (how many rows).
   *
   * @return how many rows tall the board is
   */
  int getHeight();

  /**
   * Tells us if the pair of coordinates is valid (within range of this board)
   *
   * @param col the column of the coordinate
   * @param row the row of the coordinate
   * @return if this pair of coordinates is on this board
   */
  boolean validCoordinates(int col, int row);

  /**
   * Gets a list of all pieces on this board.
   *
   * @return a list of all pieces on the board.
   */
  List<IPiece> getAllPiecesOnBoard();

  /**
   * Creates a deep copy of the board.
   *
   * @return a board that is a copy of this one.
   */
  IBoard deepCopy();

}
