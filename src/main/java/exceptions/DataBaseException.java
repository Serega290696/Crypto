package exceptions;

/**
 * Created by Serega on 24.07.2015.
 */
public class DataBaseException extends Exception{

    public DataBaseException(String msg) {
        System.err.println(msg);
    }
}
