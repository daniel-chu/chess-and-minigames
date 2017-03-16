package model;

import java.util.List;

import model.board.StandardBoard;
import model.pieces.IPiece;
import model.pieces.Pawn;
import model.pieces.PieceType;
import model.pieces.Queen;
import model.players.IPlayer;
import model.players.Team;

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

  @Override
  protected void setupBoard() {
    this.board = new StandardBoard();
    int row = 1;
    // sets up pawns
    for (int col = 0; col < this.board.getWidth(); col++) {
      this.board.addPiece(new Pawn(p1.getTeam(), col, row), col, row);
    }
    // sets up the queen
    this.board.addPiece(new Queen(p2.getTeam(), 3, 7), 3, 7);
  }

  @Override
  public IPiece movePiece(int fromCol, int fromRow, int targetCol, int targetRow) throws
          IllegalArgumentException {
    IPiece takenPiece = super.movePiece(fromCol, fromRow, targetCol, targetRow);
    if (takenPiece != null) {
      // game specific processing in here, or split into own method if necessary
    }
    this.handleTurns();
    return takenPiece;
  }

  @Override
  public boolean willCauseInvalidStateFromCheck(int fromCol, int fromRow, int targetCol, int
          targetRow) {
    // this will never happen in this game
    return false;
  }

  @Override
  public GameStatusCode getGameStatus() {
    // checks if any pawns have reached the other side
    for (int col = 0; col < this.board.getWidth(); col++) {
      IPiece curPiece = this.board.getPieceAt(col, 7);
      if (curPiece != null) {
        if (curPiece.getType() == PieceType.PAWN) {
          return GameStatusCode.TEAM_ONE_WINS;
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
      return GameStatusCode.TEAM_ONE_WINS;
    }
    // if there are 0 pawns left, player 2 has won
    if (numPawn == 0) {
      return GameStatusCode.TEAM_TWO_WINS;
    }
    // otherwise, the game is still in progress
    return GameStatusCode.IN_PROGRESS;
  }

  /**
   * Changes whose turn it is.
   */
  private void handleTurns() {
    if (this.currentPlayer.equals(p1)) {
      this.currentPlayer = p2;
    } else {
      this.currentPlayer = p1;
    }
  }

  @Override
  public void restartGame() {
    super.restartGame();
    this.setupBoard();
  }

  @Override
  public String getGameModeName() {
    return "Pawn Rush";
  }
}
