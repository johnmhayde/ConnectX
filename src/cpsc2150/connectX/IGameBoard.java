package cpsc2150.connectX;

/**
 * A game board for ConnectX containing characters. The size of the board is decided by the user when they run
 * the program, as is the number in a row needed to win. The purpose of ConnectX is to be the first player to try
 * and get a certain number of marks in a row on the board. You can win by having marks in a row horizontally,
 * vertically, or diagonally.
 *
 * The board is bounded by MIN_SIZE, MAX_SIZE, MIN_TO_WIN, and MAX_TO_WIN.
 *
 * Initialization ensures the game board is set to all blank spaces.
 *
 * Defines:
 *      row_count:R
 *      column_count:C
 *      num_to_win:N
 *      game_type:T
 *
 * Constraints:
 *      MIN_SIZE <= row_count <= MAX_SIZE
 *      MIN_SIZE <= column_count <= MAX_SIZE
 *      MIN_TO_WIN <= num_to_win <= MAX_TO_WIN
 *      MIN_NUM_PLAYERS <= num_players <= MAX_NUM_PLAYERS
 */

public interface IGameBoard {

    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 100;
    public static final int MIN_TO_WIN = 3;
    public static final int MAX_TO_WIN = 25;
    public static final int MIN_NUM_PLAYERS = 2;
    public static final int MAX_NUM_PLAYERS = 10;


    /**
     *
     * @param p player character
     * @param c column number
     * @pre p is a valid character and 0 <= c < COL_COUNT
     * @post [p is placed at next open spot in column]
     *
     */
    public void placeToken (char p, int c);

    /**
     *
     * @param r row number
     * @param c column number
     * @return [character that is located at board_matrix[r][c]]
     * @pre 0 <= r < ROW_COUNT and 0 <= c < COL_COUNT
     * @post [returns the character that is located at board_matrix[r][c]]
     *
     */
    public char whatsAtPos (int r, int c);

    /**
     *
     * @return number of rows on the board
     * @pre ROW_COUNT has been set
     * @post returns ROW_COUNT
     *
     */
    public int getNumRows();

    /**
     *
     * @return number of columns on the board
     * @pre COL_COUNT has been set
     * @post returns COL_COUNT
     *
     */
    public int getNumColumns();

    /**
     *
     * @return number of characters in-a-row needed to win the game
     * @pre NUM_TO_WIN has been set
     * @post returns NUM_TO_WIN
     *
     */
    public int getNumToWin();

    /**
     *
     * @param c column number
     * @return [true if there is open space in the column, false if the column is full]
     * @pre c >= 0
     * @post [returns true if there is open space in the column, false if the column is full]
     *
     */
    default boolean checkIfFree (int c) {
        //initialize variable to false assuming column is full
        boolean isFree = false;
        //loop through column
        for (int i = 0; i < getNumRows(); i++) {
            //check if matrix[i][c is free or not]
            if (whatsAtPos(i,c) == ' ') {
                //isFree = true if there is blank space in column
                isFree = true;
            }
        }
        return isFree;
    }

    /**
     *
     * @param c column number
     * @return [true is there are NUM_TO_WIN in a row of p in the array, false if not]
     * @pre 0 <= c < COL_COUNT
     * @post [returns true if there are NUM_TO_WIN in a row of p in the array, false if not]
     *
     */
    default boolean checkForWin (int c) {
        //initialize r to value of get_row(c)
        int i = 0;
        while (whatsAtPos(i, c) != ' ' && i < getNumRows() - 1) {
            i++;
        }
        int r = i - 1;
        //initialize player to whatsAtPos(r,c)
        char player = whatsAtPos(r,c);

        //check for vertical win
        if (checkVertWin(r, c, player)) {
            return true;
        }
        //check for horizontal win
        else if (checkHorizWin(r, c, player)) {
            return true;
        }
        //check for diagonal win
        else {
            return checkDiagWin(r, c, player);
        }
    }

    /**
     *
     * @param r row number
     * @param c column number
     * @param p player character
     * @return true if there are NUM_TO_WIN in a row in the row, false if not
     * @pre 0 <= r < ROW_COUNT and 0 <= c < COL_COUNT and [p is a valid character]
     * @post [returns true if there are NUM_TO_WIN in a row in the row, false if not]
     *
     */
    default boolean checkHorizWin (int r, int c, char p) {
        //hasWon = false to start, only true if programs finds 4 in a row
        boolean hasWon = false;
        //inARow = 0 to start
        int inARow = 0;

        //loops through row and checks for NUM_TO_WIN in a row
        for (int i = c - (getNumToWin() - 1); i < c + getNumToWin() && i < getNumColumns(); i++) {
            //ensures point is in bounds of the array
            if (i < 0) {
                i = 0;
            }
            //checks to see if whatsAtPos(r,i) == p and if so adds 1 to inARow
            if (whatsAtPos(r,i) == p) {
                inARow += 1;
                //if inARow == NUM_TO_WIN, returns true
                if (inARow == getNumToWin()) {
                    hasWon = true;
                }
            }
            //if the character is not = p, reset inARow count
            else {
                inARow = 0;
            }
        }
        //returns true if NUM_TO_WIN in a row were found, false if not
        return hasWon;
    }

