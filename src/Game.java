import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Game {

    public Game() {
        Player playerW = new Player(Color.WHITE);
        Player playerB = new Player(Color.BLACK);
        GraphicalBoard graphicalBoard = new GraphicalBoard();
        Board board = new Board();

        int moveFrom,moveTo;
        Player currentPlayer = playerW;
        HashSet<Integer> validFigures;
        HashSet<Integer> validMoves;
        Piece selectedFigure;
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            validFigures = board.getValidFigures(currentPlayer);
            //is checked
            if (board.Check(currentPlayer)){
                System.out.println("You have shah, save your king " + board.getYourKing(currentPlayer));
            }
            if (currentPlayer.getColor().equals(Color.WHITE)) {
                System.out.println("White turn!");
            } else System.out.println("Black turn!");
            System.out.print("Enter your move(press: 8.for save board 9.for load board): ");
            moveFrom = scanner.nextInt();
            switch (moveFrom){
                case 8:
                    try {
                        board.binarySave();
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 9:
                    board.board = Board.fromBinaryFormat(Board.readBinaryFile("primes.bin"));
                    graphicalBoard.cleanIcons();
                    graphicalBoard.repaint(board);
                    break;
            }
            while(true) {
                if (validFigures.contains(moveFrom)) {
                    selectedFigure = board.checkPosition(moveFrom);
                    validMoves = selectedFigure.getValidMoves(selectedFigure.getColor(), board);
                    if (validMoves.isEmpty()) {
                        System.out.println("this figure dont have moves, select another");
                        moveFrom = scanner.nextInt();
                    } else break;
                } else {
                    System.out.println("You cant select this figure");
                    moveFrom = scanner.nextInt();
                }
            }
                System.out.println("Valid moves:");
                for (int e: validMoves) {
                    System.out.print(" "+ e);
                }
                System.out.println("");
                graphicalBoard.setEnabledTurns(validMoves);
                while (true){
                    int move = scanner.nextInt();
                    if (validMoves.contains(move)){
                        moveTo = move;
                        break;
                    } else {
//                        System.out.println("You can`t move there");
                        moveTo = move;
                        break;
                    }
                }
                //king save
            if (selectedFigure.getName().equals("w_king") && moveTo == 76 && board.board[7][7].getName().equals("w_rook")){
                graphicalBoard.moveFigure(board.checkPosition(77), 75);
                board.board[7][7].move(7,5);
                board.board[7][5] = board.board[7][7];
                board.board[7][7] = null;
            }
            if (selectedFigure.getName().equals("b_king") && moveTo == 6 && board.board[0][7].getName().equals("b_rook")){
                graphicalBoard.moveFigure(board.checkPosition(7), 5);
                board.board[0][7].move(0,5);
                board.board[0][5] = board.board[0][7];
                board.board[0][7] = null;
            }
                //moving
                graphicalBoard.moveFigure(selectedFigure,moveTo);
                board.setNewPosition(selectedFigure,moveTo);
            //pawn-evolution
            if (selectedFigure.getColor().equals(Color.WHITE) && selectedFigure.row == 0){
                System.out.println("select evolution: 1.rook 2.knight 3.bishop 4.queen");
                int numer = scanner.nextInt();
                board.pawnEvolution(selectedFigure,numer);
                graphicalBoard.repaint(board);
            }
            if (selectedFigure.getColor().equals(Color.BLACK) && selectedFigure.row == 7){
                System.out.println("select evolution: 1.rook 2.knight 3.bishop 4.queen");
                int numer = scanner.nextInt();
                board.pawnEvolution(selectedFigure,numer);
                graphicalBoard.repaint(board);
            }
            //is Mat
            if (board.Mat(currentPlayer)) {
                if (currentPlayer.getColor() == Color.WHITE) {
                    Endwindow endwindow = new Endwindow("BLACK");
                    break;
                }
                if (currentPlayer.getColor() == Color.BLACK) {
                    Endwindow endwindow = new Endwindow("WHITE");
                    break;
                }
            }
                currentPlayer = getNextPlayer(playerW,playerB);
        }
    }


    private Player getNextPlayer (Player p1, Player p2){
       if (p1.isTurn()){
           p1.setTurn(false);
           p2.setTurn(true);
           return p2;
       }
       else {
           p1.setTurn(true);
           p2.setTurn(false);
           return p1;
       }
    }
    private boolean isGameOver() {

        return false;
    }
}
