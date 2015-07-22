import console_interface.Console;
import data_base.entities.User;

/**
 * Created by Serega on 19.07.2015.
 */
public class MainApp {


    static final User curUser = null;
    private static Console console = new Console(curUser);


    public static void main(String[] args) {
//
//        Arrays.asList(new String[]
//                        {
//                                "aaa2",
//                                "bbb2",
//                                "ccc2",
//                        }
//        ).stream().
//                forEach(new MainApp()::print);
//
//        new ArrayList<String>() {{
//            {
//                add("aaa3");
//                add("bbb3");
//                add("ccc3");
//            }
//        }}.
//                stream().
//                forEach(new MainApp()::print);

        console.mainAlgorithm();


    }


}
