package model;

/**
 * Created by danielchu on 3/15/17.
 */
public enum GameStatusCode {
  IN_PROGRESS(""),
  TEAM_ONE_WINS("Team One Wins!"),
  TEAM_TWO_WINS("Team Two Wins!"),
  DRAW("Game ended in a draw!"),
  STALEMATE("Game ended in a stalemate"),
  TEAM_TWO_MUST_WIN_NEXT_TURN("Team Two has one turn to draw."),
  CHECK("Check!");

  private final String message;

  GameStatusCode(String message) {
    this.message = message;
  }

  /**
   * Returns the message for this game status code.
   *
   * @return the message for this game status code
   */
  public String getMessage() {
    return this.message;
  }

}