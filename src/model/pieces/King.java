package model.pieces;

import java.util.HashSet;
import java.util.Set;

import model.board.IBoard;
import model.players.Team;

/**
 * Created by danielchu on 12/30/16.
 */

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
    int distCol = Math.abs(this.col - targetCol);
    int distRow = Math.abs(this.row - targetRow);
    if (distCol > 1 || distRow > 1 || (distCol == 0 && distRow == 0)) {
      return false;
    }
    if(this.willBeInCheck(targetCol, targetRow, board)) {
      return false;
    }
    return super.pathFree(targetCol, targetRow, board);
  }

  @Override
  public void moveTo(int targetCol, int targetRow) {
    super.moveTo(targetCol, targetRow);
    this.hasMoved = true;
  }

  @Override
  public Set<IPiece> canTakeThese(IBoard board) {
    Set<IPiece> result = new HashSet<IPiece>();
    // up
    super.simulateAttacks(this.col, this.row - 1, 0, -1, board);
    // down
    super.simulateAttacks(this.col, this.row + 1, 0, 1, board);
    // left
    super.simulateAttacks(this.col - 1, this.row, -1, 0, board);
    // right
    super.simulateAttacks(this.col + 1, this.row, 1, 0, board);
    return result;
  }

  public boolean willBeInCheck(int targetCol, int targetRow, IBoard board) {
    // TODO implement check for if King will be in Check
    return false;
  }

  private Set<IPiece> filterGuardedPieces(Set<IPiece> allPieces, IBoard board) {
    Set<IPiece> result = new HashSet<IPiece>();
    for(IPiece piece : allPieces) {
      if(!this.willBeInCheck(piece.getCol(), piece.getRow(), board)) {
        result.add(piece);
      }
    }
    return result;
  }
}
