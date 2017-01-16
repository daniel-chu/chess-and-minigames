package view.board.pieceimagemaps;

import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.pieces.PieceType;

/**
 * Created by danielchu on 1/15/17.
 */

/**
 * Class that holds a map containing images for standard black pieces.
 */
public class StandardBlackPieces implements IPieceImageMaps{
  private Map<PieceType, ImageIcon> pieceMap;

  /**
   * Constructor that creates the map.
   */
  public StandardBlackPieces() {
    this.pieceMap = new HashMap<PieceType, ImageIcon>();
    this.pieceMap.put(PieceType.PAWN, new ImageIcon("resources/pieceSprites/blackpawn.png"));
    this.pieceMap.put(PieceType.ROOK, new ImageIcon("resources/pieceSprites/blackrook.png"));
    this.pieceMap.put(PieceType.KNIGHT, new ImageIcon("resources/pieceSprites/blackknight.png"));
    this.pieceMap.put(PieceType.BISHOP, new ImageIcon("resources/pieceSprites/blackbishop.png"));
    this.pieceMap.put(PieceType.QUEEN, new ImageIcon("resources/pieceSprites/blackqueen.png"));
    this.pieceMap.put(PieceType.KING, new ImageIcon("resources/pieceSprites/blackking.png"));
  }

  /**
   * Gets the image based on the piece type.
   *
   * @param piece piece type
   * @return the image representing the piece type
   */
  public ImageIcon getImage(PieceType piece) {
    return this.pieceMap.get(piece);
  }
}
