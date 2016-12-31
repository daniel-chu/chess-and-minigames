package controller;

import model.IChessGameModel;
import view.IChessGameView;

/**
 * Created by danielchu on 12/30/16.
 */
public class ChessController implements IChessController {

  private IChessGameModel model;
  private IChessGameView view;

  public ChessController(IChessGameModel model, IChessGameView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() {

  }
}
