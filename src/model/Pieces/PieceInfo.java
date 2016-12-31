package model.Pieces;

/**
 * Created by danielchu on 12/31/16.
 */

import model.Players.Team;

/**
 * Class to represent a piece's information necessary for display.
 */
public class PieceInfo {

  /**
   * Type of piece that this represents.
   */
  PieceType type;

  /**
   * Team of piece that this represents.
   */
  Team team;

  /**
   * Constructor for a PieceInfo representing a IPiece.
   *
   * @param type type of the piece
   * @param team team the piece is from
   */
  public PieceInfo(PieceType type, Team team) {
    this.type = type;
    this.team = team;
  }

  /**
   * Returns this piece's type.
   *
   * @return the type of the piece this pieceinfo represents.
   */
  public PieceType getType() {
    return type;
  }

  /**
   * Returns this piece's team.
   *
   * @return the team of the piece this pieceinfo represents
   */
  public Team getTeam() {
    return team;
  }

}
