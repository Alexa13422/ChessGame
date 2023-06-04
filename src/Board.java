import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.util.Objects;

public class Board {
    Piece[][] board = new Piece[8][8];
    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 7)) {
                    this.board[i][j] = new Rook(Color.BLACK,i,j);
                } else if ((i == 0 && j == 1) || (i == 0 && j == 6)) {
                    this.board[i][j] = new Knight(Color.BLACK,i,j);
                } else if ((i == 0 && j == 2) || (i == 0 && j == 5)) {
                    this.board[i][j] = new Bishop(Color.BLACK,i,j);
                } else if (i == 0 && j == 3) {
                    this.board[i][j] = new Queen(Color.BLACK,i,j);
                } else if (i == 0) {
                    this.board[i][j] = new King(Color.BLACK,i,j);
                } else if (i == 1) {
                    this.board[i][j] = new Pawn(Color.BLACK,i,j);
                }
                if ((i == 7 && j == 0) || (i == 7 && j == 7)) {
                    this.board[i][j] = new Rook(Color.WHITE,i,j);
                } else if ((i == 7 && j == 1) || (i == 7 && j == 6)) {
                    this.board[i][j] = new Knight(Color.WHITE,i,j);
                } else if ((i == 7 && j == 2) || (i == 7 && j == 5)) {
                    this.board[i][j] = new Bishop(Color.WHITE,i,j);
                } else if (i == 7 && j == 3) {
                    this.board[i][j] = new Queen(Color.WHITE,i,j);
                } else if (i == 7) {
                    this.board[i][j] = new King(Color.WHITE,i,j);
                } else if (i == 6) {
                    this.board[i][j] = new Pawn(Color.WHITE,i,j);
                }
            }
        }
    }
    public HashSet<Integer> getValidFigures(Player player){
        HashSet<Integer> validFigures = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getColor().equals(player.getColor())) {
                        validFigures.add(i * 10 + j);
                    }
                }
            }
        }
        return validFigures;
    }
    public Piece checkPosition(int position){
        return board[position/10][position%10];
    }

    public void setNewPosition(Piece figure,int moveTo){
        board[figure.row][figure.col] = null;
        figure.move(moveTo/10,moveTo%10);
        board[figure.row][figure.col] = figure;
    }
    public boolean isEmptySquare(int row, int col) {return this.board[row][col] == null; }

    public void pawnEvolution(Piece selectedFigure,int numer){
        if (selectedFigure.getColor() == Color.WHITE) {
            switch (numer) {
                case 1 -> board[selectedFigure.row][selectedFigure.col] = new Rook(Color.WHITE, selectedFigure.row, selectedFigure.col);
                case 2 -> board[selectedFigure.row][selectedFigure.col] = new Knight(Color.WHITE, selectedFigure.row, selectedFigure.col);
                case 3 -> board[selectedFigure.row][selectedFigure.col] = new Bishop(Color.WHITE, selectedFigure.row, selectedFigure.col);
                case 4 -> board[selectedFigure.row][selectedFigure.col] = new Queen(Color.WHITE, selectedFigure.row, selectedFigure.col);

            }
        } else {
            switch (numer) {
                case 1 -> board[selectedFigure.row][selectedFigure.col] = new Rook(Color.BLACK, selectedFigure.row, selectedFigure.col);
                case 2 -> board[selectedFigure.row][selectedFigure.col] = new Knight(Color.BLACK, selectedFigure.row, selectedFigure.col);
                case 3 -> board[selectedFigure.row][selectedFigure.col] = new Bishop(Color.BLACK, selectedFigure.row, selectedFigure.col);
                case 4 -> board[selectedFigure.row][selectedFigure.col] = new Queen(Color.BLACK, selectedFigure.row, selectedFigure.col);
            }
        }
    }
    public boolean Check(Player currentPlayer){
        Player enemy;
        if (currentPlayer.getColor() == Color.WHITE) enemy = new Player(Color.BLACK);
        else enemy = new Player(Color.WHITE);
        HashSet<Integer> validFigures,validMoves;
        validFigures = getValidFigures(enemy);
        for (int e:validFigures) {
            validMoves = checkPosition(e).getValidMoves(enemy.getColor(), this);
            if (validMoves.contains(getYourKing(currentPlayer))){
                return true;

            }
        }
        return false;
    }
    public boolean Mat(Player currentPlayer){
        Player enemy;
        if (currentPlayer.getColor() == Color.WHITE) enemy = new Player(Color.BLACK);
        else enemy = new Player(Color.WHITE);
        int king;
        HashSet<Integer> validFigures,kingMoves,validMoves;
        HashSet<Integer> res = new HashSet<>();
        king = getYourKing(currentPlayer);
        kingMoves = checkPosition(king).getValidMoves(currentPlayer.getColor(),this);
        kingMoves.add(getYourKing(currentPlayer));
        validFigures = getValidFigures(enemy);
        for (int e:validFigures) {
            validMoves = checkPosition(e).getValidMoves(enemy.getColor(), this);
            for (int a:kingMoves){
               if (validMoves.contains(a)) res.add(a);
            }
        }
        return res.equals(kingMoves);
    }
    public int getYourKing(Player currentPlayer){
        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!isEmptySquare(i,j) && currentPlayer.getColor() == Color.WHITE && Objects.equals(board[i][j].getName(), "w_king")){
                    return (board[i][j].row*10) + board[i][j].col;
                } else if (!isEmptySquare(i,j) && currentPlayer.getColor() == Color.BLACK && Objects.equals(board[i][j].getName(), "b_king")){
                    return (board[i][j].row*10) + board[i][j].col;
                }
            }
        }
        return 0;
    }
    public void binarySave() throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream("primes.bin"));
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    byte[] binaryFormat = new byte[2];
                    binaryFormat[0] = (byte) (findClass(piece) << 5);
                    binaryFormat[0] |= (byte) (piece.row << 1);
                    binaryFormat[1] = (byte) (piece.col << 4);
                    if (piece.getColor() == Color.WHITE) {
                        binaryFormat[1] |= 0x01;
                    } else if (piece.getColor() == Color.BLACK) {
                        binaryFormat[1] |= 0x00;
                    }
                    output.write(binaryFormat);
                }
            }
        }
    }
    public static byte[][] readBinaryFile(String filename) {
        byte[][] data = null;

        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            // Get the size of the file
            long fileSize = fileInputStream.available();

            // Calculate the number of rows in the 2D array
            int numRows = (int) (fileSize / 2);
            data = new byte[numRows][2];

            // Read bytes from the file and populate the 2D array
            for (int i = 0; i < numRows; i++) {
                fileInputStream.read(data[i]);
            }

        } catch (IOException e) {
            System.err.println("Error reading binary file: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }
    // Method to create a Piece object from binary format
    public static Piece[][] fromBinaryFormat(byte[][] binary) {
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < binary.length; i++) {

            // Extract the type of piece from the first 3 bits
            int type = (binary[i][0] & 0xE0) >> 5;

            // Extract the horizontal position from the next 4 bits
            int row = (binary[i][0] & 0x1E) >> 1;

            // Extract the vertical position from the next 4 bits
            int col = (binary[i][1] & 0xF0) >> 4;

            // Extract the color of the piece from the last bit
            boolean isWhite = (binary[i][1]%2 != 0);
            board[row][col] = Board.createPiece(type, row, col,isWhite);
        }
        return board;
    }
    public static Piece createPiece(int type,int row,int col,boolean isWhite){
        Piece piece;
        if (isWhite){
            switch (type){
                case 0: return piece = new Pawn(Color.WHITE,row,col);
                case 1: return piece = new King(Color.WHITE,row,col);
                case 2: return piece = new Queen(Color.WHITE,row,col);
                case 3: return piece = new Rook(Color.WHITE,row,col);
                case 4: return piece = new Bishop(Color.WHITE,row,col);
                case 5: return piece = new Knight(Color.WHITE,row,col);

            }
        } else {
            switch (type){
                case 0: return piece = new Pawn(Color.BLACK,row,col);
                case 1: return piece = new King(Color.BLACK,row,col);
                case 2: return piece = new Queen(Color.BLACK,row,col);
                case 3: return piece = new Rook(Color.BLACK,row,col);
                case 4: return piece = new Bishop(Color.BLACK,row,col);
                case 5: return piece = new Knight(Color.BLACK,row,col);

            }
        }
        return null;
    }
    public short findClass(Piece piece){
        if (piece instanceof Pawn){
            return 0;
        }
        if (piece instanceof King){
            return 1;
        }
        if (piece instanceof Queen){
            return 2;
        }
        if (piece instanceof Rook){
            return 3;
        }
        if (piece instanceof Bishop){
            return 4;
        }
        if (piece instanceof Knight) {
            return 5;
        }
        return 0;
    }
}

