package console_interface;

import data_base.entities.User;
import encryption.CipherWorker;
import exceptions.EncryptionException;
import exceptions.IncorrectInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by Serega on 22.07.2015.
 */
public class Console {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private User curUser = null;
    private Menu menu = Menu.ACTION_CHOICE;
    //    private UsersDAO usersDao = new UsersDAO();
    private Scanner scanner = new Scanner(System.in);
    private CipherWorker crypto = new CipherWorker();

    private static final String SPLIT = "\n============================ ";
    private static final String SPLIT2 = " ============================\n";

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

    public void mainAlgorithm() throws IOException {
//        Stream.of(
//                "aaa2",
//                "bbb2",
//                "ccc2"
//        ).
//                forEach(new Console()::choiceListPrinter);

        while (!isExit) {
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
                    break;
                case SIGN_UP:
                    signUp();
                    break;

                case ENCRYPT:
                    encrypt();
                    break;
                case DECRYPT:
                    decrypt();
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
        System.out.println(SPLIT + menu.name() + SPLIT2);
        System.out.println("Hello" + ((curUser != null) ? ", " + curUser.getName() : "") + "!");

        if (curUser == null) {
            Stream.of(
                    "Sign in",
                    "Sign up",
                    "Exit",
                    "Enter [1]: "
            ).
                    forEach(new Console()::choiceListPrinter);
            getInput();
        } else {
            menu = Menu.ACTION_CHOICE;
        }
    }

    public void signIn() throws IOException {
        System.out.println(SPLIT + menu.name() + SPLIT2);

        System.out.println("Input login:");
        String inputLogin = br.readLine();
//check if does not exist
        System.out.println("Input password:");
        String inputPassword = br.readLine();

//        usersDao.getUser(new User(inputLogin, inputPassword));
        menu = Menu.GREETINGS;
//        if (menu.getSubmenu().size() >= --inputValue)
//            menu = menu.getSubmenu().get(inputValue);
//        else {
//            menu = Menu.ACTION_CHOICE;
//        }

    }

    private void signUp() {
    }

    public void chooseAction() {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        Stream.of(
                "Encrypt something",
                "Decrypt something",
                "Sign out",
                "Enter [1]: "
        ).
                forEach(new Console()::choiceListPrinter);
        getInput();
    }


    private void encrypt() throws IOException {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        int a = chooseSource();
        String key = "";
        String text = "";
        switch (a) {
            case 1:
                break;
            case 2:
                System.out.println("Enter key: ");
                key = br.readLine();
                System.out.println("Enter text to operation: ");
                text = br.readLine();
                break;
            case 3:
                break;
            default:
                return;
        }
        try {
            String encText = crypto.encrypt(text, key);
            System.out.println("Encrypted text: " + encText);
            System.out.println("Press 'Enter' to continue.");
            br.readLine();
        } catch (EncryptionException e) {
            e.printStackTrace();
        }
    }

    private void decrypt() throws IOException {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        int a = chooseSource();
        String key = "";
        String text = "";
        switch (a) {
            case 1:
                break;
            case 2:
                System.out.println("Enter key: ");
                key = br.readLine();
                System.out.println("Enter text to operation: ");
                text = br.readLine();
                break;
            case 3:
                break;
            default:
                return;
        }
        try {
            String decText = crypto.decrypt(text, key);
            System.out.println("Decrypted text: " + decText);
            System.out.println("Press 'Enter' to continue.");
            br.readLine();
        } catch (EncryptionException e) {
            e.printStackTrace();
        }
    }

    public int chooseSource() throws IOException {
//        System.out.println(SPLIT + menu.name());
        System.out.println("\n\nChoose source:");
        Stream.of(
                "From saved notes",
                "Input in console",
                "From file",
                "Enter [1]: "
        ).
                forEach(new Console()::choiceListPrinter);
//        String way = br.readLine();
        return getInput(false);
    }

    private int getInput() {
        return getInput(true);
    }

    private int getInput(boolean goToNextMenu) {
        int inputValue = 0;
        boolean correctInput = false;
        while (!correctInput) {
            String s = scanner.nextLine();
            correctInput = true;
            for (int i = 0; i < s.length(); i++)
                if ((int) s.charAt(i) < 48 || ((int) s.charAt(i)) > 57) {
                    correctInput = false;
                    break;
                }
            if (correctInput && s.length() <= 4)
                inputValue = Integer.parseInt(s);
            else {
                correctInput = false;
                try {
                    throw new IncorrectInput("But you enter text or too long number!");
                } catch (IncorrectInput incorrectInput) {
                    incorrectInput.printStackTrace();
                }
                continue;
            }
            if (menu.getSubmenu().size() >= inputValue && inputValue >= 0) {
                if (inputValue == 0) moveBack();
                if (goToNextMenu) menu = menu.getSubmenu().get(inputValue - 1);
                if (inputValue == -1 || menu == Menu.EXIT) isExit = true;
                return inputValue;
            } else
                try {
                    correctInput = false;
                    throw new IncorrectInput();
                } catch (IncorrectInput incorrectInput) {
                    incorrectInput.printStackTrace();
                }
        }
        return inputValue;
    }

    private void moveBack() {
        System.out.println("\n<- Back");
        for (Menu m : Menu.values()) {
            if (m.getSubmenu().contains(menu)) {
                this.menu = m;
                break;
            }
        }
    }

    private void choiceListPrinter(String s) {
        if (s.contains("nter")) System.out.println(s);
        else System.out.println(curStringNumber++ + ". " + (s) + ".");
    }
}
