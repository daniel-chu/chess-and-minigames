package model.Pieces;

import model.Board.IBoard;
import model.Players.Team;

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
  PieceType type;

  /**
   * Constructor for a piece.
   *
   * @param team the team of this piece
   * @param col  the column this bishop will be at
   * @param row  the row this bishop will be at
   */
  public APiece(Team team, int col, int row, PieceType type) {
    if ((col < 0 || col > 7) || (row < 0 || row > 7)) {
      throw new IllegalArgumentException("Piece's column and row must range from 0 to 7.");
    }
    this.team = team;
    this.col = col;
    this.row = row;
    this.type = type;
  }

  /**
   * Checks if this piece can move to the specified location.
   *
   * @param targetCol   the column to move to
   * @param targetRow   the row to move to
   * @param board the board representing where all pieces are
   * @return if we can move the piece to that spot
   */
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    IPiece currentPiece = board.getPieceAt(targetCol, targetRow);
    if(currentPiece != null) {
      if(currentPiece.getTeam() == this.team) {
        return false;
      }
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
}
