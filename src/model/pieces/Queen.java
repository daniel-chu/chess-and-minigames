package model.pieces;

/**
 * Created by danielchu on 12/30/16.
 */

import java.util.HashSet;
import java.util.Set;

import model.board.IBoard;
import model.players.Team;

/**
 * Class representing a standard Queen.
 */
public class Queen extends APiece {

  /**
   * Constructor for a Queen piece.
   *
   * @param team the team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public Queen(Team team, int col, int row) {
    super(team, col, row, PieceType.QUEEN);
  }

  @Override
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    if (!super.validMove(targetCol, targetRow, board)) {
      return false;
    }
    int distCol = Math.abs(this.col - targetCol);
    int distRow = Math.abs(this.row - targetRow);
    if (((distCol == 0) == (distRow == 0)) && (distCol != distRow)) {
      return false;
    }
    return super.pathFree(targetCol, targetRow, board);
  }

  @Override
  public Set<IPiece> canTakeThese(IBoard board) {
    Set<IPiece> result = new HashSet<IPiece>();
    // traverses left
    result.addAll(super.simulateAttacks(this.col - 1, this.row, -1, 0, board));
    // traverses right
    result.addAll(super.simulateAttacks(this.col + 1, this.row, 1, 0, board));
    // traverses up
    result.addAll(super.simulateAttacks(this.col, this.row + 1, 0, 1, board));
    // traverses down
    result.addAll(super.simulateAttacks(this.col, this.row - 1, 0, -1, board));
    // traverses diagonal up/left
    result.addAll(super.simulateAttacks(this.col - 1, this.row - 1, -1, -1, board));
    // traverses diagonal up/right
    result.addAll(super.simulateAttacks(this.col + 1, this.row - 1, 1, -1, board));
    // traverses diagonal down/left
    result.addAll(super.simulateAttacks(this.col - 1, this.row + 1, -1, 1, board));
    // traverses diagonal down/right
    result.addAll(super.simulateAttacks(this.col + 1, this.row + 1, 1, 1, board));
    return result;
  }

  @Override
  public IPiece copy() {
    return new Queen(this.getTeam(), this.getCol(), this.getRow());
  }
}
