package model.pieces;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.board.IBoard;
import model.board.StandardBoard;
import model.players.Team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by danielchu on 2/4/17.
 */
public class BishopTest {

  IBoard standardBoard;
  IPiece bishop;
  IPiece pawnc0r5;
  IPiece pawnc1r3;
  IPiece pawnc2r2;
  IPiece pawnc2r4;
  IPiece pawnc3r2;
  IPiece pawnc3r3;
  IPiece pawnc3r4;
  IPiece pawnc4r1;
  IPiece pawnc4r5;

  @Before
  public void setUp() throws Exception {
    standardBoard = new StandardBoard();
    bishop = new Bishop(Team.ONE, 2, 3);
    pawnc0r5 = new Pawn(Team.TWO, 0, 5);
    pawnc1r3 = new Pawn(Team.TWO, 1, 3);
    pawnc2r2 = new Pawn(Team.TWO, 2, 2);
    pawnc2r4 = new Pawn(Team.TWO, 2, 4);
    pawnc3r2 = new Pawn(Team.TWO, 3, 2);
    pawnc3r3 = new Pawn(Team.TWO, 3, 3);
    pawnc3r4 = new Pawn(Team.TWO, 3, 4);
    pawnc4r1 = new Pawn(Team.TWO, 4, 1);
    pawnc4r5 = new Pawn(Team.TWO, 4, 5);

    standardBoard.addPiece(bishop, bishop.getCol(), bishop.getRow());
    standardBoard.addPiece(pawnc0r5, pawnc0r5.getCol(), pawnc0r5.getRow());
    standardBoard.addPiece(pawnc1r3, pawnc1r3.getCol(), pawnc1r3.getRow());
    standardBoard.addPiece(pawnc2r2, pawnc2r2.getCol(), pawnc2r2.getRow());
    standardBoard.addPiece(pawnc2r4, pawnc2r4.getCol(), pawnc2r4.getRow());
    standardBoard.addPiece(pawnc3r2, pawnc3r2.getCol(), pawnc3r2.getRow());
    standardBoard.addPiece(pawnc3r3, pawnc3r3.getCol(), pawnc3r3.getRow());
    standardBoard.addPiece(pawnc3r4, pawnc3r4.getCol(), pawnc3r4.getRow());
    standardBoard.addPiece(pawnc4r1, pawnc4r1.getCol(), pawnc4r1.getRow());
    standardBoard.addPiece(pawnc4r5, pawnc4r5.getCol(), pawnc4r5.getRow());
  }

  @Test
  public void validMove() throws Exception {
    assertTrue(bishop.validMove(0, 1, standardBoard));
    assertTrue(bishop.validMove(1, 2, standardBoard));
    assertTrue(bishop.validMove(1, 4, standardBoard));
    assertFalse(bishop.validMove(0, 2, standardBoard));
    assertFalse(bishop.validMove(1, 5, standardBoard));
    assertFalse(bishop.validMove(4, 5, standardBoard));
    assertTrue(bishop.validMove(3, 2, standardBoard));
    assertFalse(bishop.validMove(4, 1, standardBoard));
    assertFalse(bishop.validMove(2, 4, standardBoard));
  }

  @Test
  public void canTakeThese() throws Exception {
    List<IPiece> takeablePieces = new ArrayList<IPiece>();
    takeablePieces.add(pawnc0r5);
    takeablePieces.add(pawnc3r2);
    takeablePieces.add(pawnc3r4);
    assertEquals(new HashSet(takeablePieces), new HashSet(bishop.canTakeThese(standardBoard)));
  }
}
