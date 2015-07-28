package com.encryption;

/**
 * Created by Вова on 26.07.2015.
 */
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

public class AesEncrypter {
    public static String symmetricEncrypt(String text, String secretKey) {
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

    public static String symmetricDecrypt(String text, String secretKey) {
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

    public static void main(String[] args) throws IOException {
       // String secretKey = "XMzDdG4D03CKm2IxIWQw7g==";
      /*  String value1= "ABCD";
        String enctypedValue1= "3uweh4pzoVyH1uODQmVNJA==";
        String enctypedValue2= "37PTC20w4DMZYjG3f+GWepSvAbEJUccMXwS/lXilLav1qM/PrCTdontw5/82OdC1zzyhDEsFVRGo rV6gXAQcm+Zai15hliiUQ8l8KRMtUl4=";
        String value4= "20000";
        String value5= "prosto proverka koda";
        String text;
        String answer;
        String secretKey1 = null;


        System.out.println("Do you want enter the key?") ;
        System.out.println("1:Yes") ;
        System.out.println("2:No") ;
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
        answer = in1.readLine();
        if(answer.equals("Yes")||answer.equals("yes")) {


            System.out.println("Enter key with 22 length");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            secretKey1 = in.readLine();
            System.out.println("Your key is:"+secretKey1);
        }else{
            secretKey1 = "XMzDdG4D03CKm2IxIWQw7g==";

            System.out.println("Your key is:"+secretKey1);
        }


        System.out.println("Enter encrypt text") ;
        BufferedReader in5 = new BufferedReader(new InputStreamReader(System.in));
        text = in5.readLine();

        String enc1 = symmetricEncrypt(text, secretKey1);
        String dec1 = symmetricDecrypt(enc1, secretKey1);


        System.out.println("Encrypt text:"+enc1);
        System.out.println("Decrypt text:"+dec1);
*/
        }
    }
