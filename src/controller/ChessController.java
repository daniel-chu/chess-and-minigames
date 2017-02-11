package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
   * The key handler for the chess game.
   */
  private KeyHandler keyHandler;

  /**
   * Constructor for the controller.
   *
   * @param model The model we are using
   * @param view  The view we are using
   */
  public ChessController(IChessGameModel model, IGuiView view) {
    this.model = model;
    this.view = view;
    this.mouseHandler = this.createMouseHandler();
    this.keyHandler = this.createKeyHandler();
    this.view.addButtonsAndListeners(this);
    this.view.addKeyListenerToComponents(keyHandler);
    this.view.addMouseListenerToComponents(mouseHandler);
    this.updateView();
    this.view.updateModelDependentAttributes();
  }

  @Override
  public void run() {
    this.view.initialize();
  }

  public MouseHandler createMouseHandler() {
    //TODO implement mouse controls
    MouseHandler result = new MouseHandler();
    // adds in a mouse clicked event for when the user clicks on the panel
    result.addMouseClicked(MouseEvent.BUTTON1, () -> {
      String previousCell = this.view.getCurrentSelected();
      this.view.selectCell(result.getMouseEvent().getX(), result.getMouseEvent().getY());
      String newCell = this.view.getCurrentSelected();

      System.out.println(previousCell);
      System.out.println(newCell);
      // TODO based on previous/new cell, move piece

      this.view.resetFocus();
    });
    return result;
  }

  public KeyHandler createKeyHandler() {
    KeyHandler result = new KeyHandler();
    result.addKeyPressed(KeyEvent.VK_R, () -> {
      this.restartGame();
    });
    // TODO implement keyboard controls
    return result;
  }

  /**
   * Sets the fields of the view and updates it.
   */
  private void updateView() {
    this.view.setInfo(this.model.getBoard(), this.model.whosTurn());
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
        this.view.setStatusMessage("");
        this.view.setInfo(model.getBoard(), model.whosTurn());
        this.view.update();
        this.checkIfWin();
      } catch (NoSuchElementException err) {
        this.view.setStatusMessage("Invalid Input!");
      } catch (NumberFormatException err2) {
        this.view.setStatusMessage("Invalid Input!");
      } catch (IllegalArgumentException err3) {
        this.view.setStatusMessage(err3.getMessage());
      }
    };
    return move;
  }

  @Override
  public ActionListener getRestartListener() {
    ActionListener restart = (ActionEvent e) -> {
      this.restartGame();
    };
    return restart;
  }

  /**
   * Checks if someone won the game, and tells the view to display a win screen if so.
   */
  private void checkIfWin() {
    int winnerCode = this.model.isGameOver();
    if (winnerCode == 1) {
      this.view.winScreen("Player 1", this);
    } else if (winnerCode == 2) {
      this.view.winScreen("Player 2", this);
    }
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

  /**
   * Method to restart the game, abstracted here for reuse.
   */
  private void restartGame() {
    this.view.resetFocus();
    this.model.restartGame();
    this.view.clearInputString();
    this.updateView();
  }
}
