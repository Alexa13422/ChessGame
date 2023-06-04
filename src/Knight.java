import java.awt.*;
import java.util.HashSet;

class Knight extends Piece implements Move{
    String name;
    public Knight(Color color, int row, int col) {
        super(color, row, col);
        if (color == Color.WHITE) name ="w_knight";
        else name = "b_knight";
    }

    @Override
    public HashSet<Integer> getValidMoves(Color color, Board board) {
        HashSet<Integer> validMoves = new HashSet<>();
        //up-left
        if (row>=1 && col>=2) {
            if (board.board[row-1][col-2] == null || board.board[row-1][col-2].getColor() != this.getColor()) validMoves.add((row-1)*10 + col-2);
        }
        //left-up
        if (row>=2 && col>=1){
            if (board.board[row-2][col-1] == null || board.board[row-2][col-1].getColor() != this.getColor()) validMoves.add((row-2)*10 + col-1);
        }
        //right-up
        if (row>=2 && col<=6){
            if (board.board[row-2][col+1] == null || board.board[row-2][col+1].getColor() != this.getColor()) validMoves.add((row-2)*10 + col+1);
        }
        //up-right
        if (row>=1 && col<=5){
            if (board.board[row-1][col+2] == null || board.board[row-1][col+2].getColor() != this.getColor()) validMoves.add((row-1)*10 + col+2);
        }
        //down-right
        if (row<=6 && col<=5){
            if (board.board[row+1][col+2] == null || board.board[row+1][col+2].getColor() != this.getColor()) validMoves.add((row+1)*10 + col+2);
        }
        //right-down
        if (row<=5 && col<=6){
            if (board.board[row+2][col+1] == null || board.board[row+2][col+1].getColor() != this.getColor()) validMoves.add((row+2)*10 + col+1);
        }
        //left-down
        if (row<=5 && col>=1){
            if (board.board[row+2][col-1] == null || board.board[row+2][col-1].getColor() != this.getColor()) validMoves.add((row+2)*10 + col-1);
        }
        //down-left
        if (row<=6 && col>=2){
            if (board.board[row+1][col-2] == null || board.board[row+1][col-2].getColor() != this.getColor()) validMoves.add((row+1)*10 + col-2);
        }
        return validMoves;
    }


    @Override
    public void move(int toRow, int toCol) {
        row = toRow;
        col = toCol;
    }
    public String getName() {
        return name;
    }
}
