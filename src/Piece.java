import java.awt.*;
import java.util.HashSet;

abstract class Piece implements Move{
    private final Color color;
    private String name;
    public int row;
    public int col;

    public Piece(Color color, int row , int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getName() {
        return name;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public Color getColor() {
        return color;
    }

    public abstract HashSet<Integer> getValidMoves(Color color, Board board);

}
