package console_interface;

import console_interface.enumeration.Menu;
import console_interface.enumeration.TextSource;
import data_base.data_access_objects.NotesDAO;
import data_base.data_access_objects.UsersDAO;
import data_base.entities.User;
import encryption.CipherWorker;
import encryption.hashing.HashingMD5;
import exceptions.EncryptionException;
import exceptions.IncorrectInput;
import file_worker.FileWorker;
import file_worker.IFileWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Created by Serega on 22.07.2015.
 */
public class Console implements AppInterface {

    private static final Logger log = Logger.getLogger("mylogger");
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private User curUser = null;
    private Menu menu = Menu.ACTION_CHOICE;
    private Scanner scanner = new Scanner(System.in);

    private CipherWorker crypto = new CipherWorker();
    private HashingMD5 hashing = new HashingMD5();

    private IFileWorker fileWorker = new FileWorker();
    private UsersDAO usersDAO = new UsersDAO();
    private NotesDAO notesDAO = new NotesDAO();

    private static final String SPLIT = "\n============================ ";
    private static final String SPLIT2 = " ============================\n";

    private int curStringNumber = 1;
    private boolean isExit = false;
    private int inputValue;

    public Console() {
        this(null);
    }

    public Console(User curUser) {
        System.out.println("* Console interface created");
        this.curUser = curUser;
        isExit = false; // �� ������ ������ ;)
    }

    public void launch() {
        System.out.println(SPLIT + SPLIT);
        System.out.println("* Console interface launched");
        System.out.println(SPLIT + SPLIT);
        log.info("I`m here!");
        log.warning("Some errors!");
//        log.t("Some errors!");
//        Stream.of(
//                "aaa2",
//                "bbb2",
//                "ccc2"
//        ).
//                forEach(new Console()::choiceListPrinter);

        try {
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
        } catch (IOException e) {
            e.printStackTrace();
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
        String inputLogin = in.readLine();
//check if does not exist
        System.out.println("Input password:");
        String inputPassword = in.readLine();

//        usersDao.getUser(new User(inputLogin, inputPassword));
        menu = Menu.GREETINGS;
//        if (menu.getSubmenu().size() >= --inputValue)
//            menu = menu.getSubmenu().get(inputValue);
//        else {
//            menu = Menu.ACTION_CHOICE;
//        }

    }

    private void signUp() {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        User newUser = new User();
        String pre = "Enter";
        String suf = ": ";
        try {
            String s = "";
            String s2 = "";
            do {
                System.out.println(pre + "login" + suf);
                s = in.readLine();
                String tempS = s;
                if (usersDAO.getAll().stream().filter((u) -> u.getLogin().equals(tempS)).count() == 0)
                    break;
                else System.err.println("User with such login already exist!\n" +
                        "Please, enter another login.");
            } while (true);
            newUser.setLogin(s);

            do {
                System.out.println(pre + "mail" + suf);
                s = in.readLine();
                String tempS = s;
                if (usersDAO.getAll().stream().filter((u) -> u.getMail().equals(tempS)).count() == 0)
                    break;
                else System.err.println("User with mail login already exist!\n" +
                        "Please, enter another mail.");
            } while (true);
            newUser.setMail(s);

            System.out.println(pre + "name (enter '0' to miss this stage)" + suf);
            s = in.readLine();
            newUser.setName(s.equals("0") ? "" : s);
            do {
                do {
                    System.out.println(pre + "password (minimum 6 character)" + suf);
                    s = in.readLine();
                    if (s.length() < 6)
                        System.err.println("The password must to contain minimum 6 characters!" +
                                "Your password contain only " + s.length() + " symbols.");
                } while (s.length() < 6);
                System.out.println("Please, repeat password again" + suf);
                s2 = in.readLine();
                if (!s.equals(s2)) break;
                else System.err.println("Passwords don't match.\n" +
                        "Please, try again.");
            } while (true);
            newUser.setPassword(hashing.toHashCode(s));
            usersDAO.addUser(newUser);
            System.out.println("Registration finished successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        TextSource a = chooseSource();
        String src = "";
        String key = "";
        String text = "";
        switch (a) {
            case NOTE:
                System.out.println("Enter new note name: ");
                src = in.readLine();
                System.out.println("Enter key: ");
                key = in.readLine();
                System.out.println("Enter text to encryption: ");
                text = in.readLine();
                try {
                    notesDAO.addNote(src, crypto.encrypt(text, key), curUser);
                } catch (EncryptionException e) {
                    e.printStackTrace();
                }
                break;
            case CONSOLE:
                System.out.println("Enter key to encryption: ");
                key = in.readLine();
                System.out.println("Enter text to encryption: ");
                text = in.readLine();
                break;
            case FILE:
                System.out.println("Enter name with full path of new file\n(example: C:/myFolder/anotherFolder/myFile.txt): ");
                src = in.readLine();
                System.out.println("Enter key to encryption: ");
                key = in.readLine();
                System.out.println("Enter text to encryption: ");
                text = in.readLine();
                try {
                    fileWorker.write(src, crypto.encrypt(text, key));
                } catch (EncryptionException e) {
                    e.printStackTrace();
                }
                break;
            default:
                return;
        }
        try {
            String encText = crypto.encrypt(text, key);
            System.out.println("Encrypted text: \n" + encText);
            System.out.println("\n* Complete! Press 'Enter' to continue. . .");
            in.readLine();
        } catch (EncryptionException e) {
            e.printStackTrace();
        }
    }

    private void decrypt() throws IOException {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        TextSource a = chooseSource();
        String key = "";
        String src = "";
        String decText = "";
        System.out.println("Enter key: ");
        key = in.readLine();
        try {
            switch (a) {
                case NOTE:
                    System.out.println("Enter note name: ");
                    src = in.readLine();
                    decText = crypto.decrypt(notesDAO.getNote(src).getValue(), key);
                    break;
                case CONSOLE:
                    System.out.println("Enter text to operation: ");
                    src = in.readLine();
                    decText = crypto.decrypt(src, key);
                    break;
                case FILE:
                    System.out.println("Enter file name with full path (example: C:/myFolder/anotherFolder/myFile.txt): ");
                    src = in.readLine();
                    decText = crypto.decrypt(fileWorker.read(src), key);
                    break;
                default:
                    return;
            }
        } catch (EncryptionException e) {
            e.printStackTrace();
        }
//            String decText = crypto.decrypt(src, key);
        System.out.println("Decrypted text: \n" + decText);
        System.out.println("\n* Complete! Press 'Enter' to continue. . .");
        in.readLine();
    }

    public TextSource chooseSource() throws IOException {
//        System.out.println(SPLIT + menu.name());
        System.out.println("\n\nChoose source:");
        Stream.of(
                "From saved notes",
                "Input in console",
                "From file",
                "Enter [1]: "
        ).
                forEach(new Console()::choiceListPrinter);
//        String way = in.readLine();
        return TextSource.values()[getInput(false) - 1];
    }

    private int getInput() {
        return getInput(true);
    }

    private int getInput(boolean goToNextMenu) {
        int inputValue = 0;
        boolean correctInput = false;
        while (!correctInput) {
            String s = null;
            try {
                s = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
