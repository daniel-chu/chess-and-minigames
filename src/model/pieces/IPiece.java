package model.pieces;

import java.util.List;

import model.board.IBoard;
import model.players.Team;

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
   * @param targetCol the column to move to
   * @param targetRow the row to move to
   * @param board     the board representing where all pieces are
   * @return if we can move the piece to that spot
   */
  boolean validMove(int targetCol, int targetRow, IBoard board);

  /**
   * Moves the piece to the specified location.
   *
   * @param targetCol the column to move to
   * @param targetRow the row to move to
   */
  void moveTo(int targetCol, int targetRow);

  /**
   * Gives a list of enemy pieces that would be able to take this piece with a single move.
   *
   * @param board the board we are checking
   * @return a list of pieces that are capable of taking this piece
   */
  List<IPiece> canBeTakenBy(IBoard board);

  /**
   * Gives a list of enemy pieces that this piece is able to take in one move.
   *
   * @param board the board we are checking
   * @return a list of pieces that this piece can take
   */
  List<IPiece> canTakeThese(IBoard board);

  /**
   * Gets the column this piece is in.
   *
   * @return the column
   */
  int getCol();

  /**
   * Gets the row this piece is in.
   *
   * @return the row
   */
  int getRow();

  /**
   * Gets the team this piece is on.
   *
   * @return the team of this piece
   */
  Team getTeam();

  /**
   * Gets the type of this piece.
   *
   * @return the type of this piece
   */
  PieceType getType();
}
