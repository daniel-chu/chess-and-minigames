package model.Pieces;

import model.Board.IBoard;
import model.Players.Team;

/**
 * Created by danielchu on 12/30/16.
 */

// TODO implement valid move check

/**
 * Class representing a standard King.
 */
public class King extends APiece {

  /**
   * If this piece has moved or not. Used to check if we can castle with the king or not.
   */
  protected boolean hasMoved;

  /**
   * Constructor for a King piece.
   *
   * @param team the team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public King(Team team, int col, int row) {
    super(team, col, row, PieceType.KING);
    this.hasMoved = false;
  }

  @Override
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    if (!super.validMove(targetCol, targetRow, board)) {
      return false;
    }
    return true;
  }

  @Override
  public void moveTo(int targetCol, int targetRow) {
    super.moveTo(targetCol, targetRow);
    this.hasMoved = true;
  }
}
