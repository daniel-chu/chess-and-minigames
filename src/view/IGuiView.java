package view;

import controller.handlers.KeyHandler;
import controller.handlers.MouseHandler;

/**
 * Created by danielchu on 1/15/17.
 */
public interface IGuiView extends IChessGameView {

  /**
   * Gets the string that will be used to make a move.
   *
   * @return string containing the move to be made
   */
  String getMoveString();

  /**
   * Creates buttons, sets the action listeners for the buttons in this view using the
   * IViewButtonListeners, as well as adds them to the panel.
   *
   * @param buttonListener the instance of IViewButtonListeners we will get the listeners from
   */
  void addButtonsAndListeners(IViewButtonListeners buttonListener);

  /**
   * Clears the input field.
   */
  void clearInputString();

  /**
   * Updates attributes that depend on the board (for ex. text field width).
   */
  void updateModelDependentAttributes();

  /**
   * Adds the key listener to the necessary components.
   *
   * @param keyHandler the keyhandler we are adding
   */
  void addKeyListenerToComponents(KeyHandler keyHandler);

  /**
   * Adds the mouse listener to the necessary components.
   *
   * @param mouseHandler the mouse handler we are adding
   */
  void addMouseListenerToComponents(MouseHandler mouseHandler);

  /**
   * Resets focus on components in the view.
   */
  void resetFocus();

  /**
   * Displays a win screen and allows user to restart the game.
   *
   * @param s         String containing which player has won.
   * @param listeners instance of IViewButtonListeners containing listeners needed
   */
  void winScreen(String s, IViewButtonListeners listeners);
}
