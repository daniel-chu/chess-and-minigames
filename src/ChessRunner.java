import controller.BasicController;
import controller.IChessController;
import model.IChessGameModel;
import model.PawnRush;
import model.players.HumanPlayer;
import model.players.IPlayer;
import model.players.Team;
import model.StandardChess;
import view.ConsoleView;
import view.IChessGameView;

/**
 * Created by danielchu on 12/30/16.
 */
public class ChessRunner {

  public static void main(String[] args) {
    IChessGameView consoleView = new ConsoleView();

    IPlayer player1 = new HumanPlayer(Team.ONE);
    IPlayer player2 = new HumanPlayer(Team.TWO);
    IChessGameModel standardGame = new StandardChess(player1, player2);
    IChessGameModel pawnRush = new PawnRush(player1, player2);

    IChessController controller = new BasicController(pawnRush, consoleView);
    controller.run();

  }
}
