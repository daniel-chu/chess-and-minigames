package controller.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles mouse events.
 */
public class MouseHandler implements MouseListener {

  /**
   * Maps Integer keys to Runnables.
   */
  private Map<Integer, Runnable> mouseClicked;

  /**
   * Stores the last mouse event so we can get the position.
   */
  private MouseEvent mouseEvent;

  /**
   * Constructor for a MouseHandler.
   */
  public MouseHandler() {
    this.mouseClicked = new HashMap<Integer, Runnable>();
  }

  /**
   * Adds in an event for when the given button is clicked.
   *
   * @param key    The key we want the given runnable to be mapped to
   * @param action The runnable we want to run when the given button is clicked
   */
  public void addMouseClicked(int key, Runnable action) {
    this.mouseClicked.put(key, action);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.mouseEvent = e;
    if (mouseClicked.containsKey(e.getButton())) {
      Runnable action = this.mouseClicked.get(e.getButton());
      if (action != null) {
        action.run();
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // Not needed, we have no pressed events
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // Not needed, we have no released events
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // Not needed, we have no entered events
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // Not needed, we have no exited events
  }

  /**
   * Gets the last mouse event.
   */
  public MouseEvent getMouseEvent() {
    return this.mouseEvent;
  }

}
