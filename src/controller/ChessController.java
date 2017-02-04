package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.handlers.KeyHandler;
import controller.handlers.MouseHandler;
import model.IChessGameModel;
import view.IGuiView;
import view.IViewButtonListeners;

/**
 * Created by danielchu on 12/30/16.
 */

/**
 * Controller for a chess game.
 */
public class ChessController implements IChessController, IViewButtonListeners {

  /**
   * The model we are using.
   */
  private IChessGameModel model;

  /**
   * The view we are using.
   */
  private IGuiView view;

  /**
   * The mouse handler for the chess game.
   */
  private MouseHandler mouseHandler;

  /**
   * Constructor for the controller.
   *
   * @param model The model we are using
   * @param view The view we are using
   */
  public ChessController(IChessGameModel model, IGuiView view) {
    this.model = model;
    this.view = view;
    this.mouseHandler = this.createMouseHandler();
    this.view.addButtonsAndListeners(this);
    this.updateView();
    this.view.updateModelDependentAttributes();
  }

  @Override
  public void run() {
    this.view.initialize();
  }

  public MouseHandler createMouseHandler() {
    MouseHandler result = new MouseHandler();
    // adds in a mouse clicked event for when the user clicks on the panel
    result.addMouseClicked(MouseEvent.BUTTON1, () -> {
      // TODO implement mouse controls
    });
    return result;
  }

  public KeyHandler createKeyHandler() {
    KeyHandler result = new KeyHandler();
    // TODO implement keyboard controls
    return result;
  }

  /**
   * Sets the fields of the view and updates it.
   */
  private void updateView() {
    this.view.setBoard(this.model.getBoard(), this.model.whosTurn());
    this.view.update();
  }

  @Override
  public ActionListener getMoveListener() {
    ActionListener move = (ActionEvent e) -> {
      String moveString = this.view.getMoveString();
      this.view.clearInputString();
      Scanner scanner = new Scanner(moveString);
      // parses input
      try {
        String from = scanner.next();
        String target = scanner.next();
        int fromCol = this.parseColumnLabel(from);
        int fromRow = Integer.parseInt(from.substring(1)) - 1;
        int targetCol = this.parseColumnLabel(target);
        int targetRow = Integer.parseInt(target.substring(1)) - 1;

        // makes the move and updates the view
        this.model.movePiece(fromCol, fromRow, targetCol, targetRow);
        this.view.setBoard(model.getBoard(), model.whosTurn());
        this.view.update();
      } catch (NoSuchElementException err) {
        System.out.println("Invalid input");
      } catch (IllegalArgumentException err2) {
        System.out.println("Invalid move");
      }
    };
    return move;
  }

  /**
   * Turns a column label (i.e. "a", "b" ...) into the col index.
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
