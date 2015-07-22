package exceptions;

/**
 * Created by Serega on 22.07.2015.
 */
public class EncryptionException extends Exception {
    public EncryptionException(String message) {
        System.err.println("Exception during encryption! " + message);
    }

    public EncryptionException() {
        this("");
    }
}
