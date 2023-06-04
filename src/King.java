import java.awt.*;
import java.util.HashSet;

class King extends Piece implements Move{
    String name;
    boolean firstMove = true;

    public King(Color color, int row, int col) {
        super(color, row, col);
        if (color == Color.WHITE) name ="w_king";
        else name = "b_king";
    }

    @Override
    public HashSet<Integer> getValidMoves(Color color, Board board) {
        HashSet<Integer> validMoves = new HashSet<>();
        if (row>0 && board.isEmptySquare(row - 1, col)) validMoves.add((row-1)*10+col);
        if (row>0 && col<7 && board.isEmptySquare(row-1,col+1)) validMoves.add((row-1)*10+col+1);
        if (col<7 && board.isEmptySquare(row,col+1))validMoves.add(row*10+col+1);
        if (row<7 && col<7 && board.isEmptySquare(row+1,col+1)) validMoves.add((row+1)*10+col+1);
        if (row<7 && board.isEmptySquare(row+1,col)) validMoves.add((row+1)*10+col);
        if (row<7 && board.isEmptySquare(row+1,col-1)) validMoves.add((row+1)*10+col-1);
        if (col>0 && board.isEmptySquare(row,col-1)) validMoves.add(row*10+col-1);
        if (row>0 && col>0 && board.isEmptySquare(row-1,col-1)) validMoves.add((row-1)*10+col-1);
        if (firstMove && color.equals(Color.WHITE) && row == 7 && col == 4 && board.isEmptySquare(row,col+1) && board.isEmptySquare(row,col+2))
            validMoves.add((row*10)+col+2);
        if (firstMove && color.equals(Color.BLACK) && row == 0 && col == 4 && board.isEmptySquare(row,col+1) && board.isEmptySquare(row,col+2))
            validMoves.add((row*10)+col+2);
        return validMoves;
    }

    @Override
    public void move(int toRow, int toCol) {
        row = toRow;
        col = toCol;
        firstMove = false;
    }
    public String getName() {
        return name;
    }
}
