package model.players;

/**
 * Created by danielchu on 12/31/16.
 */
public enum Team {
  ONE("White"), TWO("Black");

  private String color;

  Team(String color) {
    this.color = color;
  }

  public String getColor() {
    return this.color;
  }

}

