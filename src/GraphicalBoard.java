import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Objects;

class GraphicalBoard {
    Pawn w_pawn1 = new Pawn(Color.WHITE, 0, 6);
    Pawn w_pawn2 = new Pawn(Color.WHITE, 1, 6);
    Pawn w_pawn3 = new Pawn(Color.WHITE, 2, 6);
    Pawn w_pawn4 = new Pawn(Color.WHITE, 3, 6);
    Pawn w_pawn5 = new Pawn(Color.WHITE, 4, 6);
    Pawn w_pawn6 = new Pawn(Color.WHITE, 5, 6);
    Pawn w_pawn7 = new Pawn(Color.WHITE, 6, 6);
    Pawn w_pawn8 = new Pawn(Color.WHITE, 7, 6);
    Pawn b_pawn1 = new Pawn(Color.BLACK, 0, 1);
    Pawn b_pawn2 = new Pawn(Color.BLACK, 1, 1);
    Pawn b_pawn3 = new Pawn(Color.BLACK, 2, 1);
    Pawn b_pawn4 = new Pawn(Color.BLACK, 3, 1);
    Pawn b_pawn5 = new Pawn(Color.BLACK, 4, 1);
    Pawn b_pawn6 = new Pawn(Color.BLACK, 5, 1);
    Pawn b_pawn7 = new Pawn(Color.BLACK, 6, 1);
    Pawn b_pawn8 = new Pawn(Color.BLACK, 7, 1);

    public JButton[][] board = new JButton[8][8];
    public ImageIcon w_pawn;
    public ImageIcon w_rook;
    public ImageIcon w_bishop;
    public ImageIcon w_horse;
    public ImageIcon w_king;
    public ImageIcon w_queen;
    public ImageIcon b_pawn;
    public ImageIcon b_rook;
    public ImageIcon b_bishop;
    public ImageIcon b_horse;
    public ImageIcon b_king;
    public ImageIcon b_queen;

    public GraphicalBoard() {
        JFrame desk = new JFrame();
        desk.setTitle("Chess");
        desk.setSize(480, 480);
        desk.setLayout(new GridLayout(8, 8));
        JButton square;
        desk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desk.setVisible(true);
        try {
            w_pawn = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/w_pawn.png"))));
            w_rook = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/w_rook.png"))));
            w_bishop = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/w_slon.png"))));
            w_horse = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/w_horse.png"))));
            w_king = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/w_king.png"))));
            w_queen = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/w_qween.png"))));
            b_pawn = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/b_pawn.png"))));
            b_rook = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/b_rook.png"))));
            b_bishop = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/b_slon.png"))));
            b_horse = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/b_horse.png"))));
            b_king = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/b_king.png"))));
            b_queen = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("Images/b_qween.png"))));
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                square = new JButton();
                this.board[i][j] = square;
                square.setSize(60, 60);
                square.setVisible(true);
                if ((i == 0 && j == 0) || (i == 0 && j == 7)) {
                    this.board[i][j].setIcon(b_rook);
                } else if ((i == 0 && j == 1) || (i == 0 && j == 6)) {
                    this.board[i][j].setIcon(b_horse);
                } else if ((i == 0 && j == 2) || (i == 0 && j == 5)) {
                    this.board[i][j].setIcon(b_bishop);
                } else if (i == 0 && j == 3) {
                    this.board[i][j].setIcon(b_queen);
                } else if (i == 0) {
                    this.board[i][j].setIcon(b_king);
                } else if (i == 1) {
                    this.board[i][j].setIcon(b_pawn);
                }
                if ((i == 7 && j == 0) || (i == 7 && j == 7)) {
                    this.board[i][j].setIcon(w_rook);
                } else if ((i == 7 && j == 1) || (i == 7 && j == 6)) {
                    this.board[i][j].setIcon(w_horse);
                } else if ((i == 7 && j == 2) || (i == 7 && j == 5)) {
                    this.board[i][j].setIcon(w_bishop);
                } else if (i == 7 && j == 3) {
                    this.board[i][j].setIcon(w_queen);
                } else if (i == 7) {
                    this.board[i][j].setIcon(w_king);
                } else if (i == 6) {
                    this.board[i][j].setIcon(w_pawn);
                }
                desk.add(this.board[i][j]);
            }
        }
        desk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desk.setVisible(true);
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        this.board[i][j].setBackground(Color.white);
                    } else this.board[i][j].setBackground(new Color(0x849CFF01, true));
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        this.board[i][j].setBackground(new Color(0x849CFF01, true));
                    } else this.board[i][j].setBackground(Color.white);
                }
            }
        }
    }
    public JButton selectedButton = null;

    public void setSelectedFigure(int row,int col){
        this.board[row][col].setBackground(Color.CYAN);
    }
    public void setEnabledTurns(HashSet<Integer> set){
        for (int e: set) {
            this.board[e/10][e%10].setBackground(Color.CYAN);
            this.board[e/10][e%10].setText(e/10 +""+ e%10);
        }
    }
    public void moveFigure(Piece piece, int to){
        board[to/10][to%10].setIcon(board[piece.row][piece.col].getIcon());
      board[piece.row][piece.col].setIcon(null);
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].getText() != null)board[i][j].setText("");
                    if (j % 2 == 0) {
                        this.board[i][j].setBackground(Color.white);
                    } else this.board[i][j].setBackground(new Color(0x849CFF01, true));
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].getText() != null)board[i][j].setText("");
                    if (j % 2 == 0) {
                        this.board[i][j].setBackground(new Color(0x849CFF01, true));
                    } else this.board[i][j].setBackground(Color.white);
                }
            }
        }
    }
    public void repaint(Board board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            this.board[i][j].setIcon(null);
            if (board.board[i][j] != null) {
                if (board.board[i][j].getName().equals("w_pawn")) this.board[i][j].setIcon(w_pawn);
                if (board.board[i][j].getName().equals("w_rook")) this.board[i][j].setIcon(w_rook);
                if (board.board[i][j].getName().equals("w_knight")) this.board[i][j].setIcon(w_horse);
                if (board.board[i][j].getName().equals("w_bishop")) this.board[i][j].setIcon(w_bishop);
                if (board.board[i][j].getName().equals("w_queen")) this.board[i][j].setIcon(w_queen);
                if (board.board[i][j].getName().equals("w_king")) this.board[i][j].setIcon(w_king);
                if (board.board[i][j].getName().equals("b_pawn")) this.board[i][j].setIcon(b_pawn);
                if (board.board[i][j].getName().equals("b_rook")) this.board[i][j].setIcon(b_rook);
                if (board.board[i][j].getName().equals("b_knight")) this.board[i][j].setIcon(b_horse);
                if (board.board[i][j].getName().equals("b_bishop")) this.board[i][j].setIcon(b_bishop);
                if (board.board[i][j].getName().equals("b_queen")) this.board[i][j].setIcon(b_queen);
                if (board.board[i][j].getName().equals("b_king")) this.board[i][j].setIcon(b_king);
            }
            }
        }
    }
    public void cleanIcons() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j].setIcon(null);
            }
        }
    }

}
