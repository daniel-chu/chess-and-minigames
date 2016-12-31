package model.Pieces;

import java.awt.*;

/**
 * Created by danielchu on 12/30/16.
 */

// TODO implement valid move check

/**
 * Class representing a standard Knight.
 */
public class Knight extends APiece {

  /**
   * Constructor for a Knight piece.
   *
   * @param team the color/team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public Knight(Color team, int col, int row) {
    super(team, col, row);
  }

  @Override
  public boolean validMove(int col, int row, IPiece[][] board) {
    return true;
  }
}
