package model;

import model.players.Team;

/**
 * Class representing a move in a game of chess.
 */
public class Move {

  private Team team;
  private int fromCol;
  private int fromRow;
  private int toCol;
  private int toRow;

  public Move(Team team, int fromCol, int fromRow, int toCol, int toRow) {
    this.team = team;
    this.fromCol = fromCol;
    this.fromRow = fromRow;
    this.toCol = toCol;
    this.toRow = toRow;
  }

  public Team getTeam() {
    return team;
  }

  public int getFromCol() {
    return fromCol;
  }

  public int getFromRow() {
    return fromRow;
  }

  public int getToCol() {
    return toCol;
  }

  public int getToRow() {
    return toRow;
  }

  @Override
  public String toString() {
    return "Move[" + "team=" + team.toString() + ", fromCol=" + fromCol + ", fromRow=" + fromRow +
            ", toCol=" + toCol + ", toRow=" + toRow + "]";
  }
}
