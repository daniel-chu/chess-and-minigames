package controller;

import java.util.Scanner;

import model.IChessGameModel;
import view.IChessGameView;

/**
 * Created by danielchu on 12/30/16.
 */
public class BasicController implements IChessController {

  private IChessGameModel model;
  private IChessGameView view;

  public BasicController(IChessGameModel model, IChessGameView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() {
    // initial render
    this.view.setBoard(model.getBoard(), model.whosTurn());
    this.view.render();
    // creates scanner
    Scanner scanner = new Scanner(System.in);
    while (!model.isGameOver()) {
      // player makes their turn
      try {
        // parses input
        String from = scanner.next();
        String target = scanner.next();
        int fromCol = this.parseColumnLabel(from);
        int fromRow = Integer.parseInt(from.substring(1)) - 1;
        int targetCol = this.parseColumnLabel(target);
        int targetRow = Integer.parseInt(target.substring(1)) - 1;

        // makes the move and updates the view
        this.model.movePiece(fromCol, fromRow, targetCol, targetRow);
        this.view.setBoard(model.getBoard(), model.whosTurn());
        this.view.render();
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }

  }

  /**
   * Turns a column label (i.e. "a", "b" ...).
   *
   * @param column the column label
   * @throws IllegalArgumentException if the column is invalid
   */
  private int parseColumnLabel(String column) throws IllegalArgumentException {
    char letter = column.toLowerCase().charAt(0);
    int code = (int) letter - 97;
    if (code < 0 || code > this.model.getBoard().length) {
      throw new IllegalArgumentException("Invalid column.");
    }
    return code;
  }
}
