package model;

import model.board.StandardBoard;
import model.pieces.IPiece;
import model.pieces.Pawn;
import model.pieces.PieceType;
import model.pieces.Queen;
import model.players.IPlayer;

/**
 * Created by danielchu on 12/31/16.
 */

/**
 * Chess mini-game, where Player 1 has 8 pawns, and Player 2 has a single queen.
 *
 * OBJECTIVES: Player 1 - Reach the other side of the board with any pawn or take the queen to win.
 * Player 2 - Take all pawns to win.
 *
 * pieces follow standard chess rules.
 */
public class PawnRush extends AChessGame {

  public PawnRush(IPlayer p1, IPlayer p2) {
    super(p1, p2);
    this.setupBoard();
  }

  /**
   * Sets up the board for pawn rush.
   */
  private void setupBoard() {
    this.board = new StandardBoard();
    int row = 1;
    // sets up pawns
    for (int col = 0; col < this.board.getWidth(); col++) {
      this.board.addPiece(new Pawn(p1.getTeam(), col, row, true), col, row);
    }
    // sets up the queen
    this.board.addPiece(new Queen(p2.getTeam(), 3, 7), 3, 7);
  }


  @Override
  public int isGameOver() {
    // checks if any pawns have reached the other side
    for (int col = 0; col < this.board.getWidth(); col++) {
      IPiece curPiece = this.board.getPieceAt(col, 7);
      if (curPiece != null) {
        if (curPiece.getType() == PieceType.PAWN) {
          return 1;
        }
      }
    }
    // checks if the queen has been taken, or all pawns have been taken
    int numQueen = 0;
    int numPawn = 0;
    for (int col = 0; col < this.board.getWidth(); col++) {
      for (int row = 0; row < this.board.getHeight(); row++) {
        IPiece curPiece = this.board.getPieceAt(col, row);
        if (curPiece != null) {
          if (curPiece.getType() == PieceType.PAWN) {
            numPawn++;
          } else if (curPiece.getType() == PieceType.QUEEN) {
            numQueen++;
          }
        }
      }
    }
    // if there are 0 queens left, player 1 has won
    if (numQueen == 0) {
      return 1;
    }
    // if there are 0 pawns left, player 2 has won
    if (numPawn == 0) {
      return 2;
    }
    // otherwise, the game is still in progress
    return 0;
  }
}
