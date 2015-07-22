import console_interface.Console;
import data_base.entities.User;

import java.io.IOException;

/**
 * Created by Serega on 19.07.2015.
 */
public class MainApp {


    static final User curUser = null;
    private static Console console = new Console(curUser);


    public static void main(String[] args) {

        try {
            console.mainAlgorithm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
