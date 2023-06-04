import java.awt.*;
import java.util.HashSet;

class Queen extends Piece implements Move {
String name;
    public Queen(Color color, int row, int col) {
        super(color, row, col);
        if (color == Color.WHITE) name ="w_queen";
        else name = "b_queen";
    }

    @Override
    public HashSet<Integer> getValidMoves(Color color, Board board) {
        HashSet<Integer> validMoves = new HashSet<>();
        //left
        if (col == 0){}
        else {
            for (int i = col-1; i > 0; i--) {
                if (board.board[row][i] != null && board.board[row][i].getColor() == this.getColor()) break;
                validMoves.add(row*10+i);
                if (board.board[row][i] != null && board.board[row][i].getColor() != this.getColor()) break;
                if (col==0) break;
            }}
        //up
        if (row==0){}
        else {
            for (int i = row-1; i > 0; i--) {
                if (board.board[i][col] != null && board.board[i][col].getColor() == this.getColor()) break;
                validMoves.add(i*10+col);
                if (board.board[i][col] != null && board.board[i][col].getColor() != this.getColor()) break;
                if (row == 0) break;
            }}
        //right
        if (col == 7){}
        for (int i = col+1; i < board.board[1].length; i++) {
            if (board.board[row][i] != null && board.board[row][i].getColor() == this.getColor()) break;
            validMoves.add(row*10+i);
            if (board.board[row][i] != null && board.board[row][i].getColor() != this.getColor()) break;
            if (col==7) break;
        }
        //down
        if (row == 7){}
        else {
            for (int i = row+1; i < board.board.length; i++) {
                if (board.board[i][col] != null && board.board[i][col].getColor() == this.getColor()) break;
                validMoves.add(i*10+col);
                if (board.board[i][col] != null && board.board[i][col].getColor() != this.getColor()) break;
                if (row == 7) break;
            }}
        if (row==0 || col==0){}
        else {
            for (int i = row-1,j = col-1; i>0 || j>0; i-- ,j--) {
                if (board.board[i][j] != null && board.board[i][j].getColor() == this.getColor()) break;
                validMoves.add(i*10+j);
                if (board.board[i][j] != null && board.board[i][j].getColor() != this.getColor()) break;
                if (i==0||j==0) break;
            }}
        if (row==0 || col==7){}
        else {
            for (int i = row-1,j = col+1; i>0 || j<board.board.length; i--,j++) {
                if (board.board[i][j] != null && board.board[i][j].getColor() == this.getColor()) break;
                validMoves.add(i*10+j);
                if (board.board[i][j] != null && board.board[i][j].getColor() != this.getColor()) break;
                if (i==0||j==7) break;
            }}
        if (row== 7 || col == 7){}
        else {
            for (int i = row+1,j = col+1; i<board.board.length || j<board.board.length; i++,j++) {
                if (board.board[i][j] != null && board.board[i][j].getColor() == this.getColor()) break;
                validMoves.add(i*10+j);
                if (board.board[i][j] != null && board.board[i][j].getColor() != this.getColor()) break;
                if (i==7||j==7) break;
            }}
        if (row==7||col==0){}
        else {
            for (int i = row+1,j = col-1; i<board.board.length || j>0; i++,j--) {
                if (board.board[i][j] != null && board.board[i][j].getColor() == this.getColor()) break;
                validMoves.add(i*10+j);
                if (board.board[i][j] != null && board.board[i][j].getColor() != this.getColor()) break;
                if (i==7||j==0) break;
            }}
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
