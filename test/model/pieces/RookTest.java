package model.pieces;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.board.IBoard;
import model.board.StandardBoard;
import model.players.Team;

import static org.junit.Assert.*;

/**
 * Created by danielchu on 1/2/17.
 */
public class RookTest {
  IBoard standardBoard;
  IPiece rook;
  IPiece pawnSameTeam;
  IPiece pawn1Takeable;
  IPiece pawn2Takeable;
  IPiece pawn3Takeable;
  IPiece pawn4blocked;
  IPiece pawn5safe;

  @Before
  public void setUp() throws Exception {
    standardBoard = new StandardBoard();
    rook = new Rook(Team.ONE, 3, 3);
    pawnSameTeam = new Pawn(Team.ONE, 4, 3);
    pawn1Takeable = new Pawn(Team.TWO, 3, 1);
    pawn2Takeable = new Pawn(Team.TWO, 3, 4);
    pawn3Takeable = new Pawn(Team.TWO, 1, 3);
    pawn4blocked = new Pawn(Team.TWO, 3, 5);
    pawn5safe = new Pawn(Team.TWO, 2, 2);

    standardBoard.addPiece(rook, rook.getCol(), rook.getRow());
    standardBoard.addPiece(pawnSameTeam, pawnSameTeam.getCol(), pawnSameTeam.getRow());
    standardBoard.addPiece(pawn1Takeable, pawn1Takeable.getCol(), pawn1Takeable.getRow());
    standardBoard.addPiece(pawn2Takeable, pawn2Takeable.getCol(), pawn2Takeable.getRow());
    standardBoard.addPiece(pawn3Takeable, pawn3Takeable.getCol(), pawn3Takeable.getRow());
    standardBoard.addPiece(pawn4blocked, pawn4blocked.getCol(), pawn4blocked.getRow());
    standardBoard.addPiece(pawn5safe, pawn5safe.getCol(), pawn5safe.getRow());
  }

  @Test
  public void validMove() throws Exception {
    assertTrue(rook.validMove(3, 4, standardBoard));
    assertTrue(rook.validMove(2, 3, standardBoard));
    assertTrue(rook.validMove(1, 3, standardBoard));
    assertTrue(rook.validMove(3, 4, standardBoard));
    assertFalse(rook.validMove(0, 3, standardBoard));
    assertFalse(rook.validMove(3, 5, standardBoard));
    assertFalse(rook.validMove(3, 6, standardBoard));
    assertFalse(rook.validMove(4, 3, standardBoard));
  }

  @Test
  public void canTakeThese() throws Exception {
    List<IPiece> takeablePieces = new ArrayList<IPiece>();
    takeablePieces.add(pawn3Takeable);
    takeablePieces.add(pawn2Takeable);
    takeablePieces.add(pawn1Takeable);
    assertEquals(takeablePieces, rook.canTakeThese(standardBoard));
  }

}