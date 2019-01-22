package cpsc2150.connectX;

/**
 * Correspondence: row_count = ROW_COUNT
 * Correspondence: column_count = COL_COUNT
 * Correspondence: num_to_win = NUM_TO_WIN
 * Correspondence: game_type = GAME_TYPE
 *
 * @invariant MIN_SIZE < ROW_COUNT < MAX_SIZE
 * @invariant MIN_SIZE < COL_COUNT < MAX_SIZE
 * @invariant MIN_TO_WIN < NUM_TO_WIN < MAX_TO_WIN
 */

public class GameBoard implements IGameBoard {
    //private variables
    private static int ROW_COUNT;
    private static int COL_COUNT;
    private static int NUM_TO_WIN;
    //2D array to represent the board
    private char [][] BOARD_MATRIX;
    //public game type
    public static final String GAME_TYPE = "Ff";

    /**
     *
     * @pre board_size > 0
     * @post [all members of board_matrix = ' ']
     *
     */
    private void initBoard() {
        //loops through each spot on the board and sets it equal to a blank space
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                BOARD_MATRIX[i][j] = ' ';
            }
        }
    }

    /**
     *
     * @param cols number of columns
     * @param rows number of rows
     * @param num_to_win number in a row needed to win game
     *
     * @post [init_board is called] and BOARD_MATRIX is initialized and
     *    ROW_COUNT = rows and
     *    COL_COUNT = cols and
     *    NUM_TO_WIN = num_to_win
     *
     */
    public GameBoard(int cols, int rows, int num_to_win) {
        ROW_COUNT = rows;
        COL_COUNT = cols;
        NUM_TO_WIN = num_to_win;
        BOARD_MATRIX = new char[ROW_COUNT][COL_COUNT];
        initBoard();
    }

    public void placeToken (char p, int c) {
        int i = 0;
        //loop through column to find open spot
        while (whatsAtPos(i,c) != ' ') {
            i++;
        }
        //place token at open spot in column
        BOARD_MATRIX[i][c] = p;
    }

    public char whatsAtPos (int r, int c) {
        return BOARD_MATRIX[r][c];
    }

    /**
     *
     * @return String version of the game board
     * @pre GameBoard has been initialized
     * @post returns a String version of the game board
     *
     */
    @Override public String toString () {
        //initializes string to first row of board
        String outString = "";

        //add first line of numbered columns
        for (int i = 0; i < getNumColumns(); i++) {
            if (i < 10) {
                outString += "| ";
                outString += i;
            }
            else {
                outString += "|";
                outString += i;
            }
        }
        outString += "|\n";

        //loops through rows backward so bottom of board is row 0
        for (int i = getNumRows() - 1; i >= 0; i--) {
            //loops through columns normally so left of board is 0
            for (int j = 0; j < getNumColumns(); j++) {
                //adds "|" and member of board_matrix to the string
                outString += "|";
                outString += whatsAtPos(i, j) + " ";
            }
            //adds extra "|" and newline to string
            outString += ("|\n");
        }
        return outString;
    }

    public int getNumRows() {
        return ROW_COUNT;
    }

    public int getNumColumns() {
        return COL_COUNT;
    }

    public int getNumToWin() {
        return NUM_TO_WIN;
    }

}
