package cpsc2150.connectX;

/**
 * This class just loads the set up screen and controller
 */
public class ConnectXApp {

    public static void main(String [] args) {
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
