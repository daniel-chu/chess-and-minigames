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

  public static final int CELL_SIZE = 65;

  public static final int LABEL_OFFSET = CELL_SIZE / 3;

  public static final Color DARK_BROWN = new Color(170, 105, 25);

  public static final Color BROWN = new Color(232, 180, 118);

  private final IPieceImageMaps blackPieceImages = new StandardBlackPieces();

  private final IPieceImageMaps whitePieceImages = new StandardWhitePieces();

  private int selectedColIndex;

  private int selectedRowIndex;

  /**
   * The 2d array representing the game board.
   */
  private PieceInfo[][] board;

  public GamePanel() {
    super();
    this.board = new PieceInfo[0][0];
    this.selectedColIndex = -1;
    this.selectedRowIndex = -1;
  }

  public void setBoard(PieceInfo[][] board) {
    this.board = board;
    this.setPreferredSize(this.findSizes());
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
    highlightCell(g);
    paintPieces(g);
    paintLabels(g);
  }

  /**
   * Highlights the selected cell
   */
  private void highlightCell(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    if (selectedColIndex >= 0 && selectedRowIndex >= 0) {
      g.setColor(Color.RED);
      g2.drawRect(selectedColIndex * CELL_SIZE + LABEL_OFFSET, selectedRowIndex * CELL_SIZE + LABEL_OFFSET,
              CELL_SIZE, CELL_SIZE);
      g.setColor(new Color(255, 0, 0, 120));
      g2.fillRect(selectedColIndex * CELL_SIZE + LABEL_OFFSET, selectedRowIndex * CELL_SIZE + LABEL_OFFSET,
              CELL_SIZE, CELL_SIZE);
    }
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
    g2.setColor(DARK_BROWN);
    if (board.length > 0) {
      int maxRow = this.board[0].length - 1;
      for (int curRow = 0; curRow < board[0].length; curRow++) {
        for (int curCol = curRow % 2; curCol < board.length; curCol += 2) {
          g2.fillRect(((maxRow - curRow) * CELL_SIZE) + LABEL_OFFSET, (curCol * CELL_SIZE) +
                  LABEL_OFFSET, CELL_SIZE, CELL_SIZE);
        }
      }
      g2.setColor(BROWN);
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

  /**
   * Highlights (or unhighlights if already highlighted) the clicked cell, as well as returns the
   * string representing it. If unhighlighting something, the string is empty.
   *
   * @param x the x coordinate of the click
   * @param y the y coordinate of the click
   * @return the string representing the clicked cell. Empty string if unhighlighting
   */
  public String selectOrDeselectAndGetCell(int x, int y) {
    int newCol = this.findClickedColumn(x);
    int newRow = this.findClickedRow(y);
    if (selectedColIndex == newCol && selectedRowIndex == newRow) {
      selectedColIndex = -1;
      selectedRowIndex = -1;
      this.repaint();
      return "";
    }
    selectedColIndex = newCol;
    selectedRowIndex = newRow;
    this.repaint();
    String result = Character.toString((char) (selectedColIndex + 65))
            + Integer.toString(board[0].length - selectedRowIndex);
    return result;
  }

  /**
   * Finds the column we clicked on based on the given x coordinate. If it is under the minimum,
   * return the minimum. If it is over the max, return the max.
   *
   * @param x the x coordinate in pixels of where the click was
   */
  private int findClickedColumn(int x) {
    int result = (x - LABEL_OFFSET) / CELL_SIZE;
    if(result > board.length - 1) {
      return board.length - 1;
    } else if(result < 0) {
      return 0;
    }
    return result;
  }

  /**
   * Finds the row we clicked on based on the given y coordinate. If it is under the minimum,
   * return the minimum. If it is over the max, return the max.
   *
   * @param y the y coordinate in pixels of where the click was
   */
  private int findClickedRow(int y) {
    int result = (y - LABEL_OFFSET) / CELL_SIZE;
    if(result > board[0].length - 1) {
      return board[0].length - 1;
    } else if(result < 0) {
      return 0;
    }
    return result;
  }

  /**
   * Resets the selected col and row.
   */
  public void resetSelectedColAndRow() {
    selectedColIndex = -1;
    selectedRowIndex = -1;
  }
}
