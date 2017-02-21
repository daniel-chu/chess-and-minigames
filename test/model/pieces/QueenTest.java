package model.pieces;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import model.board.IBoard;
import model.board.StandardBoard;
import model.players.Team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by danielchu on 1/15/17.
 */
public class QueenTest {
  IBoard standardBoard;
  IPiece queen;
  IPiece pawnc0r0;
  IPiece pawnc0r3;
  IPiece pawnc1r3;
  IPiece pawnc1r5;
  IPiece pawnc2r2;
  IPiece pawnc2r4;
  IPiece pawnc3r0;
  IPiece pawnc3r2;
  IPiece pawnc3r5;
  IPiece pawnc3r6;
  IPiece pawnc4r3;
  IPiece pawnc5r1;
  IPiece pawnc5r5;
  IPiece pawnc6r0;
  IPiece pawnc6r3;
  IPiece pawnc6r6;

  @Before
  public void setUp() throws Exception {
    standardBoard = new StandardBoard();
    queen = new Queen(Team.ONE, 3, 3);
    pawnc0r0 = new Pawn(Team.TWO, 0, 0);
    pawnc0r3 = new Pawn(Team.TWO, 0, 3);
    pawnc1r3 = new Pawn(Team.TWO, 1, 3);
    pawnc1r5 = new Pawn(Team.TWO, 1, 5);
    pawnc2r2 = new Pawn(Team.TWO, 2, 2);
    pawnc2r4 = new Pawn(Team.TWO, 2, 4);
    pawnc3r0 = new Pawn(Team.TWO, 3, 0);
    pawnc3r2 = new Pawn(Team.TWO, 3, 2);
    pawnc3r5 = new Pawn(Team.TWO, 3, 5);
    pawnc3r6 = new Pawn(Team.TWO, 3, 6);
    pawnc4r3 = new Pawn(Team.TWO, 4, 3);
    pawnc5r1 = new Pawn(Team.TWO, 5, 1);
    pawnc5r5 = new Pawn(Team.TWO, 5, 5);
    pawnc6r0 = new Pawn(Team.TWO, 6, 0);
    pawnc6r3 = new Pawn(Team.TWO, 6, 3);
    pawnc6r6 = new Pawn(Team.TWO, 6, 6);

    standardBoard.addPiece(pawnc0r0, pawnc0r0.getCol(), pawnc0r0.getRow());
    standardBoard.addPiece(pawnc0r3, pawnc0r3.getCol(), pawnc0r3.getRow());
    standardBoard.addPiece(pawnc1r3, pawnc1r3.getCol(), pawnc1r3.getRow());
    standardBoard.addPiece(pawnc1r5, pawnc1r5.getCol(), pawnc1r5.getRow());
    standardBoard.addPiece(pawnc2r2, pawnc2r2.getCol(), pawnc2r2.getRow());
    standardBoard.addPiece(pawnc2r4, pawnc2r4.getCol(), pawnc2r4.getRow());
    standardBoard.addPiece(pawnc3r0, pawnc3r0.getCol(), pawnc3r0.getRow());
    standardBoard.addPiece(pawnc3r2, pawnc3r2.getCol(), pawnc3r2.getRow());
    standardBoard.addPiece(pawnc3r5, pawnc3r5.getCol(), pawnc3r5.getRow());
    standardBoard.addPiece(pawnc3r6, pawnc3r6.getCol(), pawnc3r6.getRow());
    standardBoard.addPiece(pawnc4r3, pawnc4r3.getCol(), pawnc4r3.getRow());
    standardBoard.addPiece(pawnc5r1, pawnc5r1.getCol(), pawnc5r1.getRow());
    standardBoard.addPiece(pawnc5r5, pawnc5r5.getCol(), pawnc5r5.getRow());
    standardBoard.addPiece(pawnc6r0, pawnc6r0.getCol(), pawnc6r0.getRow());
    standardBoard.addPiece(pawnc6r3, pawnc6r3.getCol(), pawnc6r3.getRow());
    standardBoard.addPiece(pawnc6r6, pawnc6r6.getCol(), pawnc6r6.getRow());
  }

  @Test
  public void validMove() throws Exception {
    assertTrue(queen.validMove(2, 3, standardBoard));
    assertTrue(queen.validMove(2, 4, standardBoard));
    assertTrue(queen.validMove(1, 3, standardBoard));
    assertTrue(queen.validMove(3, 4, standardBoard));
    assertTrue(queen.validMove(2, 4, standardBoard));
    assertFalse(queen.validMove(3, 6, standardBoard));
    assertFalse(queen.validMove(0, 3, standardBoard));
    assertFalse(queen.validMove(2, 1, standardBoard));
    assertFalse(queen.validMove(3, 1, standardBoard));
    assertTrue(queen.validMove(3, 2, standardBoard));
    assertTrue(queen.validMove(4, 2, standardBoard));
    assertTrue(queen.validMove(4, 3, standardBoard));
    assertTrue(queen.validMove(4, 4, standardBoard));
    assertTrue(queen.validMove(5, 1, standardBoard));
    assertFalse(queen.validMove(5, 3, standardBoard));
    assertTrue(queen.validMove(5, 5, standardBoard));
    assertFalse(queen.validMove(6, 6, standardBoard));
    assertFalse(queen.validMove(6, 0, standardBoard));
    assertFalse(queen.validMove(6, 3, standardBoard));
  }

  @Test
  public void canTakeThese() throws Exception {
    Set<IPiece> takeablePieces = new HashSet<IPiece>();
    takeablePieces.add(pawnc1r3);
    takeablePieces.add(pawnc2r2);
    takeablePieces.add(pawnc2r4);
    takeablePieces.add(pawnc3r2);
    takeablePieces.add(pawnc3r5);
    takeablePieces.add(pawnc4r3);
    takeablePieces.add(pawnc5r1);
    takeablePieces.add(pawnc5r5);
    assertEquals(takeablePieces, queen.canTakeThese(standardBoard));
  }

}
