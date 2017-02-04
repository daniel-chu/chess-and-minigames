package view;

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
}
