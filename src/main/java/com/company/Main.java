package com.company;

import com.company.application.MainMenuView;
import com.company.application.exceptions.SystemException;

import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            MainMenuView mainMenu = new MainMenuView();
            mainMenu.run();
        }
        catch (SystemException e) {
            LOGGER.severe("Unexpected error: " + e.getMessage());
        }
    }



}
