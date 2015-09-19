package encryption;

import exceptions.EncryptionException;
import org.apache.log4j.Logger;

/**
 * Created by Serega on 22.07.2015.
 */
public class CipherWorker {

    private static final Logger logger = Logger.getLogger("logEncryption");

    private String key = "";
    private String text = "";

    private ICryptographer crypto = new DesEncrypter();

    public CipherWorker() {
    }

    public CipherWorker(String key) {
        this.key = key;
    }

    public String encrypt(String text, String key) throws EncryptionException {
        logger.trace("User want ENCRYPT. Text: " + text + ". Key: ******. Cryptographer: " + crypto.getClass().getSimpleName());
        if (isReady(text, key))
            text = crypto.encrypt(text, key);
        else {
            if (text.length() == 0)
                logger.warn("Field \"Text\" is empty!");
            if (key.length() == 0)
                logger.warn("Field \"Key\" is empty!");
            throw new EncryptionException();
        }
        return text;
    }

    public String decrypt(String text, String key) throws EncryptionException {
        logger.trace("User want ENCRYPT. Text: " + text + ". Key: ******. Cryptographer: " + crypto.getClass().getSimpleName());
        if (isReady(text, key))
            text = crypto.decrypt(text, key);
        else {
            if (text.length() == 0)
                logger.warn("Field \"Text\" is empty!");
            if (key.length() == 0)
                logger.warn("Field \"Key\" is empty!");
            throw new EncryptionException("Your key: '" + key + "" + "'. Your text: '" + text + "'. Your " + (key.length() > 0 ? "text" : "key") + " is missing!");
        }
        return text;
    }


    private boolean isReady(String text, String key) {
        return (key.length() > 0 && text.length() > 0);
    }


    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }
}
