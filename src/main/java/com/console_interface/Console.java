package com.console_interface;

import com.AppInterface;
import com.console_interface.enumeration.Menu;
import com.console_interface.enumeration.TextSource;
import com.data_base.data_access_objects.DAOInterface;
import com.data_base.data_access_objects.NotesDAO;
import com.data_base.data_access_objects.UsersDAO;
import com.data_base.entities.Note;
import com.data_base.entities.User;
import com.encryption.CipherWorker;
import com.encryption.hashing.HashingMD5;
import com.exceptions.EncryptionException;
import com.exceptions.IncorrectInput;
import com.file_worker.FileWorker;
import com.file_worker.IFileWorker;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Console implements AppInterface {

    private static final Logger logger = Logger.getLogger("logConsoleDebugging");
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private User curUser = null;
    private Menu menu = Menu.GREETINGS;

    private CipherWorker crypto = new CipherWorker();
    private HashingMD5 hashing = new HashingMD5();

    private IFileWorker fileWorker = new FileWorker();
    private DAOInterface<User> usersDAO = new UsersDAO();
    private DAOInterface<Note> notesDAO = new NotesDAO();

    private static final String SPLIT = "\n============================ ";
    private static final String SPLIT2 = " ============================\n";

    private int curStringNumber = 1;
    private boolean isExit = false;
    private int inputValue;

    public Console() {
        this(null);
//        log.info("In console constructor");
//        log2.info("In console constructor");
    }

    public Console(User curUser) {
        this.curUser = curUser;
        isExit = false; // на всякий случай ;)
    }

    public void launch() {
        logger.info("Console interface launched!");
//        log.info("Console interface launched");
//        log.t("Some errors!");
//        Stream.of(
//                "aaa2",
//                "bbb2",
//                "ccc2"
//        ).
//                forEach(new Console()::choiceListPrinter);

        try {
            while (!isExit) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Current user: " + curUser + ". ");
                    logger.debug("In \"" + menu + "\"");
                }
                switch (menu) {
                    case GREETINGS:
                        greetings();
                        break;
                    case ACTION_CHOICE:
                        chooseAction();
                        break;
//                    case SOURCE_CHOICE:
//                    chooseSource();
//                        break;

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
        curUser = null;
        System.out.println("Hello" + ((curUser != null) ? ((curUser.getName() != null) ? ", " + curUser.getName() : "") : "") + "!");

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
        System.out.println("Input password:");
        String inputPassword = in.readLine();

        User userT = usersDAO.get(new User(inputLogin, inputPassword));
        if (userT != null) {
            curUser = userT;
            menu = Menu.ACTION_CHOICE;
        } else {
            System.err.println("\nUser with such login and password does not exist!");
            menu = Menu.GREETINGS;
        }
    }

    private void signUp() {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        User newUser = new User();
        String pre = "Enter ";
        String suf = ": ";
        try {
            String s = "";
            String s2 = "";
            do {
                System.out.println(pre + "login" + suf);
                s = in.readLine();
                String tempS = s;
                if (s.length() == 0)
                    System.err.println("You must enter login!\n");
                else if (usersDAO.getAll().stream().filter((u) -> u.getLogin().equals(tempS)).count() == 0)
                    break;
                else System.err.println("User with such login already exist!\n" +
                            "Please, enter another login.");
            } while (true);
            newUser.setLogin(s);

            do {
                System.out.println(pre + "mail" + suf);
                s = in.readLine();
                String tempS = s;
                if (s.length() == 0)
                    System.err.println("You must enter mail!\n");
                else if (usersDAO.getAll().stream().filter((u) -> u.getMail().equals(tempS)).count() == 0)
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
                if (s.equals(s2)) break;
                else System.err.println("Passwords don't match.\n" +
                        "Please, try again.");
            } while (true);
            newUser.setPasswordToMD5(s);
            usersDAO.add(newUser);
            System.out.println("REMEMBER YOUR PASSWORD: " + s);
            System.out.println("pass in DB: " + usersDAO.get(newUser).getPassword());
            System.out.println("input pass to hash: " + hashing.toHashCode(s2));
            System.out.println("Registration finished successfully!");
            menu = Menu.ACTION_CHOICE;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseAction() {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        System.out.println("Hello" + ((curUser != null) ? ", " + curUser.getName() : "") + "!");
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
        TextSource input = chooseSource("From console", "From file");
        String src = "";
        String textToEncryption = "";
        String key = "";
        switch (input) {
            case BACK:
                menu = Menu.ACTION_CHOICE;
                return;
//            case NOTE:
//                System.out.println("Enter note name: ");
//                src = in.readLine();
//                textToEncryption = notesDAO.get(new Note(src)).getValue();
//                System.out.println("Enter key: ");
//                key = in.readLine();
//                try {
//                    notesDAO.add(new Note(src, crypto.encrypt(textToEncryption, key), curUser.getId()));
//                } catch (EncryptionException e) {
//                    e.printStackTrace();
//                }
//                break;
            case CONSOLE:
                System.out.println("Enter text to encryption: ");
                textToEncryption = in.readLine();
                System.out.println("Enter key to encryption: ");
                key = in.readLine();
                break;
            case FILE:
                System.out.println("Enter name with full path of new file\n(example: C:\\myFolder\\anotherFolder\\myFile.txt): ");
                src = in.readLine();
                textToEncryption = fileWorker.read(src);
                System.out.println("Enter key to encryption: ");
                key = in.readLine();
                try {
                    fileWorker.write(src, crypto.encrypt(textToEncryption, key));
                } catch (EncryptionException e) {
                    e.printStackTrace();
                }
                break;
            default:
                return;
        }
        TextSource output = chooseSource("To console", "To file", "To note");
        String encryptedText = "";
        try {
            encryptedText = crypto.encrypt(textToEncryption, key);
        } catch (EncryptionException e) {
            e.printStackTrace();
        }
        switch (output) {
            case BACK:
                menu = Menu.ACTION_CHOICE;
                return;
            case CONSOLE:
                System.out.println("Encrypted text: ");
                System.out.println(encryptedText);
                break;
            case FILE:
                System.out.println("Enter name with full path of new file\n(example: C:/myFolder/anotherFolder/myFile.txt): ");
                src = in.readLine();
                fileWorker.write(src, encryptedText);
                break;
            case NOTE:
                if (curUser.canCreateNote(textToEncryption)) {
                    System.out.println("Enter new note name: ");
                    src = in.readLine();
                    notesDAO.add(new Note(src, encryptedText, curUser.getId()));
                } else
                    System.err.println("You version of program is limited.\n" +
                            "You can't to create new note or length of note more than " + curUser.getMaxNoteLength());
                break;
            default:
                return;
        }
        System.out.println("\n* Complete! Press 'Enter' to continue. . .");
        in.readLine();
    }

    private void decrypt() throws IOException {
        System.out.println(SPLIT + menu.name() + SPLIT2);
        TextSource inputSource = chooseSource("From console", "From file", "From note");
        String src = "";
        String textToDecryption = "";
        String key = "";
        switch (inputSource) {
            case BACK:
                menu = Menu.ACTION_CHOICE;
                return;
            case CONSOLE:
                System.out.println("Enter text to operation: ");
                textToDecryption = in.readLine();
                break;
            case FILE:
                System.out.println("Enter file name with full path (example: C:/myFolder/anotherFolder/myFile.txt): ");
                src = in.readLine();
                textToDecryption = fileWorker.read(src);
                break;
            case NOTE:
                System.out.println("Enter note name: ");
                src = in.readLine();
                textToDecryption = notesDAO.get(new Note(src, "", curUser.getId())).getValue();
                break;
            default:
                return;
        }
        System.out.println("Enter key: ");
        key = in.readLine();
        String decryptedText = "";
        try {
            decryptedText = crypto.decrypt(textToDecryption, key);
        } catch (EncryptionException e) {
            e.printStackTrace();
        }
        TextSource output = chooseSource("To console", "To file");
        switch (output) {
            case BACK:
                menu = Menu.ACTION_CHOICE;
                return;
            case CONSOLE:
                System.out.println("Encrypted text: ");
                System.out.println(decryptedText);
                break;
            case FILE:
                System.out.println("Enter name with full path of new file\n(example: C:/myFolder/anotherFolder/myFile.txt): ");
                src = in.readLine();
                fileWorker.write(src, decryptedText);
                break;
//            case NOTE:
//                System.out.println("Enter new note name: ");
//                src = in.readLine();
//                notesDAO.add(new Note(src, decryptedText, curUser.getId()));
//                break;
            default:
                return;
        }
        System.out.println("\n* Complete! Press 'Enter' to continue. . .");
        in.readLine();
    }

    public TextSource chooseSource(String... actions) throws IOException {
        if (logger.isTraceEnabled())
            logger.trace("In \"Source choose\"");
//        System.out.println(SPLIT + menu.name());
        System.out.println("\n\nChoose source:");
        System.out.println("0. Back.");
        Console consT = new Console();
        for (String s : actions) {
            consT.choiceListPrinter(s);
        }
//        String way = in.readLine();
        return TextSource.values()[getInput(false)];
    }

    private int getInput() {
        return getInput(true);
    }

    private int getInput(boolean goToNextMenu) {
        if (logger.isTraceEnabled())
            logger.trace("In \"Get input\"");
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
            if (correctInput && s.length() == 0)
                inputValue = Integer.parseInt("1");
            else if (correctInput && s.length() <= 4)
                inputValue = Integer.parseInt(s);
            else {
                correctInput = false;
                try {
                    throw new IncorrectInput("You enter text or too long number!");
                } catch (IncorrectInput incorrectInput) {
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
                    logger.error("in line 424. Input value is out of range!");
                }
        }
        return inputValue;
    }

    private void moveBack() {
        if (logger.isTraceEnabled())
            logger.trace("In \"Move back\"");
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
