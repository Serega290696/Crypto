package com.encryption;

/**
 * Created by Вова on 26.07.2015.
 */
import com.encryption.hashing.HashingMD5;
import com.exceptions.EncryptionException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesEncrypter implements ICryptographer {

    private HashingMD5 hashing = new HashingMD5();
    private int defaultKeyLength = 22;

    public String encrypt(String text, String... keys) {
        String secretKey = setKey(keys[0]);
        byte[] raw;
        String encryptedString;
        SecretKeySpec skeySpec;
        byte[] encryptText = text.getBytes();
        Cipher cipher;
        try {
            raw = Base64.decodeBase64(secretKey);
            skeySpec = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return encryptedString;
    }

    public String decrypt(String text, String... keys) {
        String secretKey = setKey(keys[0]);
        Cipher cipher;
        String encryptedString;
        byte[] encryptText = null;
        byte[] raw;
        SecretKeySpec skeySpec;
        try {
            raw = Base64.decodeBase64(secretKey);
            skeySpec = new SecretKeySpec(raw, "AES");
            encryptText = Base64.decodeBase64(text);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            encryptedString = new String(cipher.doFinal(encryptText));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return encryptedString;
    }

    private String setKey(String key) {
        String tmpStr = hashing.toHashCode(key);
        if(tmpStr.length() < defaultKeyLength) try {
            throw new EncryptionException("Length of hash code your key too short.\nNeed: " + defaultKeyLength + "\nCurrent: " + tmpStr.length());
        } catch (EncryptionException e) {
//            e.printStackTrace();
        }
        return tmpStr.substring(tmpStr.length()-defaultKeyLength-1, tmpStr.length()-1);
    }


}