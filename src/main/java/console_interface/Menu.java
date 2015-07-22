package console_interface;

import java.util.ArrayList;
import java.util.Arrays;


public enum Menu {


    SIGN_IN,
    SIGN_UP,
    EXIT,

    ENCRYPT,
    DECRYPT,

    FROM_FAVORITES,
    FROM_FILE,
    FROM_CONSOLE,
    GREETINGS(
            SIGN_IN,
            SIGN_UP,
            EXIT
    ),
    ACTION_CHOICE (
            ENCRYPT,
            DECRYPT,
            GREETINGS
    ),
    SOURCE_CHOICE (
            FROM_FAVORITES,
            FROM_FILE,
            FROM_CONSOLE
    ),
    ;

    private ArrayList<Menu> submenu = new ArrayList<Menu>();

    Menu() {
    }
    Menu(Menu ... args) {
        submenu.addAll(Arrays.asList(args));
    }



    public static Menu getInstance(int a) {
        switch (a) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return ACTION_CHOICE;
    }
//    public static int getNumber(Menu menuInstance) {
//        for(int i = 0; i < 10000; i ++) {
////            (menuInstance == getInstance(i))? return i : return 0;
//        }
//    }

    public ArrayList<Menu> getSubmenu() {
        return submenu;
    }
}
