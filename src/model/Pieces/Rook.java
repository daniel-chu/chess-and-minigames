package model.Pieces;

import java.awt.*;

/**
 * Created by danielchu on 12/30/16.
 */

// TODO implement valid move check

/**
 * Class representing a standard Rook.
 */
public class Rook extends APiece {

  /**
   * If this piece has moved or not. Used to check if we can castle with this rook or not.
   */
  boolean hasMoved;

  /**
   * Constructor for a Rook piece.
   *
   * @param team the color/team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public Rook(Color team, int col, int row) {
    super(team, col, row);
    this.hasMoved = false;
  }

  @Override
  public boolean validMove(int col, int row, IPiece[][] board) {
    return true;
  }

  @Override
  public void moveTo(int col, int row) {
    super.moveTo(col, row);
    this.hasMoved = true;
  }
}
