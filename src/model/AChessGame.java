package model;

import java.util.List;

import model.board.IBoard;
import model.pieces.IPiece;
import model.pieces.PieceInfo;
import model.pieces.PieceType;
import model.players.IPlayer;
import model.players.Team;

// TODO implement UNDO last move

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
      // process piece if needed (common to all gametypes) or split into own method
    }
    return takenPiece;
  }

  @Override
  public boolean willCauseInvalidStateFromCheck(int fromCol, int fromRow, int targetCol, int
          targetRow) {
    IBoard copyOfBoard = this.board.deepCopy();

    List<IPiece> allCopiedPieces = copyOfBoard.getAllPiecesOnBoard();
    Team teamMakingMove = this.board.getPieceAt(fromCol, fromRow).getTeam();

    IPiece copyOfTargetKing = null;

    for (IPiece piece : allCopiedPieces) {
      if (piece.getType() == PieceType.KING) {
        if (piece.getTeam() == teamMakingMove) {
          copyOfTargetKing = piece;
        }
      }
    }

    copyOfBoard.movePieceFromTo(fromCol, fromRow, targetCol, targetRow);
    if (copyOfTargetKing == null) {
      throw new IllegalArgumentException("Invalid game state. Please restart the game.");
    }
    if (copyOfTargetKing.canBeTakenBy(copyOfBoard).size() > 0) {
      return true;
    }

    return false;
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

  @Override
  public boolean hasPieceOnCell(String cell) {
    if (!cell.matches("[a-zA-Z]\\d+")) {
      return false;
    }
    char col = cell.charAt(0);
    int colIndex = (int) col - 65;
    int rowIndex = Integer.parseInt(cell.substring(1)) - 1;
    IPiece piece = this.board.getPieceAt(colIndex, rowIndex);
    if (piece == null) {
      return false;
    }
    return true;
  }

  @Override
  public String getGameModeName() {
    return "Chess";
  }
}
