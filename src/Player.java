import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private final Color color;
    private boolean turn;

    public Player(Color color) {
        this.color = color;
        if (color == Color.WHITE) {
            turn = true;
        }
        else turn = false;

    }

    public Color getColor() {
        return color;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }


// Add methods to get and set pieces for the player
}
