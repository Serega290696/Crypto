import data_base.entities.User;
import exceptions.IncorrectInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Serega on 19.07.2015.
 */
public class MainApp {


    static final String split = "=====================================";
    static final User curUser = null;
    static final int action = 3;
    private int curStringNumber = 1;


    public static void main(String[] args) {

        Arrays.asList(new String[]
                        {
                                "aaa2",
                                "bbb2",
                                "ccc2",
                        }
        ).
                stream().
                forEach(new MainApp()::print);

        new ArrayList<String>() {{
            {
                add("aaa3");
                add("bbb3");
                add("ccc3");
            }
        }}.
                stream().
                forEach(new MainApp()::print);




        MainApp main = new MainApp();
        System.out.println(split);
        greetings();
        System.out.println(split);
        while (chooseAction()) ;
        System.out.println(split);
        while (chooseSource()) ;
    }

    private void print(String s) {
        if(s.contains("nput")) System.out.println(s);
        else System.out.println(curStringNumber++ + ". " + (s) + ".");
    }


    private static void greetings() {
        if (curUser == null) {
            System.out.println("Hello!");
            System.out.println("You must sign in or sign up.");
            new ArrayList<String>() {{
                {
                    add("Sign in");
                    add("Sign up");
                    add("Exit");
                    add("Input [1]:");
                }
            }}.
                    stream().
                    forEach(new MainApp()::print);
//            System.out.println("Input [1]:");
        } else {
            System.out.println("Hello, " + curUser.getName() + "!");
            System.out.println("You can: " +
                    "1. Encrypt something." +
                    "2. Decrypt something." +
                    "3. Sign out." +
                    "Input [1]:");
        }

    }
//a
    private static boolean chooseAction() {
        switch (action) {
            case 1:
                System.out.println("You choose \"Encrypt something\".");
                System.out.println("Now input your text for encryption." +
                        "Choose your source:" +
                        "1. Input in console." +
                        "2. From file." +
                        "3. From saved notes." +
                        "Input [1]: ");

                break;
            case 2:
                break;
            case 3:
                break;
            default:
                try {
                    throw new IncorrectInput("Try again!");
                } catch (IncorrectInput incorrectInput) {
                    incorrectInput.printStackTrace();
                }
                return true;
        }
        return false;
    }

    private static boolean chooseSource() {
        switch (action) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
//                throw new IncorrectInput("Try again!");
                return true;
        }
        return false;
    }

    private static List<String> makeChoiceList(String[] strings) {
        int i = 1;
        for (String s : strings) {
            s = i + ". " + s;
            i++;
        }
        return Arrays.asList(strings);
    }

}
