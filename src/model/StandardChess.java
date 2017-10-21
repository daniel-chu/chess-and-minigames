package model;

import java.util.List;

import model.board.StandardBoard;
import model.pieces.*;
import model.players.IPlayer;
import model.players.Team;

/**
 * Created by danielchu on 12/30/16.
 */

// TODO implement castling
// TODO implement stalemate or win conditions

/**
 * Standard chess game.
 */
public class StandardChess extends AChessGame {

  /**
   * Reference to team one's King.
   */
  IPiece teamOneKing;

  /**
   * Reference to team two's King.
   */
  IPiece teamTwoKing;

  /**
   * Constructor for a standard game of chess.
   *
   * @param p1 player one
   * @param p2 player two
   */
  public StandardChess(IPlayer p1, IPlayer p2) {
    super(p1, p2);
    this.setupBoard();
  }

  @Override
  protected void setupBoard() {
    super.setupBoard();
    this.board = new StandardBoard();
    // sets up pawns
    int p1row = 1;
    int p2row = 6;
    for (int col = 0; col < this.board.getWidth(); col++) {
      this.board.addPiece(new Pawn(p1.getTeam(), col, p1row), col, p1row);
      this.board.addPiece(new Pawn(p2.getTeam(), col, p2row), col, p2row);
    }
    p1row = 0;
    p2row = 7;
    // sets up rooks
    int kingSideCol = 7;
    int queenSideCol = 0;
    this.board.addPiece(new Rook(p1.getTeam(), kingSideCol, p1row), kingSideCol, p1row);
    this.board.addPiece(new Rook(p1.getTeam(), queenSideCol, p1row), queenSideCol, p1row);
    this.board.addPiece(new Rook(p2.getTeam(), kingSideCol, p2row), kingSideCol, p2row);
    this.board.addPiece(new Rook(p2.getTeam(), queenSideCol, p2row), queenSideCol, p2row);
    // sets up knights
    kingSideCol = 6;
    queenSideCol = 1;
    this.board.addPiece(new Knight(p1.getTeam(), kingSideCol, p1row), kingSideCol, p1row);
    this.board.addPiece(new Knight(p1.getTeam(), queenSideCol, p1row), queenSideCol, p1row);
    this.board.addPiece(new Knight(p2.getTeam(), kingSideCol, p2row), kingSideCol, p2row);
    this.board.addPiece(new Knight(p2.getTeam(), queenSideCol, p2row), queenSideCol, p2row);
    // sets up bishops
    kingSideCol = 5;
    queenSideCol = 2;
    this.board.addPiece(new Bishop(p1.getTeam(), kingSideCol, p1row), kingSideCol, p1row);
    this.board.addPiece(new Bishop(p1.getTeam(), queenSideCol, p1row), queenSideCol, p1row);
    this.board.addPiece(new Bishop(p2.getTeam(), kingSideCol, p2row), kingSideCol, p2row);
    this.board.addPiece(new Bishop(p2.getTeam(), queenSideCol, p2row), queenSideCol, p2row);
    // sets up queens and kings
    kingSideCol = 4;
    queenSideCol = 3;
    this.teamOneKing = new King(p1.getTeam(), kingSideCol, p1row);
    this.teamTwoKing = new King(p2.getTeam(), kingSideCol, p2row);
    this.board.addPiece(teamOneKing, kingSideCol, p1row);
    this.board.addPiece(new Queen(p1.getTeam(), queenSideCol, p1row), queenSideCol, p1row);
    this.board.addPiece(teamTwoKing, kingSideCol, p2row);
    this.board.addPiece(new Queen(p2.getTeam(), queenSideCol, p2row), queenSideCol, p2row);
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
  public GameStatusCode getGameStatus() {
    if (this.teamOneKing.canBeTakenByPieces(this.board).size() > 0
            || this.teamTwoKing.canBeTakenByPieces(this.board).size() > 0) {
      if (hasTeamCheckmated(Team.ONE)) {
        return GameStatusCode.TEAM_ONE_WINS;
      } else if (hasTeamCheckmated(Team.TWO)) {
        return GameStatusCode.TEAM_TWO_WINS;
      } else {
        return GameStatusCode.CHECK;
      }
    }
    // TODO finish this with stalemate
    return GameStatusCode.IN_PROGRESS;
  }

  private boolean hasTeamCheckmated(Team team) {
    IPiece kingFromTeam = (team == Team.ONE) ? teamOneKing : teamTwoKing;

    if (kingFromTeam.canBeTakenByPieces(this.board).size() > 0) {
      List<Move> allPossibleMoves = super.generateAllPossibleMovesForTeam(team);
      return allPossibleMoves.size() == 0;
    }

    return false;
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

}
