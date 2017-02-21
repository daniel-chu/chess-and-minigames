package model.pieces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import model.board.IBoard;
import model.players.Team;

/**
 * Created by danielchu on 12/30/16.
 */

/**
 * Abstract class representing a piece.
 */
public abstract class APiece implements IPiece {
  /**
   * Team of this piece.
   */
  protected final Team team;

  /**
   * The column this piece is at.
   */
  protected int col;

  /**
   * The row this piece is at.
   */
  protected int row;

  /**
   * The type this piece is.
   */
  protected final PieceType type;

  /**
   * Constructor for a piece.
   *
   * @param team the team of this piece
   * @param col  the column this bishop will be at
   * @param row  the row this bishop will be at
   */
  public APiece(Team team, int col, int row, PieceType type) {
    if (col < 0 || row < 0) {
      throw new IllegalArgumentException("Piece's column and row must be greater than 0.");
    }
    this.team = team;
    this.col = col;
    this.row = row;
    this.type = type;
  }

  /**
   * Checks if this piece can move to the specified location.
   *
   * @param targetCol the column to move to
   * @param targetRow the row to move to
   * @param board     the board representing where all pieces are
   * @return if we can move the piece to that spot
   */
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    // ensures the piece is not trying to move on top of its own team's piece
    IPiece currentPiece = board.getPieceAt(targetCol, targetRow);
    if (currentPiece != null) {
      if (currentPiece.getTeam() == this.team) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the path is clear from where this piece is, to where the piece will be moving.
   * Only applies to pieces that must have a clear path to move (ex. not applicable to Knights)
   *
   * Only runs after we have already checked that the target is a valid target square (runs at
   * the end of validMove).
   *
   * @param targetCol the target column
   * @param targetRow the target row
   * @param board     the board we are checking with
   * @return true if the path up til the target is clear, false if blocked
   */
  protected boolean pathFree(int targetCol, int targetRow, IBoard board) {
    // total distances from here to the target
    int xDist = targetCol - this.col;
    int yDist = targetRow - this.row;
    // how far each step will be
    int steppingDistX = 0;
    int steppingDistY = 0;
    if (xDist != 0) {
      steppingDistX = xDist / Math.abs(xDist);
    }
    if (yDist != 0) {
      steppingDistY = yDist / Math.abs(yDist);
    }
    // the temporary coordinates of the piece as it follows the path from source to target
    int tempCol = this.col + steppingDistX;
    int tempRow = this.row + steppingDistY;
    // makes sure the path is clear
    while ((tempCol != targetCol) || (tempRow != targetRow)) {
      if (board.getPieceAt(tempCol, tempRow) != null) {
        return false;
      }
      tempCol += steppingDistX;
      tempRow += steppingDistY;
    }
    return true;
  }

  /**
   * Moves the piece to the specified location.
   *
   * @param targetCol the column to move to
   * @param targetRow the row to move to
   */
  public void moveTo(int targetCol, int targetRow) {
    this.col = targetCol;
    this.row = targetRow;
  }

  public Set<IPiece> canBeTakenBy(IBoard board) {
    Set<IPiece> result = new HashSet<IPiece>();
    for (int col = 0; col < board.getWidth(); col++) {
      for (int row = 0; row < board.getHeight(); row++) {
        IPiece curPiece = board.getPieceAt(col, row);
        if (curPiece != null && curPiece.getTeam() != this.getTeam()) {
          if (curPiece.canTakeThese(board).contains(this)) {
            result.add(curPiece);
          }
        }
      }
    }
    return result;
  }

  public abstract Set<IPiece> canTakeThese(IBoard board);

  /**
   * Simulates moving in a certain direction by the given column increment and row increment until
   * that pattern is no longer a valid move. Pieces that are able to be taken are added to a
   * list, and then that list is returned.
   *
   * @param curCol  current column this piece is simulated to be on
   * @param curRow  current row this piece is simulated to be on
   * @param colIncr how many columns this piece will move
   * @param rowIncr how many rows this piece will move
   * @param board   the board we are checking
   * @return a list of all pieces that can be taken by following this movement pattern
   */
  protected List<IPiece> simulateAttacks(int curCol, int curRow, int colIncr, int rowIncr,
                                         IBoard board) {
    List<IPiece> result = new ArrayList<IPiece>();
    while (board.validCoordinates(curCol, curRow) && this.validMove(curCol, curRow, board)) {
      IPiece target = board.getPieceAt(curCol, curRow);
      if ((target != null) && (target.getTeam() != this.team)) {
        result.add(target);
      }
      curCol += colIncr;
      curRow += rowIncr;
    }
    return result;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public Team getTeam() {
    return this.team;
  }

  @Override
  public PieceType getType() {
    return this.type;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof APiece)) {
      return false;
    }
    APiece that = (APiece) obj;
    return this.team == that.team && this.type == that.type && this.col == that.col && this.row ==
            that.row;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.type, this.team, this.col, this.row);
  }

  @Override
  public String toString() {
    return this.type.toString() + "(" + "col:" + this.col + ",row:" + this.row + ")";
  }
}
