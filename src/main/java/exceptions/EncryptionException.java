package exceptions;

/**
 * Created by Serega on 22.07.2015.
 */
public class EncryptionException extends Exception {
    public EncryptionException(String message) {
        System.err.println("Exception during com.encryption!\n" + message);
    }

    public EncryptionException() {
        this("");
    }
}
