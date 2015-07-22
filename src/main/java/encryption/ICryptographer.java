package encryption;

/**
 * Created by Serega on 22.07.2015.
 */
public interface ICryptographer {

    public String encrypt(String text, String ... keys);
    public String decrypt(String text, String ... keys);

}
