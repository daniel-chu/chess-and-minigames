package view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import controller.handlers.KeyHandler;
import controller.handlers.MouseHandler;
import model.pieces.PieceInfo;
import view.board.GamePanel;

/**
 * Created by danielchu on 1/15/17.
 */
public class MainView extends JFrame implements IGuiView {

  /**
   * Panel that holds the rendering of the board.
   */
  private GamePanel boardPanel;

  /**
   * Panel that holds the text input with moves.
   */
  private JPanel inputPanel;

  /**
   * The current player.
   */
  private int currentPlayer;

  /**
   * The input field.
   */
  private JTextField inputField;

  public MainView() {
    super();

    this.setTitle("Chess");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.boardPanel = new GamePanel();
    this.inputPanel = this.createInputPanel();

    this.add(boardPanel, BorderLayout.CENTER);
    this.add(inputPanel, BorderLayout.SOUTH);
  }

  private JPanel createInputPanel() {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Input move: ");
    JTextField inputField = new JTextField();
    this.inputField = inputField;
    panel.add(label);
    panel.add(inputField);
    return panel;
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

  @Override
  public String getMoveString() {
    return this.inputField.getText();
  }

  @Override
  public void addButtonsAndListeners(IViewButtonListeners buttonListeners) {
    JButton moveButton = new JButton("Move");
    moveButton.addActionListener(buttonListeners.getMoveListener());
    this.inputField.addActionListener(buttonListeners.getMoveListener());
    this.inputPanel.add(moveButton);
  }

  @Override
  public void clearInputString() {
    this.inputField.setText("");
  }

  @Override
  public void updateModelDependentAttributes() {
    this.inputField.setPreferredSize(new Dimension(this.boardPanel.getBoardWidth() - 200, 25));
    this.inputField.revalidate();
  }

  @Override
  public void addKeyListenerToComponents(KeyHandler keyHandler) {
    this.addKeyListener(keyHandler);
    this.boardPanel.addKeyListener(keyHandler);
  }

  @Override
  public void addMouseListenerToComponents(MouseHandler mouseHandler) {
    this.addMouseListener(mouseHandler);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void winScreen(String winner, IViewButtonListeners listeners) {
    JFrame winPopup = new JFrame("GAME OVER");
    JPanel winPanel = new JPanel();

    JButton restart = new JButton("Restart");
    restart.addActionListener(listeners.getRestartListener());
    restart.addActionListener((ActionEvent e) -> winPopup.dispose());

    JButton quit = new JButton("Quit");
    quit.addActionListener((ActionEvent e) -> {
      winPopup.dispose();
      System.exit(0);
    });

    JLabel winText = new JLabel(winner + " won! Restart or quit?");
    winPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(10, 20, 0, 20);
    c.gridwidth = 2;
    c.gridx = 1;
    c.gridy = 1;
    winPanel.add(winText, c);
    c.insets = new Insets(10, 20, 10, 20);
    c.gridwidth = 1;
    c.gridx = 1;
    c.gridy = 2;
    winPanel.add(restart, c);
    c.gridx = 2;
    c.gridy = 2;
    winPanel.add(quit, c);

    winPopup.add(winPanel);
    winPopup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    winPopup.pack();

    Point gameFrameLocation = this.getLocation();
    int mainCenterX = (int) (gameFrameLocation.getX() + this.getWidth() / 2);
    int mainCenterY = (int) (gameFrameLocation.getY() + this.getHeight() / 2);
    int winPopupX = mainCenterX - winPopup.getWidth() / 2;
    int winPopupY = mainCenterY - winPopup.getHeight() / 2;

    winPopup.setLocation(winPopupX, winPopupY);
    winPopup.setVisible(true);
  }
}
