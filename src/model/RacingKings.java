package model;

import java.util.List;

import model.board.IBoard;
import model.board.StandardBoard;
import model.pieces.Bishop;
import model.pieces.IPiece;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.PieceType;
import model.pieces.Queen;
import model.pieces.Rook;
import model.players.IPlayer;
import model.players.Team;

/**
 * Created by danielchu on 3/15/17.
 */
public class RacingKings extends AChessGame {


  // TODO implement some sort of turn counter (probably in all modes) so that we can check that
  // black reaches end the turn after white (in order to draw).


  public RacingKings(IPlayer p1, IPlayer p2) {
    super(p1, p2);
    this.setupBoard();
  }

  @Override
  protected void setupBoard() {
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
    // TODO implement this (checks cannot exist at all)
    IBoard copyOfBoard = this.board.deepCopy();

    List<IPiece> allCopiedPieces = copyOfBoard.getAllPiecesOnBoard();
    Team teamMakingMove = this.board.getPieceAt(fromCol, fromRow).getTeam();

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
    if(copyOfTeamOneKing == null || copyOfTeamTwoKing == null) {
      throw new IllegalArgumentException("Invalid game state. Please restart the game.");
    }
    if (copyOfTeamOneKing.canBeTakenBy(copyOfBoard).size() > 0
            || copyOfTeamTwoKing.canBeTakenBy(copyOfBoard).size() > 0) {
      return true;
    }

    return false;
  }

  @Override
  public int isGameOver() {
    return 0;
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
