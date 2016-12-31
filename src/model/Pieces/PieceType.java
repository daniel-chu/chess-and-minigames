package model.Pieces;

/**
 * Created by danielchu on 12/30/16.
 */
public enum PieceType {
  PAWN(1), ROOK(5), BISHOP(3), KNIGHT(3), QUEEN(9), KING(0), EMPTY(-1);

  /**
   * Value of this type of piece.
   */
  int value;

  /**
   * Constructor for a PieceType.
   *
   * @param value value of the piece
   */
  PieceType(int value) {
    this.value = value;
  }

  /**
   * Returns the value of this piece type.
   *
   * @return the value of this piece
   */
  public int getValue() {
    return this.value;
  }
}
