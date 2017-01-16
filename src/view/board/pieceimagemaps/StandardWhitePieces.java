package view.board.pieceimagemaps;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.pieces.PieceType;

/**
 * Created by danielchu on 1/15/17.
 */

/**
 * Class that holds a map containing standard white pieces.
 */
public class StandardWhitePieces implements IPieceImageMaps {
  private Map<PieceType, ImageIcon> pieceMap;

  /**
   * Constructor that creates the map.
   */
  public StandardWhitePieces() {
    this.pieceMap = new HashMap<PieceType, ImageIcon>();
    this.pieceMap.put(PieceType.PAWN, new ImageIcon("resources/pieceSprites/whitepawn.png"));
    this.pieceMap.put(PieceType.ROOK, new ImageIcon("resources/pieceSprites/whiterook.png"));
    this.pieceMap.put(PieceType.KNIGHT, new ImageIcon("resources/pieceSprites/whiteknight.png"));
    this.pieceMap.put(PieceType.BISHOP, new ImageIcon("resources/pieceSprites/whitebishop.png"));
    this.pieceMap.put(PieceType.QUEEN, new ImageIcon("resources/pieceSprites/whitequeen.png"));
    this.pieceMap.put(PieceType.KING, new ImageIcon("resources/pieceSprites/whiteking.png"));
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
