package encryption;

/**
 * Created by Serega on 22.07.2015.
 */
public interface ICryptographer {

    String encrypt(String text, String... keys);

    String decrypt(String text, String... keys);

    String setKey(String key1);
}
