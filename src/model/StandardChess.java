package model;

import model.Board.IBoard;
import model.Board.StandardBoard;
import model.Pieces.*;
import model.Players.IPlayer;

/**
 * Created by danielchu on 12/30/16.
 */
public class StandardChess implements IChessGameModel {

  /**
   * Represents the chess board.
   */
  IBoard board;

  /**
   * Player 1.
   */
  IPlayer p1;

  /**
   * Player 2.
   */
  IPlayer p2;

  /**
   * Constructor for a standard game of chess.
   *
   * @param p1 player one
   * @param p2 player two
   */
  public StandardChess(IPlayer p1, IPlayer p2) {
    this.p1 = p1;
    this.p2 = p2;
    this.setupBoard();
  }

  /**
   * Sets up the board to match a standard game of chess' setup.
   */
  private void setupBoard() {
    this.board = new StandardBoard();
    // sets up pawns
    for (int col = 0; col < this.board.getWidth(); col++) {
      IPiece t1pawn = new Pawn(p1.getTeam(), col, 6);
      this.board.addPiece(t1pawn, t1pawn.getCol(), t1pawn.getRow());
      IPiece t2pawn = new Pawn(p2.getTeam(), col, 1);
      this.board.addPiece(t2pawn, t2pawn.getCol(), t2pawn.getRow());
    }
    // sets up rooks
    this.board.addPiece(new Rook(p1.getTeam(), 0, 7), 0, 7);
    this.board.addPiece(new Rook(p1.getTeam(), 7, 7), 7, 7);
    this.board.addPiece(new Rook(p2.getTeam(), 0, 0), 0, 0);
    this.board.addPiece(new Rook(p2.getTeam(), 7, 0), 7, 0);
    // sets up bishops
    this.board.addPiece(new Bishop(p1.getTeam(), 1, 7), 1, 7);
    this.board.addPiece(new Bishop(p1.getTeam(), 6, 7), 6, 7);
    this.board.addPiece(new Bishop(p2.getTeam(), 1, 0), 1, 0);
    this.board.addPiece(new Bishop(p2.getTeam(), 6, 0), 6, 0);
    // sets up knights
    this.board.addPiece(new Knight(p1.getTeam(), 2, 7), 2, 7);
    this.board.addPiece(new Knight(p1.getTeam(), 5, 7), 5, 7);
    this.board.addPiece(new Knight(p2.getTeam(), 2, 0), 2, 0);
    this.board.addPiece(new Knight(p2.getTeam(), 5, 0), 5, 0);
    // sets up queens and kings
    this.board.addPiece(new King(p1.getTeam(), 4, 7), 4, 7);
    this.board.addPiece(new Queen(p1.getTeam(), 3, 7), 3, 7);
    this.board.addPiece(new King(p2.getTeam(), 4, 0), 4, 0);
    this.board.addPiece(new Queen(p2.getTeam(), 3, 0), 3, 0);
  }

  @Override
  public void movePiece(IPiece piece, int col, int row) {
    piece.moveTo(col, row);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }
}
