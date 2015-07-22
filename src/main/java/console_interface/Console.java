package console_interface;

import data_base.data_access_objects.UsersDAO;
import data_base.entities.User;
import exceptions.IncorrectInput;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by Serega on 22.07.2015.
 */
public class Console {

    private User curUser = null;
    private Menu menu = Menu.GREETINGS;
    private UsersDAO usersDao = new UsersDAO();
    private Scanner scanner = new Scanner(System.in);

    private static final String SPLIT = "===========================================";

    private int curStringNumber = 1;
    private boolean isExit = false;
    private int inputValue;

    public Console() {
        this(null);
    }

    public Console(User curUser) {
        this.curUser = curUser;
        isExit = false; // на всякий случай ;)
    }

    public void mainAlgorithm() {
//        Stream.of(
//                "aaa2",
//                "bbb2",
//                "ccc2"
//        ).
//                forEach(new Console()::choiceListPrinter);

        while (isExit) {
            switch (menu) {
                case GREETINGS:
                    greetings();
                    break;
                case ACTION_CHOICE:
                    chooseAction();
                    break;
                case SOURCE_CHOICE:
                    chooseSource();
                    break;

                case SIGN_IN:
                    signIn();
                    isExit = true;
                    break;
                case SIGN_UP:
                    signUp();
                    isExit = true;
                    break;

                case ENCRYPT:
                    encrypt();
                    isExit = true;
                    break;
                case DECRYPT:
                    decrypt();
                    isExit = true;
                    break;

                case EXIT:
                    isExit = true;
                    break;
                default:

                    break;
            }
        }
        System.out.println("Goodbye" + ((curUser != null) ? ", " + curUser.getName() : "") + ".");
    }


    public void greetings() {
        System.out.println(SPLIT + menu.name());
        System.out.println("Hello" + ((curUser != null) ? ", " + curUser.getName() : "") + "!");

        if (curUser == null) {
            Stream.of(
                    "Sign in",
                    "Sign up",
                    "Exit",
                    "Input [1]:"
            ).
                    forEach(new Console()::choiceListPrinter);
            getInput();
        } else {
            menu = Menu.ACTION_CHOICE;
        }
    }

    public void signIn() {
        System.out.println(SPLIT + menu.name());
        System.out.println("Input login: ");
        System.out.println("Input password: ");
        String inputLogin = "", inputPassword = "";
        usersDao.getUser(new User(inputLogin, inputPassword));
        if (menu.getSubmenu().size() >= --inputValue)
            menu = menu.getSubmenu().get(inputValue);
        else {
            menu = Menu.ACTION_CHOICE;
        }

    }

    private void signUp() {
    }

    public void chooseAction() {
        System.out.println(SPLIT + menu.name());
        Stream.of(
                "Encrypt something",
                "Decrypt something",
                "Sign out",
                "Input [1]:"
        ).
                forEach(new Console()::choiceListPrinter);
        getInput();
    }

    private void getInput() {
        int inputValue;
        while (true) {
            inputValue = scanner.nextInt() - 1;
            if (menu.getSubmenu().size() >= inputValue && inputValue >= 0) {
                menu = menu.getSubmenu().get(inputValue);
                if(inputValue == -1 || menu == Menu.EXIT) isExit = true;
                return;
            } else
                try {
                    throw new IncorrectInput();
                } catch (IncorrectInput incorrectInput) {
                    incorrectInput.printStackTrace();
                }
        }
    }

    private void encrypt() {
    }

    private void decrypt() {
    }

    public void chooseSource() {
        System.out.println(SPLIT + menu.name());
        System.out.println("Choose your source:");
        Stream.of(
                "Input in console",
                "From file",
                "From saved notes",
                "Input [1]: "
        ).
                forEach(new Console()::choiceListPrinter);
        getInput();
    }

    private void choiceListPrinter(String s) {
        if (s.contains("nput")) System.out.println(s);
        else System.out.println(curStringNumber++ + ". " + (s) + ".");
    }
}
