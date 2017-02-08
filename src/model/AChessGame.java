package model;

import model.board.IBoard;
import model.pieces.IPiece;
import model.pieces.PieceInfo;
import model.pieces.PieceType;
import model.players.IPlayer;
import model.players.Team;

/**
 * Created by danielchu on 12/31/16.
 */
public abstract class AChessGame implements IChessGameModel {

  /**
   * Represents the chess board.
   */
  protected IBoard board;

  /**
   * Player 1.
   */
  protected IPlayer p1;

  /**
   * Player 2.
   */
  protected IPlayer p2;

  /**
   * Stores who's turn it currently is.
   */
  protected IPlayer currentPlayer;

  /**
   * Constructor for a standard game of chess.
   *
   * @param p1 player one
   * @param p2 player two
   */
  public AChessGame(IPlayer p1, IPlayer p2) {
    this.p1 = p1;
    this.p2 = p2;
    this.currentPlayer = p1;
  }

  /**
   * Sets up the game board for a new game.
   */
  protected abstract void setupBoard();

  @Override
  public IPiece movePiece(int fromCol, int fromRow, int targetCol, int targetRow) throws
          IllegalArgumentException {
    if (!this.board.validCoordinates(fromCol, fromRow) || !this.board.validCoordinates(targetCol,
            targetRow)) {
      throw new IllegalArgumentException("Invalid coordinates.");
    }
    IPiece piece = this.board.getPieceAt(fromCol, fromRow);
    if (piece == null) {
      throw new IllegalArgumentException("No piece there.");
    }
    if (piece.getTeam() != this.currentPlayer.getTeam()) {
      throw new IllegalArgumentException("You can only move pieces on your team.");
    }
    if (!piece.validMove(targetCol, targetRow, this.board)) {
      throw new IllegalArgumentException("Invalid move!");
    }
    // makes the move and stores the piece that was taken (null if none)
    IPiece takenPiece = this.board.movePieceFromTo(fromCol, fromRow, targetCol, targetRow);
    if (takenPiece != null) {
      // process piece if needed (common to all gametypes) or split into own method if necessary
      // TODO remove this print later on
      System.out.println("\n\n" + takenPiece.getType().getValue());
    }
    return takenPiece;
  }

  @Override
  public abstract int isGameOver();

  @Override
  public Team whosTurn() {
    return this.currentPlayer.getTeam();
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

  @Override
  public void restartGame() {
    this.currentPlayer = p1;
  }
}
