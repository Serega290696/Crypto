package com.console_interface.enumeration;

import java.util.ArrayList;
import java.util.Arrays;


public enum Menu {


    SIGN_IN,
    SIGN_UP,
    EXIT,

    ENCRYPT(null, null, null),
    DECRYPT(null, null, null),

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



    public ArrayList<Menu> getSubmenu() {
        return submenu;
    }
}
