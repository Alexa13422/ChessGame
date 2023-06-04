# ChessGame
Chess game with console moving of pieces, saving data about board to the binary file and oportunity to load saved board on your gaming board.
1. Whole game logicaly build on Board class with 8x8 board of type Piece[][]. To start game you must simply start the Main class.
2. Each figure has its coordinates (row and column) to select and move them we must choose their coordinates in ab format where a-row, b-column(note that board starts with 00 coor. of black left rook). Then we choose destination of selected figure from valid moves.
3. Each figure type extends class Piece and implements move interface. Logic of moves bases on valid moves of each figure, that are computed in accordance with chess game rules.
4. There are also avaliable such turns like castle, pawn transformation, check and mat.
5. In the start of each turn we can type 8 to save current board and 9 to load last saved board. After loading its white turn.

 Thank you for the attencion:)
