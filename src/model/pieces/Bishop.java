package model.pieces;

import java.util.ArrayList;
import java.util.List;

import model.board.IBoard;
import model.players.Team;

/**
 * Created by danielchu on 12/30/16.
 */

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
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    if (!super.validMove(targetCol, targetRow, board)) {
      return false;
    }
    int distCol = Math.abs(this.col - targetCol);
    int distRow = Math.abs(this.row - targetRow);
    if (distCol == 0 || distRow == 0) {
      return false;
    }
    if (distCol != distRow) {
      return false;
    }
    return super.pathFree(targetCol, targetRow, board);
  }

  @Override
  public List<IPiece> canTakeThese(IBoard board) {
    List<IPiece> result = new ArrayList<IPiece>();
    // up+left
    result.addAll(super.simulateAttacks(this.col - 1, this.row - 1, -1, -1, board));
    // up+right
    result.addAll(super.simulateAttacks(this.col + 1, this.row - 1, 1, -1, board));
    // down+left
    result.addAll(super.simulateAttacks(this.col - 1, this.row + 1, -1, 1, board));
    // down+right
    result.addAll(super.simulateAttacks(this.col + 1, this.row + 1, 1, 1, board));
    return result;
  }

}
