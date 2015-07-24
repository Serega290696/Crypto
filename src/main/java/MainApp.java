import console_interface.AppInterface;
import console_interface.Console;
import data_base.entities.User;

/**
 * Created by Serega on 19.07.2015.
 */
public class MainApp {


    static final User curUser = null;
    private static AppInterface appInterface = new Console(curUser);


    public static void main(String[] args) {


        appInterface.launch();


    }


}
