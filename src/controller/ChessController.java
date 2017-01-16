package controller;

import model.IChessGameModel;
import view.IChessGameView;

/**
 * Created by danielchu on 12/30/16.
 */

/**
 * Controller for a chess game.
 */
public class ChessController implements IChessController {

  /**
   * The model we are using.
   */
  private IChessGameModel model;

  /**
   * The view we are using.
   */
  private IChessGameView view;

  /**
   * Constructor for the controller.
   *
   * @param model The model we are using
   * @param view The view we are using
   */
  public ChessController(IChessGameModel model, IChessGameView view) {
    this.model = model;
    this.view = view;
    this.updateView();
  }

  @Override
  public void run() {
    this.view.initialize();
  }

  /**
   * Sets the fields of the view and updates it.
   */
  private void updateView() {
    this.view.setBoard(this.model.getBoard(), this.model.whosTurn());
    this.view.update();
  }

}
