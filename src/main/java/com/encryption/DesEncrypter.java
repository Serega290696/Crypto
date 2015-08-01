package main;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class DesEncrypter {
    Cipher ecipher;
    Cipher dcipher;


    public DesEncrypter(String key1) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        byte[] keyBytes = key1.getBytes();
        SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");


        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = ecipher.doFinal(utf8);
        return new sun.misc.BASE64Encoder().encode(enc);
    }


    public String decrypt(String str) throws IOException, IllegalBlockSizeException, BadPaddingException {
        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        byte[] utf8 = dcipher.doFinal(dec);
        return new String(utf8, "UTF8");
    }
    public static void main(String[] s) throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException ,FileNotFoundException {
        String text = "a";
        String text1 = "b";
        String key1 = "c";
        String text3 = "d";
        Boolean tree = false;

        System.out.println("Enter key");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        key1 = in.readLine();
        while (key1.length() != 8) {
            System.out.println("Enter key with length = 8");
            key1 = in.readLine();
        }
        DesEncrypter encrypter = new DesEncrypter(key1);
        System.out.println(key1);
        System.out.println();

        System.out.println("Do you want enter text: true?");
        BufferedReader in4 = new BufferedReader(new InputStreamReader(System.in));
        tree = Boolean.valueOf(in.readLine());
        if (tree == true) {
            System.out.println("Enter encrypt text");

            BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
            text = in2.readLine();
            while (text.length() >= 56) {

                System.out.println("Enter text with length < 56");
                text = in.readLine();

            }


        } else {
            System.out.println("Enter encrypt file");

            BufferedReader in5 = new BufferedReader(new InputStreamReader(System.in));
            text = in5.readLine();

            //  text = "C:\\Users\\Вова\\Documents\\Codemasters\\Java работа с файлами\\a.txt";
            String textFromFile = FileWorker.read(text);
            System.out.println(textFromFile);

            while (textFromFile.length() >= 56) {

                System.out.println("Enter file  with text which  length < 56");
                text = in5.readLine();
                textFromFile = FileWorker.read(text);
                System.out.println(textFromFile);

            }

        }
        String SStr = encrypter.encrypt(text);
        System.out.println(SStr);
        System.out.println();

        System.out.println("Enter decrypt text");


        //BitArray bit = new BitArray(8);
       /*  public BitArray {

            BitArray bit2 = new BitArray(8);
            return ;
        }*/
        text1 = null;
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
        text1 = in1.readLine();


       /* String str =text1;
        ArrayList<byte[]> list = new ArrayList<byte[]>();
        for (int i = 0; i < str.length(); i++) {
            if(i % 8 == 0)
                list.add(new byte[8]);
            list.get(list.size()-1)[i % 8] = (byte) str.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i ++) {
            sb.append(encrypter.decrypt(new String(list.get(i))));
        }
*/

            String OStr2 = encrypter.decrypt(text1);
            System.out.println("Open String:" + text +
                    "\nAfter encripting: " + SStr + "\nAfter decripting: " + OStr2);
        }
    }
