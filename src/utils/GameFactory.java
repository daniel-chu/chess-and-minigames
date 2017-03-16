package utils;

import model.IChessGameModel;
import model.PawnRush;
import model.RacingKings;
import model.StandardChess;
import model.players.IPlayer;

/**
 * Created by danielchu on 2/25/17.
 */
public class GameFactory {

  public static IChessGameModel createGameOfType(String type, IPlayer p1, IPlayer p2) {
    type = type.toLowerCase();
    switch (type) {
      case "standard":
        return new StandardChess(p1, p2);
      case "pawnrush":
        return new PawnRush(p1, p2);
      case "racingkings":
        return new RacingKings(p1, p2);
      default:
        throw new IllegalArgumentException("Invalid game type.");
    }
  }
}
