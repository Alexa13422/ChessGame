import java.awt.*;
import java.util.HashSet;

class Bishop extends Piece implements Move {
    String name;
    public Bishop(Color color, int row, int col) {
        super(color, row, col);
        if (color == Color.WHITE) name ="w_bishop";
        else name = "b_bishop";
    }

    @Override
    public HashSet<Integer> getValidMoves(Color color, Board board) {
        HashSet<Integer> validMoves = new HashSet<>();
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
