package view;

import model.pieces.PieceInfo;
import model.pieces.PieceType;
import model.players.Team;

/**
 * Created by danielchu on 12/30/16.
 */
public class ConsoleView implements IChessGameView {

  private int currentPlayer;

  private PieceInfo[][] board;

  public ConsoleView() {
    this.currentPlayer = 0;
    this.board = null;
  }

  @Override
  public void initialize() {
    return;
  }

  @Override
  public void setBoard(PieceInfo[][] board, int currentPlayer) {
    this.board = board;
    this.currentPlayer = currentPlayer;
  }

  @Override
  public void render() {
    if (board.length > 0) {
      System.out.println("\n\n\n\n");
      for (int row = board[0].length - 1; row > -1; row--) {
        // draws line separating row from row above it
        System.out.print("  +---+");
        for (int colLine = 1; colLine < board.length; colLine++) {
          System.out.print("---+");
        }
        System.out.println("");

        // draws actual pieces in the row
        System.out.print((row + 1) + " |");
        for (int col = 0; col < board.length; col++) {
          System.out.print(" ");
          String pieceRepresentation = this.drawPiece(board[col][row].getType());
          if (board[col][row].getTeam() == Team.TWO) {
            pieceRepresentation = pieceRepresentation.toLowerCase();
          }
          System.out.print(pieceRepresentation);
          System.out.print(" |");
        }
        System.out.println("");
      }
      // draws line at the bottom of the board
      System.out.print("  +---+");
      for (int colLine = 1; colLine < board.length; colLine++) {
        System.out.print("---+");
      }
      System.out.println("");
      // prints column labels
      System.out.println("    a   b   c   d   e   f   g   h  ");

      // prints who's turn it is
      System.out.println("\nPlayer " + this.currentPlayer + "'s turn.\n\n");
    }
  }

  private String drawPiece(PieceType pieceType) {
    switch (pieceType) {
      case PAWN:
        return "P";
      case ROOK:
        return "R";
      case BISHOP:
        return "B";
      case KNIGHT:
        return "N";
      case QUEEN:
        return "Q";
      case KING:
        return "K";
      case EMPTY:
        return " ";
      default:
        return "X";
    }
  }
}
