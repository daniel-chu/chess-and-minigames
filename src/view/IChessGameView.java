package view;

import model.pieces.PieceInfo;
import model.players.Team;

/**
 * Created by danielchu on 12/30/16.
 */
public interface IChessGameView {

  /**
   * Initializes the view after it is fully constructed.
   */
  void initialize();

  /**
   * Sets the information that the view needs to render the game.
   *
   * @param board         a 2d array of PieceInfos that represents the chess game's board
   * @param currentPlayer the team who's turn it currently is
   */
  void setInfo(PieceInfo[][] board, Team currentPlayer);

  /**
   * Updates/renders the board.
   */
  void update();

}
