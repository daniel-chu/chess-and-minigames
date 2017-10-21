package model.pieces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Move;
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
    // TODO check that king is not in check after move, maybe with guarded by method (king does
    // not have this because it does not need to be guarded as it cant be taken. also prevents
    // infinite loop)
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
    List<Move> movesKingCanTake = new ArrayList<Move>();

    addMoveIfKingIsAbleToMoveTo(movesKingCanTake, col + 1, row, board);
    addMoveIfKingIsAbleToMoveTo(movesKingCanTake, col, row - 1, board);
    addMoveIfKingIsAbleToMoveTo(movesKingCanTake, col, row + 1, board);
    addMoveIfKingIsAbleToMoveTo(movesKingCanTake, col - 1, row, board);

    for(Move move : movesKingCanTake) {
      IPiece target = board.getPieceAt(move.getToCol(), move.getToRow());
      if ((target != null) && (target.getTeam() != team)) {
        result.add(target);
      }
    }

    return result;
  }

  private void addMoveIfKingIsAbleToMoveTo(List<Move> moves, int targetCol, int targetRow, IBoard
          board) {
    if (board.validCoordinates(targetCol, targetRow) && validMove(targetCol, targetRow, board)) {
      moves.add(new Move(team, col, row, targetCol, targetRow));
    }
  }

  @Override
  public List<Move> generateAllPossibleMoves(IBoard board) {
    List<Move> allMoves = new ArrayList<Move>();

    addMoveIfKingIsAbleToMoveTo(allMoves, col + 1, row, board);
    addMoveIfKingIsAbleToMoveTo(allMoves, col, row - 1, board);
    addMoveIfKingIsAbleToMoveTo(allMoves, col, row + 1, board);
    addMoveIfKingIsAbleToMoveTo(allMoves, col - 1, row, board);

    return allMoves;
  }

  @Override
  public IPiece copy() {
    return new King(this.getTeam(), this.getCol(), this.getRow());
  }
}
