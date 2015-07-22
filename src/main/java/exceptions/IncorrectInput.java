package exceptions;

/**
 * Created by Serega on 21.07.2015.
 */
public class IncorrectInput extends Exception {

    public IncorrectInput(String msg) {
        System.err.println("Incorrect input!\n" + msg);
    }
    public IncorrectInput() {
        System.err.println("Incorrect input!\n" +
                "Input values from keyboard: '1', '2', '3' etc. Please, try again.\n" +
                "Press '0' to exit.");
    }
}
