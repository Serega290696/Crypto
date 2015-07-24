package com.encryption;

/**
 * Created by Serega on 22.07.2015.
 */
public class CryptographerExam implements ICryptographer{
    @Override
    public String encrypt(String text, String... keys) {
        return text = text + keys[0];
    }

    @Override
    public String decrypt(String text, String... keys) {
        return text.substring(0, text.length() - keys[0].length());
    }
}
