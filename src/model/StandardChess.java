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
  private IBoard board;

  /**
   * Player 1.
   */
  private IPlayer p1;

  /**
   * Player 2.
   */
  private IPlayer p2;

  /**
   * Stores who's turn it currently is.
   */
  private IPlayer currentPlayer;

  /**
   * Constructor for a standard game of chess.
   *
   * @param p1 player one
   * @param p2 player two
   */
  public StandardChess(IPlayer p1, IPlayer p2) {
    this.p1 = p1;
    this.p2 = p2;
    this.currentPlayer = p1;
    this.setupBoard();
  }

  /**
   * Sets up the board to match a standard game of chess' setup.
   */
  private void setupBoard() {
    this.board = new StandardBoard();
    // sets up pawns
    for (int col = 0; col < this.board.getWidth(); col++) {
      this.board.addPiece(new Pawn(p1.getTeam(), col, 1), col, 1);
      this.board.addPiece(new Pawn(p2.getTeam(), col, 6), col, 6);
    }
    // sets up rooks
    this.board.addPiece(new Rook(p1.getTeam(), 0, 0), 0, 0);
    this.board.addPiece(new Rook(p1.getTeam(), 7, 0), 7, 0);
    this.board.addPiece(new Rook(p2.getTeam(), 0, 7), 0, 7);
    this.board.addPiece(new Rook(p2.getTeam(), 7, 7), 7, 7);
    // sets up bishops
    this.board.addPiece(new Bishop(p1.getTeam(), 1, 0), 1, 0);
    this.board.addPiece(new Bishop(p1.getTeam(), 6, 0), 6, 0);
    this.board.addPiece(new Bishop(p2.getTeam(), 1, 7), 1, 7);
    this.board.addPiece(new Bishop(p2.getTeam(), 6, 7), 6, 7);
    // sets up knights
    this.board.addPiece(new Knight(p1.getTeam(), 2, 0), 2, 0);
    this.board.addPiece(new Knight(p1.getTeam(), 5, 0), 5, 0);
    this.board.addPiece(new Knight(p2.getTeam(), 2, 7), 2, 7);
    this.board.addPiece(new Knight(p2.getTeam(), 5, 7), 5, 7);
    // sets up queens and kings
    this.board.addPiece(new King(p1.getTeam(), 4, 0), 4, 0);
    this.board.addPiece(new Queen(p1.getTeam(), 3, 0), 3, 0);
    this.board.addPiece(new King(p2.getTeam(), 4, 7), 4, 7);
    this.board.addPiece(new Queen(p2.getTeam(), 3, 7), 3, 7);
  }

  @Override
  public void movePiece(int fromCol, int fromRow, int targetCol, int targetRow) throws
          IllegalArgumentException {
    if (!this.board.validCoordinates(fromCol, fromRow) || !this.board.validCoordinates(targetCol,
            targetRow)) {
      throw new IllegalArgumentException("Invalid coordinates.");
    }
    IPiece piece = this.board.getPieceAt(fromCol, fromRow);
    if (piece.getTeam() != this.currentPlayer.getTeam()) {
      throw new IllegalArgumentException("You can only move pieces on your team.");
    }
    if (!piece.validMove(targetCol, targetRow, this.board)) {
      throw new IllegalArgumentException("Invalid move!");
    }
    // makes the move and stores the piece that was taken (null if none)
    IPiece taken = this.board.movePieceFromTo(fromCol, fromRow, targetCol, targetRow);

    //TODO process removed piece if needed

    // switches whose turn it is
    if (this.currentPlayer.equals(p1)) {
      this.currentPlayer = p2;
    } else {
      this.currentPlayer = p1;
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int whosTurn() {
    if (this.currentPlayer.equals(p1)) {
      return 1;
    }
    return 2;
  }

  @Override
  public PieceInfo[][] getBoard() {
    PieceInfo[][] result = new PieceInfo[this.board.getWidth()][this.board.getHeight()];
    for (int col = 0; col < this.board.getWidth(); col++) {
      for (int row = 0; row < this.board.getHeight(); row++) {
        IPiece piece = this.board.getPieceAt(col, row);
        if (piece == null) {
          result[col][row] = new PieceInfo(PieceType.EMPTY, null);
          continue;
        }
        result[col][row] = new PieceInfo(piece.getType(), piece.getTeam());
      }
    }
    return result;
  }
}
