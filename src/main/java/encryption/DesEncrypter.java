package encryption;

import encryption.hashing.HashingMD5;
import exceptions.EncryptionException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DesEncrypter implements ICryptographer {
    Cipher ecipher;
    Cipher dcipher;
    private HashingMD5 hashing = new HashingMD5();
    private int defaultKeyLength = 8;


    public String setKey(String key1) {
        String tmpStr = hashing.toHashCode(key1);
        if(tmpStr.length() < defaultKeyLength) try {
            throw new EncryptionException("Length of hash code your key too short.\nNeed: " + defaultKeyLength + "\nCurrent: " + tmpStr.length());
        } catch (EncryptionException e) {
//            e.printStackTrace();
        }
        key1 = tmpStr.substring(tmpStr.length()-defaultKeyLength-1, tmpStr.length()-1);
        byte[] keyBytes = key1.getBytes();
        SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");


        try {
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return tmpStr;
    }

    public String encrypt(String text, String... keys) {
        setKey(keys[0]);

        byte[] utf8 = new byte[0];
        try {
            utf8 = text.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] enc = new byte[0];
        try {
            enc = ecipher.doFinal(utf8);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new sun.misc.BASE64Encoder().encode(enc);
    }


    public String decrypt(String text, String... keys) {
        setKey(keys[0]);
        byte[] dec = new byte[0];
        try {
            dec = new sun.misc.BASE64Decoder().decodeBuffer(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] utf8 = new byte[0];
        try {
            utf8 = dcipher.doFinal(dec);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            System.err.println("Invalid key!");
            return "";
//            e.printStackTrace();
        }
        try {
            return new String(utf8, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
//  public void main(String[] args){
//      String secretKey = "XMzDdG4D03CKm2IxIWQw7g==";
//       String value1= "ABCD";
//
//      String a1 = encrypt(value1,secretKey);
//  }

}
