package model.board;

import java.util.ArrayList;
import java.util.List;

import model.pieces.IPiece;

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
    if (colWidth > 24 || rowHeight > 24) {
      throw new IllegalArgumentException("Board cannot be greater than 24 x 24");
    }
    this.board = new IPiece[colWidth][rowHeight];
  }

  @Override
  public void addPiece(IPiece piece, int col, int row) throws IllegalArgumentException {
    if (col >= this.getWidth() || row >= this.getHeight() || col < 0 || row < 0) {
      throw new IllegalArgumentException("Invalid coordinates.");
    }
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
    try {
      IPiece result = this.board[col][row];
      return result;
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }

  @Override
  public int getWidth() {
    return this.board.length;
  }

  @Override
  public IPiece movePieceFromTo(int fromCol, int fromRow, int targetCol, int targetRow) {
    this.board[fromCol][fromRow].moveTo(targetCol, targetRow);
    IPiece takenPiece = this.board[targetCol][targetRow];
    this.board[targetCol][targetRow] = this.board[fromCol][fromRow];
    this.board[fromCol][fromRow] = null;
    return takenPiece;
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
    if (col < 0 || (col > this.getWidth() - 1) || row < 0 || (row > this.getHeight() - 1)) {
      return false;
    }
    return true;
  }

  @Override
  public List<IPiece> getAllPiecesOnBoard() {
    List<IPiece> allPieces = new ArrayList<IPiece>();
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        if(this.board[col][row] != null) {
          allPieces.add(this.board[col][row]);
        }
      }
    }
    return allPieces;
  }

  @Override
  public abstract IBoard deepCopy();
}
