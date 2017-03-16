package model.pieces;

/**
 * Created by danielchu on 12/30/16.
 */

import java.util.HashSet;
import java.util.Set;

import model.board.IBoard;
import model.players.Team;

/**
 * Class representing a standard Pawn.
 */
public class Pawn extends APiece {

  /**
   * If this piece has moved or not. Used to check if we can make the two move jump on the first
   * turn.
   */
  protected boolean hasMoved;

  /**
   * If pawn's forward direction is up (as in moving from row 1 -> 2 -> 3, etc.) or incrementing
   */
  protected boolean upIsForward;

  /**
   * Constructor for a Pawn piece.
   *
   * @param team the /team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public Pawn(Team team, int col, int row) {
    super(team, col, row, PieceType.PAWN);
    this.hasMoved = false;
    if (team == Team.ONE) {
      this.upIsForward = true;
    } else {
      this.upIsForward = false;
    }
  }

  @Override
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    if (!super.validMove(targetCol, targetRow, board)) {
      return false;
    }
    int distCol = Math.abs(this.col - targetCol);
    int distRow = targetRow - this.row;
    // filters out impossible moves regardless of board situation
    if (distCol < 0 || distCol > 1 || Math.abs(distRow) > 2 || distRow == 0) {
      return false;
    }
    // sets what a forward increment is for this pawn
    int forwardIncrement;
    if (this.upIsForward) {
      forwardIncrement = 1;
    } else {
      forwardIncrement = -1;
    }
    // if it is moving straight
    if (distCol == 0) {
      if ((distRow != forwardIncrement && (distRow != (2 * forwardIncrement) ||
              this.hasMoved)) || (board.getPieceAt(targetCol, targetRow) != null)) {
        return false;
      }
    }
    // if it is moving diagonal it must be taking something
    if (distCol == 1 && distRow == forwardIncrement) {
      if (!this.isTakingPiece(targetCol, forwardIncrement, board)) {
        return false;
      }
    }
    return super.pathFree(targetCol, targetRow, board);
  }

  /**
   * Checks if this pawn is going to take a piece by moving to the given column, and one row
   * forward. Also will check if the piece is performing an en passant.
   */
  private boolean isTakingPiece(int targetCol, int forwardIncrement, IBoard board) {
    // todo implement en passant
    int distCol = Math.abs(this.col - targetCol);
    if (distCol == 1) {
      if (board.getPieceAt(targetCol, this.row + forwardIncrement) != null) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void moveTo(int targetCol, int targetRow) {
    super.moveTo(targetCol, targetRow);
    this.hasMoved = true;
  }

  @Override
  public Set<IPiece> canTakeThese(IBoard board) {
    //TODO implement this
    Set<IPiece> result = new HashSet<IPiece>();
    return result;
  }

  @Override
  public IPiece copy() {
    return new Pawn(this.getTeam(), this.getCol(), this.getRow());
  }
}
