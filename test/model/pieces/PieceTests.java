package model.pieces;

import org.junit.Before;
import org.junit.Test;

import model.board.IBoard;
import model.board.StandardBoard;
import model.players.Team;

import static org.junit.Assert.*;

/**
 * Created by danielchu on 1/2/17.
 */
public class PieceTests {
  IBoard board;
  IPiece t1pawn;
  IPiece t1pawnCopy;
  IPiece t1knight;
  IPiece t1king;
  IPiece t2bishop;
  IPiece t2queen;
  IPiece t2rook;
  IPiece t2rookCopy;


  @Before
  public void setUp() throws Exception {
    board = new StandardBoard();
    t1pawn = new Pawn(Team.ONE, 1, 4);
    t1pawnCopy = new Pawn(Team.ONE, 1, 4);
    t1knight = new Knight(Team.ONE, 3, 5);
    t1king = new King(Team.ONE, 1, 5);
    t2bishop = new Bishop(Team.TWO, 4, 3);
    t2queen = new Queen(Team.TWO, 5, 4);
    t2rook = new Rook(Team.TWO, 6, 3);
    t2rookCopy = new Rook(Team.TWO, 6, 3);

    board.addPiece(t1pawn, t1pawn.getCol(), t1pawn.getRow());
    board.addPiece(t1knight, t1knight.getCol(), t1knight.getRow());
    board.addPiece(t1king, t1king.getCol(), t1king.getRow());
    board.addPiece(t2bishop, t2bishop.getCol(), t2bishop.getRow());
    board.addPiece(t2queen, t2queen.getCol(), t2queen.getRow());
    board.addPiece(t2rook, t2rook.getCol(), t2rook.getRow());
  }

  @Test
  public void getterTests() throws Exception {
    assertEquals(t1pawn.getCol(), 1);
    assertEquals(t1pawn.getRow(), 4);
    assertEquals(t1pawn.getTeam(), Team.ONE);
    assertEquals(t1pawn.getType(), PieceType.PAWN);
    assertEquals(t2queen.getCol(), 5);
    assertEquals(t2queen.getRow(), 4);
    assertEquals(t2queen.getTeam(), Team.TWO);
    assertEquals(t2queen.getType(), PieceType.QUEEN);
  }

  @Test
  public void equals() throws Exception {
    assertEquals(t1pawn, t1pawnCopy);
    assertEquals(t2rook, t2rookCopy);
  }

  @Test
  public void validMoveTest() throws Exception {
    assertFalse(t1king.validMove(1, 4, board));
    assertFalse(t2bishop.validMove(2, 3, board));
    assertFalse(t2rook.validMove(5, 2, board));
    assertFalse(t2queen.validMove(2, 1, board));
    assertTrue(t1king.validMove(0, 4, board));
    assertTrue(t2queen.validMove(6, 5, board));
  }

  @Test
  public void moveToTest() throws Exception {
    assertEquals(t1knight.getCol(), 3);
    t1knight.moveTo(5, 4);
    assertEquals(t1knight.getCol(), 5);
    assertEquals(t1pawn.getRow(), 4);
    t1pawn.moveTo(1, 5);
    assertEquals(t1pawn.getRow(), 5);
  }

}