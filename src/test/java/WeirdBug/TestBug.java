package WeirdBug;

import com.encryption.AesEncrypter2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ���� on 13.08.2015.
 */
public class TestBug extends Assert {

    @Test
    public void a() {
        System.out.println("Start");

        new AesEncrypter2();

        System.out.println("Finish!");
    }

}
