# Chess and Minigames
Play standard chess and various mini games.

## How to Use
**Running**  
Run the jar with a single argument &lt;gametype&gt; like this:  
  java -jar ChessGame.jar &lt;gametype&gt;

   - Current game types include:
     - **Standard**
       - argument: “standard”
       - Standard chess rules.
     - **Pawn Rush**
       - argument: “pawnrush”
       - 8 White Pawns vs. 1 Black Queen. The objective for White is to either take the Queen, or have one of your pawns reach the other side. Black's objective is to take all the pawns.
     - **Racing Kings**
       - argument: "racingkings"
       - Objective is to get your king to the other side of the board. Checks are not permitted, so you may not check the opposing king, or move your own King into check. Pieces start in a special setup. If the white King reaches the other side, and the black King reaches the other side right after, the game is a tie (to compensate for white getting to move first).

**Controls**  
  - You can use click controls to move pieces, or you can type moves in the input bar below the board.
    - Typed moves are formatted like this “D4 D8”, meaning move the piece at D4 to D8.

**Keyboard Shortcuts**
  - R: Restart the game
  - U: Undo the last move
