package model.players;

/**
 * Created by danielchu on 12/30/16.
 */

// TODO maybe implement names

/**
 * Abstract class representing a player
 */
public abstract class APlayer implements IPlayer {

  /**
   * Team of this player.
   */
  Team team;

  /**
   * Constructor for a player.
   *
   * @param team the team this player is on
   */
  public APlayer(Team team) {
    this.team = team;
  }

  @Override
  public Team getTeam() {
    return this.team;
  }
}

