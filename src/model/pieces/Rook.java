package model.pieces;

/**
 * Created by danielchu on 12/30/16.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Move;
import model.board.IBoard;
import model.players.Team;

/**
 * Class representing a standard Rook.
 */
public class Rook extends APiece {

  /**
   * If this piece has moved or not. Used to check if we can castle with this rook or not.
   */
  protected boolean hasMoved;

  /**
   * Constructor for a Rook piece.
   *
   * @param team the team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public Rook(Team team, int col, int row) {
    super(team, col, row, PieceType.ROOK);
    this.hasMoved = false;
  }

  @Override
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    if (!super.validMove(targetCol, targetRow, board)) {
      return false;
    }
    int distCol = Math.abs(this.col - targetCol);
    int distRow = Math.abs(this.row - targetRow);
    if ((distCol == 0) == (distRow == 0)) {
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
    // traverses left
    result.addAll(super.simulateAttacks(-1, 0, board));
    // traverses right
    result.addAll(super.simulateAttacks(1, 0, board));
    // traverses up
    result.addAll(super.simulateAttacks(0, 1, board));
    // traverses down
    result.addAll(super.simulateAttacks(0, -1, board));
    return result;
  }

  @Override
  public List<Move> generateAllPossibleMoves(IBoard board) {
    List<Move> allMoves = new ArrayList<Move>();

    allMoves.addAll(super.generateMovesWithPattern(-1, 0, board));
    allMoves.addAll(super.generateMovesWithPattern(1, 0, board));
    allMoves.addAll(super.generateMovesWithPattern(0, 1, board));
    allMoves.addAll(super.generateMovesWithPattern(0, -1, board));

    return allMoves;
  }

  @Override
  public IPiece copy() {
    return new Rook(this.getTeam(), this.getCol(), this.getRow());
  }

}
