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
 * Created by danielchu on 2/14/17.
 */
public class KnightTest {

  IBoard standardBoard;
  IPiece knight;
  IPiece pawnc2r5;
  IPiece pawnc4r5;
  IPiece pawnc5r4SameTeam;
  IPiece pawnc4r1;
  IPiece pawnc1r2;
  IPiece pawnc7r7;

  @Before
  public void setUp() throws Exception {
    standardBoard = new StandardBoard();
    knight = new Knight(Team.ONE, 3, 3);
    pawnc2r5 = new Pawn(Team.TWO, 2, 5);
    pawnc4r5 = new Pawn(Team.TWO, 4, 5);
    pawnc5r4SameTeam = new Pawn(Team.ONE, 5, 4);
    pawnc4r1 = new Pawn(Team.TWO, 4, 1);
    pawnc1r2 = new Pawn(Team.TWO, 1, 2);
    pawnc7r7 = new Pawn(Team.TWO, 7, 7);

    standardBoard.addPiece(pawnc2r5, pawnc2r5.getCol(), pawnc2r5.getRow());
    standardBoard.addPiece(pawnc4r5, pawnc4r5.getCol(), pawnc4r5.getRow());
    standardBoard.addPiece(pawnc5r4SameTeam, pawnc5r4SameTeam.getCol(), pawnc5r4SameTeam.getRow());
    standardBoard.addPiece(pawnc4r1, pawnc4r1.getCol(), pawnc4r1.getRow());
    standardBoard.addPiece(pawnc1r2, pawnc1r2.getCol(), pawnc1r2.getRow());
    standardBoard.addPiece(pawnc7r7, pawnc7r7.getCol(), pawnc7r7.getRow());
  }

  @Test
  public void validMove() throws Exception {
    assertFalse(knight.validMove(3, 3, standardBoard));
    assertTrue(knight.validMove(2, 5, standardBoard));
    assertTrue(knight.validMove(4, 5, standardBoard));
    assertFalse(knight.validMove(5, 4, standardBoard));
    assertTrue(knight.validMove(4, 1, standardBoard));
    assertTrue(knight.validMove(1, 4, standardBoard));
    assertTrue(knight.validMove(1, 2, standardBoard));
    assertFalse(knight.validMove(7, 7, standardBoard));
    assertFalse(knight.validMove(7, 6, standardBoard));
    assertFalse(knight.validMove(5, 3, standardBoard));
    assertFalse(knight.validMove(3, 5, standardBoard));

  }

  @Test
  public void canTakeThese() throws Exception {
    List<IPiece> takeablePieces = new ArrayList<IPiece>();
    takeablePieces.add(pawnc2r5);
    takeablePieces.add(pawnc4r5);
    takeablePieces.add(pawnc4r1);
    takeablePieces.add(pawnc1r2);
    assertEquals(new HashSet(takeablePieces), new HashSet(knight.canTakeThese(standardBoard)));
  }
}
