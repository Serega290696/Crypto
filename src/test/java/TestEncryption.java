import com.encryption.AesEncrypter;
import com.encryption.DesEncrypter;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Serega on 27.07.2015.
 */
public class TestEncryption extends Assert {


    @Test
    public void testEncryptDes() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        DesEncrypter desEncrypter = new DesEncrypter();
//        DesEncrypter aesEncrypter = new DesEncrypter("123456789_123456");
        String toEnc = "HellO! My name Serega!";
        String encryptedText = desEncrypter.encrypt(toEnc, "123123123678");
        System.out.println(encryptedText);
    }
    @Test
    public void testDecryptDes() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        DesEncrypter desEncrypter = new DesEncrypter();
        String toDec = "GL6aDMl3EARCoDGgHTjGUe+DV/ps6olp";
        String decryptedText = desEncrypter.decrypt(toDec, "123123123678");
        System.out.println(decryptedText);
    }

    @Test
    public void testEncryptAes() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        AesEncrypter aes = new AesEncrypter();
        String toEnc = "Here my secret text!";
        String encryptedText = aes.encrypt(toEnc, "123456756789_123");
        System.out.println(encryptedText);
    }
    @Test
    public void testDecryptAes() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        AesEncrypter aes = new AesEncrypter();
        String toDec = "INGXr7UJJN8phyWSgVRmBvxS6nEurMSuDHqFjg+1dvw=";
        String decryptedText = aes.decrypt(toDec, "123456756789_123");
        System.out.println(decryptedText);
    }

}
