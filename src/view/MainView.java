package view;

import java.awt.*;

import javax.swing.*;

import model.pieces.PieceInfo;
import view.board.GamePanel;

/**
 * Created by danielchu on 1/15/17.
 */
public class MainView extends JFrame implements IGuiView {

  /**
   * Panel that holds the rendering of the board.
   */
  GamePanel boardPanel;

  /**
   * The current player
   */
  int currentPlayer;

  public MainView() {
    super();

    this.setTitle("Chess");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.boardPanel = new GamePanel();

    this.add(boardPanel, BorderLayout.CENTER);
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public void setBoard(PieceInfo[][] board, int currentPlayer) {
    this.boardPanel.setBoard(board);
    this.currentPlayer = currentPlayer;
    this.pack();
  }

  @Override
  public void update() {
    this.boardPanel.repaint();
  }
}
