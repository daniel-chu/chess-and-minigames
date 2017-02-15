package model.pieces;

/**
 * Created by danielchu on 12/30/16.
 */
public enum PieceType {
  PAWN(1, "P"), ROOK(5, "R"), BISHOP(3, "B"), KNIGHT(3, "K"), QUEEN(9, "Q"), KING(0, "K"), EMPTY
          (-1, " ");

  /**
   * Value of this type of piece.
   */
  int value;

  /**
   * String representing the type
   */
  String typeString;

  /**
   * Constructor for a PieceType.
   *
   * @param value value of the piece
   */
  PieceType(int value, String typeString) {
    this.value = value;
    this.typeString = typeString;
  }

  /**
   * Returns the value of this piece type.
   *
   * @return the value of this piece
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Returns the string representation of this piece's type.
   */
  public String toString() {
    return this.typeString;
  }
}
