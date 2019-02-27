/*
  This file provided by professor
*/

package cpsc2150.connectX;

/**
 * The controller class handles communication between View and Model
 */

public class ConnectXController {
    //current game that is being played
    private IGameBoard curGame;

    //The screen that provides view
    private ConnectXView screen;

    public static final int MAX_PLAYERS = 10;

    //play tokens are hard coded (could make a screen to get those from the user)
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'};

    private int numPlayers;

    //private variable to check if game is over or not
    private boolean gameOver = false;

    private int turnCount = 0;

    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
    }

    /**
     *
     *
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {

        //checks to see if game is over, and if so, starts a new game
        if (gameOver) {
            newGame();
        }
        //executes if the game is not over
        else {
            //sets correct message for player's turn
            screen.setMessage("It is " + players[(turnCount + 1) % numPlayers] + "'s turn!");

            //will not perform any action other than print error message if column is full
            if (!curGame.checkIfFree(col)) {
                screen.setMessage("Column is full");
            }

            //will execute if column is not full
            else {
                //variable for row number to place marker in
                int rowNum = 0;
                //loop through column to find open row
                while (curGame.whatsAtPos(rowNum,col) != ' ') {
                    rowNum += 1;
                }

                //place token into the curGame object
                curGame.placeToken(players[turnCount % numPlayers], col);

                //place token on the screen
                screen.setMarker(rowNum, col, players[turnCount % numPlayers]);

                //print message for winner
                if (curGame.checkForWin(col)) {
                    screen.setMessage("Player " + players[turnCount % numPlayers] + " wins! Click any button to start a new game");
                    gameOver = true;
                }

                //print message for tie game
                else if (curGame.checkTie()) {
                    screen.setMessage("Tie game! Click any button to start a new game");
                    gameOver = true;
                }

                //increment turn counter
                turnCount += 1;
            }
        }

    }

    /**
     * Starts a new game by returning to the setup screen and controller
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
