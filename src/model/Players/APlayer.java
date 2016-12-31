package model.Players;

import java.awt.*;

/**
 * Created by danielchu on 12/30/16.
 */

/**
 * Abstract class representing a player
 */
public abstract class APlayer implements IPlayer {

  /**
   * Color/team of this player
   */
  Color team;

  /**
   * Constructor for a player.
   *
   * @param team the color/team this player is on
   */
  public APlayer(Color team) {
    this.team = team;
  }

  @Override
  public Color getTeam() {
    return this.team;
  }
}

