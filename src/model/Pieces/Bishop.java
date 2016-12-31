package model.Pieces;

import model.Players.Team;

/**
 * Created by danielchu on 12/30/16.
 */

// TODO implement valid move check

/**
 * Class representing a standard Bishop.
 */
public class Bishop extends APiece {

  /**
   * Constructor for a Bishop piece.
   *
   * @param team the team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public Bishop(Team team, int col, int row) {
    super(team, col, row, PieceType.BISHOP);
  }

  @Override
  public boolean validMove(int col, int row, IPiece[][] board) {
    return true;
  }

}
