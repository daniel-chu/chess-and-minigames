package model.board;

import org.junit.Before;
import org.junit.Test;

import model.pieces.*;
import model.players.Team;

import static org.junit.Assert.*;

/**
 * Created by danielchu on 1/2/17.
 */
public class BoardTests {
  IBoard board1;
  IPiece t1pawn1;
  IPiece t1pawn2;
  IPiece t1knight1;
  IPiece t1king1;
  IPiece t2pawn1;
  IPiece t2bishop1;
  IPiece t2queen1;
  IPiece t2rook1;


  @Before
  public void setUp() throws Exception {
    board1 = new StandardBoard();
    t1pawn1 = new Pawn(Team.ONE, 1, 4, true);
    t1pawn2 = new Pawn(Team.ONE, 2, 4, true);
    t1knight1 = new Knight(Team.ONE, 3, 5);
    t1king1 = new King(Team.ONE, 1, 5);
    t2pawn1 = new Pawn(Team.TWO, 6, 5, false);
    t2bishop1 = new Bishop(Team.TWO, 4, 2);
    t2queen1 = new Queen(Team.TWO, 5, 4);
    t2rook1 = new Rook(Team.TWO, 6, 3);

    board1.addPiece(t1pawn1, t1pawn1.getCol(), t1pawn1.getRow());
    board1.addPiece(t1pawn2, t1pawn2.getCol(), t1pawn2.getRow());
    board1.addPiece(t1knight1, t1knight1.getCol(), t1knight1.getRow());
    board1.addPiece(t1king1, t1king1.getCol(), t1king1.getRow());
    board1.addPiece(t2pawn1, t2pawn1.getCol(), t2pawn1.getRow());
    board1.addPiece(t2queen1, t2queen1.getCol(), t2queen1.getRow());
    board1.addPiece(t2rook1, t2rook1.getCol(), t2rook1.getRow());
  }

  @Test
  public void getPieceAt() throws Exception {
    assertEquals(board1.getPieceAt(t1pawn1.getCol(), t1pawn1.getRow()), t1pawn1);
    assertEquals(board1.getPieceAt(t2pawn1.getCol(), t2pawn1.getRow()), t2pawn1);
    assertEquals(board1.getPieceAt(t2queen1.getCol(), t2queen1.getRow()), t2queen1);
  }

  @Test
  public void addPiece() throws Exception {
    assertNull(board1.getPieceAt(t2bishop1.getCol(), t2bishop1.getRow()));
    board1.addPiece(t2bishop1, t2bishop1.getCol(), t2bishop1.getRow());
    assertEquals(board1.getPieceAt(t2bishop1.getCol(), t2bishop1.getRow()), t2bishop1);
  }

  @Test
  public void removePiece() throws Exception {
    board1.addPiece(t2bishop1, t2bishop1.getCol(), t2bishop1.getRow());
    assertEquals(board1.getPieceAt(t2bishop1.getCol(), t2bishop1.getRow()), t2bishop1);
    board1.removePiece(t2bishop1.getCol(), t2bishop1.getRow());
    assertNull(board1.getPieceAt(t2bishop1.getCol(), t2bishop1.getRow()));
  }

  @Test
  public void movePieceFromTo() throws Exception {
    assertEquals(board1.getPieceAt(t2queen1.getCol(), t2queen1.getRow()), t2queen1);
    board1.movePieceFromTo(t1knight1.getCol(), t1knight1.getRow(), t2queen1.getCol(),
            t2queen1.getRow());
    assertEquals(board1.getPieceAt(t2queen1.getCol(), t2queen1.getRow()), t1knight1);
  }

  @Test
  public void validCoordinates() throws Exception {
    assertFalse(board1.validCoordinates(-1, 4));
    assertFalse(board1.validCoordinates(0, 8));
    assertFalse(board1.validCoordinates(10, 3));
    assertFalse(board1.validCoordinates(7, -4));
    assertTrue(board1.validCoordinates(4, 7));
  }

}