package view;

import model.pieces.PieceInfo;

/**
 * Created by danielchu on 12/30/16.
 */
public interface IChessGameView {

  /**
   * Initializes the view after it is fully constructed.
   */
  void initialize();

  /**
   * Sets the board that this view will use to render the game.
   *
   * @param board         a 2d array of PieceInfos that represents the chess game's board
   * @param currentPlayer the player who's turn it currently is
   */
  void setBoard(PieceInfo[][] board, int currentPlayer);

  /**
   * Updates/renders the board.
   */
  void update();

}
