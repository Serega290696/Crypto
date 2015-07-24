package com.encryption;

import com.exceptions.EncryptionException;

/**
 * Created by Serega on 22.07.2015.
 */
public class CipherWorker {

    private String key = "";
    private String text = "";

    private ICryptographer crypto = new CryptographerExam();

    public CipherWorker() {
    }

    public CipherWorker(String key) {
        this.key = key;
    }

    public String encrypt(String text, String key) throws EncryptionException {
        if (isReady(text, key))
            text = crypto.encrypt(text, key);
        else
            throw new EncryptionException();
        return text;
    }

    public String decrypt(String text, String key) throws EncryptionException {
        if (isReady(text, key))
            text = crypto.decrypt(text, key);
        else
            throw new EncryptionException("Your key: '" + key + "" + "'. Your text: '" + text + "'. Your " + (key.length() > 0 ? "text" : "key") + " is missing!");
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
