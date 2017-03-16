package model.board;

/**
 * Created by danielchu on 12/30/16.
 */

import java.util.List;

import model.pieces.IPiece;

/**
 * Class that represents a standard chess board that is 8x8.
 */
public class StandardBoard extends ABoard {

  /**
   * Constructor for a standard board.
   */
  public StandardBoard() {
    super(8, 8);
  }

  @Override
  public IBoard deepCopy() {
    IBoard copiedBoard = new StandardBoard();

    List<IPiece> allOriginalPieces = super.getAllPiecesOnBoard();

    for(IPiece piece : allOriginalPieces) {
      IPiece copiedPiece = piece.copy();
      copiedBoard.addPiece(copiedPiece, copiedPiece.getCol(), copiedPiece.getRow());
    }

    return copiedBoard;
  }

}
