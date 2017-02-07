import controller.ChessController;
import controller.IChessController;
import model.IChessGameModel;
import model.PawnRush;
import model.players.HumanPlayer;
import model.players.IPlayer;
import model.players.Team;
import model.StandardChess;
import view.IGuiView;
import view.MainView;

/**
 * Created by danielchu on 12/30/16.
 */
public class ChessRunner {

  public static void main(String[] args) {
    IGuiView guiView = new MainView();

    IPlayer player1 = new HumanPlayer(Team.ONE);
    IPlayer player2 = new HumanPlayer(Team.TWO);
    IChessGameModel standardGame = new StandardChess(player1, player2);
    IChessGameModel pawnRush = new PawnRush(player1, player2);

    IChessController regularController = new ChessController(pawnRush, guiView);

    regularController.run();
  }
}
