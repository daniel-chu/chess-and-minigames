package view.board.pieceimagemaps;

import javax.swing.*;

import model.pieces.PieceType;

/**
 * Created by danielchu on 1/15/17.
 */
public interface IPieceImageMaps {
  /**
   * Gets the image based on the piece type.
   *
   * @param piece piece type
   * @return the image representing the piece type
   */
  ImageIcon getImage(PieceType piece);
}
