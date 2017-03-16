package model;

import java.util.List;

import model.board.IBoard;
import model.board.StandardBoard;
import model.pieces.Bishop;
import model.pieces.IPiece;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.PieceType;
import model.pieces.Queen;
import model.pieces.Rook;
import model.players.IPlayer;
import model.players.Team;

/**
 * Created by danielchu on 3/15/17.
 */
public class RacingKings extends AChessGame {

  /**
   * Represents if team one's king is at the finish line
   */
  private boolean teamOneKingReachedFinishLine;

  /**
   * Represents if team two's king is at the finish line
   */
  private boolean teamTwoKingReachedFinishLine;

  /**
   * If team two must reach the finish line this turn to end the game with a draw.
   */
  private boolean teamTwoMustFinishThisTurn;

  public RacingKings(IPlayer p1, IPlayer p2) {
    super(p1, p2);
    this.setupBoard();
  }

  @Override
  protected void setupBoard() {
    this.teamOneKingReachedFinishLine = false;
    this.teamTwoKingReachedFinishLine = false;
    this.teamTwoMustFinishThisTurn = false;

    this.board = new StandardBoard();
    // kings
    this.board.addPiece(new King(p1.getTeam(), 0, 1), 0, 1);
    this.board.addPiece(new King(p2.getTeam(), 7, 1), 7, 1);
    // queens
    this.board.addPiece(new Queen(p1.getTeam(), 0, 0), 0, 0);
    this.board.addPiece(new Queen(p2.getTeam(), 7, 0), 7, 0);
    // rooks
    this.board.addPiece(new Rook(p1.getTeam(), 1, 1), 1, 1);
    this.board.addPiece(new Rook(p1.getTeam(), 1, 0), 1, 0);
    this.board.addPiece(new Rook(p2.getTeam(), 6, 1), 6, 1);
    this.board.addPiece(new Rook(p2.getTeam(), 6, 0), 6, 0);
    // bishops
    this.board.addPiece(new Bishop(p1.getTeam(), 2, 1), 2, 1);
    this.board.addPiece(new Bishop(p1.getTeam(), 2, 0), 2, 0);
    this.board.addPiece(new Bishop(p2.getTeam(), 5, 1), 5, 1);
    this.board.addPiece(new Bishop(p2.getTeam(), 5, 0), 5, 0);
    // knights
    this.board.addPiece(new Knight(p1.getTeam(), 3, 1), 3, 1);
    this.board.addPiece(new Knight(p1.getTeam(), 3, 0), 3, 0);
    this.board.addPiece(new Knight(p2.getTeam(), 4, 1), 4, 1);
    this.board.addPiece(new Knight(p2.getTeam(), 4, 0), 4, 0);
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
    IBoard copyOfBoard = this.board.deepCopy();

    List<IPiece> allCopiedPieces = copyOfBoard.getAllPiecesOnBoard();

    IPiece copyOfTeamOneKing = null;
    IPiece copyOfTeamTwoKing = null;

    for (IPiece piece : allCopiedPieces) {
      if (piece.getType() == PieceType.KING) {
        if (piece.getTeam() == Team.ONE) {
          copyOfTeamOneKing = piece;
        } else if (piece.getTeam() == Team.TWO) {
          copyOfTeamTwoKing = piece;
        }
      }
    }

    copyOfBoard.movePieceFromTo(fromCol, fromRow, targetCol, targetRow);
    if (copyOfTeamOneKing == null || copyOfTeamTwoKing == null) {
      throw new IllegalArgumentException("Invalid game state. Please restart the game.");
    }
    if (copyOfTeamOneKing.canBeTakenBy(copyOfBoard).size() > 0
            || copyOfTeamTwoKing.canBeTakenBy(copyOfBoard).size() > 0) {
      return true;
    }

    return false;
  }

  @Override
  public GameStatusCode getGameStatus() {
    for (int col = 0; col < this.board.getWidth(); col++) {
      IPiece pieceAtEndOfBoard = this.board.getPieceAt(col, 7);
      if (pieceAtEndOfBoard != null && pieceAtEndOfBoard.getType() == PieceType.KING) {
        if (pieceAtEndOfBoard.getTeam() == Team.ONE) {
          this.teamOneKingReachedFinishLine = true;
        }
        if (pieceAtEndOfBoard.getTeam() == Team.TWO) {
          this.teamTwoKingReachedFinishLine = true;
        }
      }
    }

    if (this.teamOneKingReachedFinishLine && this.teamTwoMustFinishThisTurn
            && !this.teamTwoKingReachedFinishLine) {
      return GameStatusCode.TEAM_ONE_WINS;
    } else if (!this.teamOneKingReachedFinishLine && this.teamTwoKingReachedFinishLine) {
      return GameStatusCode.TEAM_TWO_WINS;
    } else if (this.teamOneKingReachedFinishLine && this.teamTwoMustFinishThisTurn
            && this.teamTwoKingReachedFinishLine) {
      return GameStatusCode.DRAW;
    }

    if (this.teamOneKingReachedFinishLine) {
      this.teamTwoMustFinishThisTurn = true;
      return GameStatusCode.TEAM_TWO_MUST_WIN_NEXT_TURN;
    }
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
    return "Racing Kings";
  }
}
