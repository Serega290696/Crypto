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
    public void testEncrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        DesEncrypter desEncrypter = new DesEncrypter("12345678");
//        DesEncrypter aesEncrypter = new DesEncrypter("123456789_123456");
        String toEnc = "HellO! My name Serega!";
        String encryptedText = desEncrypter.encrypt(toEnc);
        System.out.println(encryptedText);
    }

    @Test
    public void testDecrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        DesEncrypter desEncrypter = new DesEncrypter("12345678");
        String toDec = "528N+7sURhb9zgI5JT7Qdqu4G5Tgje7H";
        String decryptedText = desEncrypter.decrypt(toDec);
        System.out.println(decryptedText);
    }

}
