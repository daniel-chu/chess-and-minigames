package view.board;

import java.awt.*;

import javax.swing.*;

import model.pieces.PieceInfo;
import model.players.Team;
import view.board.pieceimagemaps.IPieceImageMaps;
import view.board.pieceimagemaps.StandardBlackPieces;
import view.board.pieceimagemaps.StandardWhitePieces;

/**
 * Created by danielchu on 1/15/17.
 */

/**
 * Panel containing the rendered board and pieces.
 */
public class GamePanel extends JPanel {

  public static final int CELL_SIZE = 60;

  public static final int LABEL_OFFSET = CELL_SIZE / 3;

  public static final Color BROWN = new Color(170, 105, 25);

  public static final Color DARK_BROWN = new Color(232, 180, 118);

  private final IPieceImageMaps blackPieceImages = new StandardBlackPieces();

  private final IPieceImageMaps whitePieceImages = new StandardWhitePieces();

  /**
   * The 2d array representing the game board.
   */
  private PieceInfo[][] board;

  public GamePanel() {
    super();
    this.board = new PieceInfo[0][0];
  }

  public void setBoard(PieceInfo[][] board) {
    this.board = board;
    this.setPreferredSize(this.findSizes());
    this.revalidate();
    this.invalidate();
  }

  public Dimension findSizes() {
    int width = 0;
    int height = 0;
    if (this.board.length > 0) {
      width = this.board.length * CELL_SIZE;
      height = this.board[0].length * CELL_SIZE;
    }
    width += LABEL_OFFSET * 2;
    height += LABEL_OFFSET * 2;
    return new Dimension(width, height);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintBoard(g);
    paintPieces(g);
    paintLabels(g);
  }

  private void paintLabels(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    for (int i = 65; i < 73; i++) {
      g2.drawString(Character.toString((char) i), ((i - 65) * CELL_SIZE) + LABEL_OFFSET +
              CELL_SIZE / 2 - 5, LABEL_OFFSET * 2 / 3);
      g2.drawString(Character.toString((char) i), ((i - 65) * CELL_SIZE) + LABEL_OFFSET +
              CELL_SIZE / 2 - 5, (this.board[0].length * CELL_SIZE) + LABEL_OFFSET * 3 / 2 + 5);
    }
    for (int i = 8; i > 0; i--) {
      g2.drawString(Integer.toString(i), LABEL_OFFSET / 3, (9 - i) * CELL_SIZE - 5);
      g2.drawString(Integer.toString(i), (this.board.length * CELL_SIZE) + LABEL_OFFSET * 3 / 2 - 5,
              (9 - i) * CELL_SIZE - 5);
    }
  }

  /**
   * Renders the board.
   */
  private void paintBoard(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(BROWN);
    if (board.length > 0) {
      int maxRow = this.board[0].length - 1;
      for (int curRow = 0; curRow < board[0].length; curRow++) {
        for (int curCol = curRow % 2; curCol < board.length; curCol += 2) {
          g2.fillRect(((maxRow - curRow) * CELL_SIZE) + LABEL_OFFSET, (curCol * CELL_SIZE) +
                  LABEL_OFFSET, CELL_SIZE, CELL_SIZE);
        }
      }
      g2.setColor(DARK_BROWN);
      for (int curRow = 0; curRow < board[0].length; curRow++) {
        for (int curCol = (curRow + 1) % 2; curCol < board.length; curCol += 2) {
          g2.fillRect(((maxRow - curRow) * CELL_SIZE) + LABEL_OFFSET, (curCol * CELL_SIZE) +
                  LABEL_OFFSET, CELL_SIZE, CELL_SIZE);
        }
      }
    }
  }

  /**
   * Renders all pieces.
   */
  private void paintPieces(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    if (board.length > 0) {
      int maxRow = this.board[0].length - 1;
      for (int row = 0; row < this.board[0].length; row++) {
        for (int col = 0; col < this.board.length; col++) {
          PieceInfo piece = this.board[col][row];
          if (piece.getTeam() == Team.ONE) {
            ImageIcon pieceImage = this.whitePieceImages.getImage(piece.getType());
            pieceImage.paintIcon(this, g, (col * CELL_SIZE) + LABEL_OFFSET, ((maxRow - row) *
                    CELL_SIZE) + LABEL_OFFSET);
          } else if (piece.getTeam() == Team.TWO) {
            ImageIcon pieceImage = this.blackPieceImages.getImage(piece.getType());
            pieceImage.paintIcon(this, g, (col * CELL_SIZE) + LABEL_OFFSET, ((maxRow - row) *
                    CELL_SIZE) + LABEL_OFFSET);
          }
        }
      }
    }
  }

  /**
   * Gets how wide the board is in pixels.
   *
   * @return the width of the board in pixels.
   */
  public int getBoardWidth() {
    return board.length * CELL_SIZE;
  }

}
