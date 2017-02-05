package controller.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Keyboard handler for key events.
 */
public class KeyHandler implements KeyListener {

  /**
   * Map of integer keys and runnable values for when a key is typed.
   */
  private Map<Integer, Runnable> keyTyped;
  /**
   * Map of integer keys and runnable values for when a key is pressed.
   */
  private Map<Integer, Runnable> keyPressed;
  /**
   * Map of integer keys and runnable values for when a key is released.
   */
  private Map<Integer, Runnable> keyReleased;

  /**
   * Constructor for a KeyHandler.
   */
  public KeyHandler() {
    this.keyTyped = new HashMap<Integer, Runnable>();
    this.keyPressed = new HashMap<Integer, Runnable>();
    this.keyReleased = new HashMap<Integer, Runnable>();
  }

  /**
   * Adds an event for when the given key is typed.
   *
   * @param key    The key we want the given runnable to be mapped to
   * @param action The runnable we want to run when the given key is pressed
   */
  public void addKeyTyped(int key, Runnable action) {
    this.keyTyped.put(key, action);
  }

  /**
   * Adds in an event for when the given key is pressed.
   *
   * @param key    The key we want the given runnable to be mapped to
   * @param action The runnable we want to run when the given key is pressed
   */
  public void addKeyPressed(int key, Runnable action) {
    this.keyPressed.put(key, action);
  }

  /**
   * Adds in an event for when the given key is released.
   *
   * @param key    The key we want the given runnable to be mapped to
   * @param action The runnable we want to run when the given key is pressed
   */
  public void addKeyReleased(int key, Runnable action) {
    this.keyReleased.put(key, action);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (this.keyTyped.containsKey(e.getKeyCode())) {
      Runnable action = this.keyTyped.get(e.getKeyCode());
      if (action != null) {
        action.run();
      }
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (this.keyPressed.containsKey(e.getKeyCode())) {
      Runnable action = this.keyPressed.get(e.getKeyCode());
      if (action != null) {
        action.run();
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (this.keyReleased.containsKey(e.getKeyCode())) {
      Runnable action = this.keyReleased.get(e.getKeyCode());
      if (action != null) {
        action.run();
      }
    }
  }
}
