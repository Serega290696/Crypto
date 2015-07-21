package exceptions;

/**
 * Created by Serega on 21.07.2015.
 */
public class IncorrectInput extends Exception {

    public IncorrectInput(String msg) {
        System.err.println("Incorrect input!\n" + msg);
    }
}
