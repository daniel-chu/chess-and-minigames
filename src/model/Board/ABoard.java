package model.Board;

import model.Pieces.IPiece;

/**
 * Created by danielchu on 12/30/16.
 */
public abstract class ABoard implements IBoard {

  /**
   * Represents the chess board. First [] is the column, second [] is the row.
   */
  protected IPiece[][] board;

  /**
   * Constructor for a board.
   */
  public ABoard(int colWidth, int rowHeight) {
    this.board = new IPiece[colWidth][rowHeight];
  }

  @Override
  public void addPiece(IPiece piece, int col, int row) {
    if (this.board[col][row] == null) {
      this.board[col][row] = piece;
    } else {
      throw new IllegalArgumentException("Piece already in that spot.");
    }
  }

  @Override
  public void removePiece(int col, int row) {
    this.board[col][row] = null;
  }

  @Override
  public IPiece getPieceAt(int col, int row) {
    return this.board[col][row];
  }

  @Override
  public int getWidth() {
    return this.board.length;
  }

  @Override
  public void movePieceFromTo(int fromCol, int fromRow, int targetCol, int targetRow) {
    this.board[fromCol][fromRow].moveTo(targetCol, targetRow);
    this.board[targetCol][targetRow] = this.board[fromCol][fromRow];
    this.board[fromCol][fromRow] = null;
  }

  @Override
  public int getHeight() {
    if (board.length > 0) {
      return this.board[0].length;
    }
    return 0;
  }

  @Override
  public boolean validCoordinates(int col, int row) {
    if(col < 0 || (col > this.getWidth() - 1) || row < 0 || (row > this.getHeight() - 1)) {
      return false;
    }
    return true;
  }
}
