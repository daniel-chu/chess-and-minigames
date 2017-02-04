package view.board.pieceimagemaps;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.pieces.PieceType;
import view.board.GamePanel;

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
    this.pieceMap.put(PieceType.PAWN, getAndScaleImage("resources/pieceSprites/whitepawn.png"));
    this.pieceMap.put(PieceType.ROOK, getAndScaleImage("resources/pieceSprites/whiterook.png"));
    this.pieceMap.put(PieceType.KNIGHT, getAndScaleImage("resources/pieceSprites/whiteknight.png"));
    this.pieceMap.put(PieceType.BISHOP, getAndScaleImage("resources/pieceSprites/whitebishop.png"));
    this.pieceMap.put(PieceType.QUEEN, getAndScaleImage("resources/pieceSprites/whitequeen.png"));
    this.pieceMap.put(PieceType.KING, getAndScaleImage("resources/pieceSprites/whiteking.png"));
  }

  /**
   * Gets an image icon from the file path, and scales it to the correct size.
   *
   * @param filePath the fiel path the image icon is at
   * @return the scaled image icon
   */
  private ImageIcon getAndScaleImage(String filePath) {
    ImageIcon orig = new ImageIcon(filePath);
    Image scaledImage = orig.getImage().getScaledInstance(GamePanel.CELL_SIZE, GamePanel
            .CELL_SIZE, Image.SCALE_SMOOTH);
    ImageIcon result = new ImageIcon(scaledImage);
    return result;
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