    /**
     *
     * @param r row number
     * @param c column number
     * @param p player character
     * @return true if there are NUM_TO_WIN in a row in the column, false if not
     * @pre 0 <= r < ROW_COUNT and 0 <= c < COL_COUNT and [p is a valid character]
     * @post [returns true if there are NUM_TO_WIN in a row in the column, false if not]
     *
     */
    default boolean checkVertWin (int r, int c, char p) {
        //hasWon = false to start, only true if programs finds 4 in a row
        boolean hasWon = false;
        //inARow count = 0 to start
        int inARow = 0;

        //loops through column from position (r - (NUM_TO_WIN - 1)) while i < r + NUM_TO_WIN
        for (int i = r - (getNumToWin() - 1); i < r + getNumToWin() && i < getNumRows(); i++) {
            //ensures point is in bounds of the array
            if (i < 0) {
                i = 0;
            }
            //checks to see if whatsAtPos(i,c) == p and if so adds 1 to inARow
            if (whatsAtPos(i,c) == p) {
                inARow += 1;
                //if inARow == NUM_TO_WIN, returns true
                if (inARow == getNumToWin()) {
                    hasWon = true;
                }
            }
            //if the character is not = p, reset inARow count
            else {
                inARow = 0;
            }
        }
        //returns true if NUM_TO_WIN in a row were found, false if not
        return hasWon;
    }

    /**
     *
     * @param r row number
     * @param c column number
     * @param p player character
     * @return [true if there are NUM_TO_WIN in a row in any diagonal line of the point [r][c], false if not]
     * @pre 0 <= r < ROW_COUNT and 0 <= c < COL_COUNT and [p is a valid character]
     * @post [returns true if there are NUM_TO_WIN in a row in the diagonal, false if not]
     *
     */
    default boolean checkDiagWin (int r, int c, char p) {
        boolean hasWon = false;
        int inARow = 0;
        int j = c - (getNumToWin() - 1);

        //first diagonal (bottom left to top right)
        //loop from row - NUM_TO_WIN to row + NUM_TO_WIN
        for (int i = r - (getNumToWin() - 1); i < r + getNumToWin(); i++) {
            //ensure value is in array
            if (i >= 0 && i < getNumRows() && j >= 0 && j < getNumColumns()) {
                //check to see if p == whatsAtPos, increment inARow if true
                if (whatsAtPos(i,j) == p) {
                    inARow += 1;
                    //hasWon = true if there are NUM_TO_WIN in a row
                    if (inARow == getNumToWin()) {
                        hasWon = true;
                    }
                }
                //if p != whatsAtPos, reset inARow
                else {
                    inARow = 0;
                }
            }
            //increment j
            j += 1;
        }

        //second diagonal (top left to bottom right)
        //reset counter variables
        j = c - (getNumToWin() - 1);
        inARow = 0;
        //loop from row + NUM_TO_WIN to row - NUM_TO_WIN
        for (int i = r + (getNumToWin() - 1); i > r - getNumToWin(); i--) {
            //ensure value is in array
            if (i >= 0 && i < getNumRows() && j >= 0 && j < getNumColumns()) {
                //check to see if p == whatsAtPos, increment inARow if true
                if (whatsAtPos(i,j) == p) {
                    inARow += 1;
                    //hasWon = true is there are NUM_TO_WIN in a row
                    if (inARow == getNumToWin()) {
                        hasWon = true;
                    }
                }
                //if p != whatsAtPos, reset inARow
                else {
                    inARow = 0;
                }
            }
            //increment j
            j += 1;
        }

        return hasWon;
    }

    /**
     *
     * @return [true if there are no open spaces on the board, false if there are]
     * @pre [board_matrix is a valid 2D array] and [checkWin has been called] and move == ROW_COUNT * COL_COUNT
     * @post [returns true if there are no open spaces on the board, false if there are]
     *
     */
    default boolean checkTie () {
        //variable to count number of full columns
        int tieCount = 0;
        //loop through each column
        for (int i = 0; i < getNumColumns(); i++) {
            //if the column is not full add 1 to tie_count
            if (!checkIfFree(i)) {
                tieCount += 1;
            }
        }
        //return true if each column is full
        return tieCount == getNumColumns();
    }

}
