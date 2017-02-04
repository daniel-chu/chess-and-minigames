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
    inputField.setPreferredSize(new Dimension(200, 25));
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

  //TODO get this working
  @Override
  public void updateModelDependentAttributes() {
    this.inputField.setPreferredSize(new Dimension(this.boardPanel.getBoardWidth() - 200, 25));
  }

}
