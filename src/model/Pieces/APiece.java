package model.Pieces;

import java.awt.*;

/**
 * Created by danielchu on 12/30/16.
 */

/**
 * Abstract class representing a piece.
 */
public abstract class APiece implements IPiece {
  /**
   * Color/team of this piece.
   */
  Color team;

  /**
   * The column this piece is at.
   */
  int col;

  /**
   * The row this piece is at.
   */
  int row;

  /**
   * Constructor for a piece.
   *
   * @param team the color/team of this piece
   * @param col  the column this bishop will be at
   * @param row  the row this bishop will be at
   */
  public APiece(Color team, int col, int row) {
    if ((col < 0 || col > 7) || (row < 0 || row > 7)) {
      throw new IllegalArgumentException("Piece's column and row must range from 0 to 7.");
    }
    this.team = team;
    this.col = col;
    this.row = row;
  }

  /**
   * Checks if this piece can move to the specified location.
   *
   * @param col   the column to move to
   * @param row   the row to move to
   * @param board the board representing where all pieces are
   * @return if we can move the piece to that spot
   */
  public abstract boolean validMove(int col, int row, IPiece[][] board);

  /**
   * Moves the piece to the specified location.
   *
   * @param col the column to move to
   * @param row the row to move to
   */
  public void moveTo(int col, int row) {
    this.col = col;
    this.row = row;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public int getRow() {
    return this.row;
  }
}
