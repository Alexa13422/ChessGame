import java.awt.Color;
import java.util.HashSet;
import java.util.Scanner;

class Pawn extends Piece implements Move {
    String name;
    public Pawn(Color color, int row, int col) {
        super(color, row, col);
        if (color == Color.WHITE) name ="w_pawn";
        else name = "b_pawn";
    }
    @Override
    public HashSet<Integer> getValidMoves(Color color, Board board) {
        HashSet<Integer> validMoves = new HashSet<>();
                if (color.equals(Color.WHITE)) {
                    //Moving
                    if (row==6 && board.isEmptySquare(row-1,col) && board.isEmptySquare(row-2,col))
                        validMoves.add((row - 2) * 10 + col);
                    if (row!=0){
                    if (board.isEmptySquare(row-1,col)) validMoves.add((row-1)*10 + col);
                    }
                    //Fight
                    if (col == 0 && row !=0) {
                        if (!board.isEmptySquare(row-1,col+1) && board.board[row - 1][col + 1].getColor() == Color.BLACK)
                            validMoves.add((row-1)*10 + col+1);
                    } else if (col == 7 && row != 0){
                        if (!board.isEmptySquare(row-1,col-1) && board.board[row - 1][col - 1].getColor() == Color.BLACK)
                            validMoves.add((row-1)*10 + col-1);
                    } else if (row !=0){
                        if (!board.isEmptySquare(row-1,col+1) && board.board[row-1][col+1].getColor() == Color.BLACK)
                            validMoves.add((row-1)*10 + col+1);
                        if (!board.isEmptySquare(row-1,col-1) && board.board[row - 1][col - 1].getColor() == Color.BLACK)
                            validMoves.add((row-1)*10 + col-1);
                    }
                } else {
                    //Moving
                    if (row==1 && board.isEmptySquare(row+1,col) && board.isEmptySquare(row+2,col))
                        validMoves.add((row + 2) * 10 + col);
                    if (row!=7){
                        if (board.isEmptySquare(row+1,col)) validMoves.add((row+1)*10 + col);
                    }
                    //Fight
                    if (col == 0 && row!=7) {
                        if (!board.isEmptySquare(row+1,col+1) && board.board[row + 1][col + 1].getColor() == Color.WHITE)
                            validMoves.add((row+1)*10 + col+1);
                    } else if (col == 7 && row!=7){
                        if (!board.isEmptySquare(row+1,col-1) && board.board[row + 1][col - 1].getColor() == Color.WHITE)
                            validMoves.add((row+1)*10 + col-1);
                    } else if (row!=7){
                        if (!board.isEmptySquare(row+1,col+1) && board.board[row+1][col+1].getColor() == Color.WHITE)
                            validMoves.add((row+1)*10 + col+1);
                        if (!board.isEmptySquare(row+1,col-1) && board.board[row + 1][col - 1].getColor() == Color.WHITE)
                            validMoves.add((row+1)*10 + col-1);
                    }
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
