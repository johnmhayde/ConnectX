package cpsc2150.connectX;

import java.util.*;

/**
 * Correspondence: row_count = ROW_COUNT
 * Correspondence: column_count = COL_COUNT
 * Correspondence: num_to_win = NUM_TO_WIN
 * Correspondence: game_type = GAME_TYPE
 *
 * @invariant MIN_SIZE <= ROW_COUNT <= MAX_SIZE
 * @invariant MIN_SIZE <= COL_COUNT <= MAX_SIZE
 * @invariant MIN_TO_WIN <= NUM_TO_WIN <= MAX_TO_WIN
 */

public class GameBoardMem implements IGameBoard {
    //private variables
    private static int ROW_COUNT;
    private static int COL_COUNT;
    private static int NUM_TO_WIN;
    //map to represent the board
    private static Map <Integer, List<Character>> BOARD_MAP;
    //public game type
    public static final String GAME_TYPE = "Mm";

    /**
     *
     * @param cols number of columns
     * @param rows number of rows
     * @param num_to_win number in a row needed to win game
     *
     * @post
     *    ROW_COUNT = rows and
     *    COL_COUNT = cols and
     *    NUM_TO_WIN = num_to_win
     */
    public GameBoardMem(int cols, int rows, int num_to_win) {
        ROW_COUNT = rows;
        COL_COUNT = cols;
        NUM_TO_WIN = num_to_win;
        BOARD_MAP = new HashMap<>();
    }

    public void placeToken(char p, int c) {
        //initialize a new list if the map does not contain one at c
        if (!BOARD_MAP.containsKey(c)) {
            BOARD_MAP.put(c, new ArrayList<>());
        }
        //add p to the list at key c
        BOARD_MAP.get(c).add(p);
    }

    public char whatsAtPos (int r, int c) {
        //return a blank space if the map doesn't contain a key at c
        if (!BOARD_MAP.containsKey(c)) {
            return ' ';
        }
        else {
            //returns a blank space if the row number is larger than the list size
            if (r >= BOARD_MAP.get(c).size()) {
                return ' ';
            }
            else {
                //returns the character at index r in key c
                return BOARD_MAP.get(c).get(r);
            }
        }
    }

    /**
     *
     * @return String version of the game board
     * @pre GameBoardMem has been initialized
     * @post returns a String version of the game board
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
