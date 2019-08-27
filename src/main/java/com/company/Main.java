package com.company;

import com.company.application.MainMenuView;
import com.company.application.exceptions.SystemException;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
         try {
            initConsole();
            MainMenuView mainMenu = new MainMenuView();
            mainMenu.run();
        }
        catch (SystemException e) {
            LOGGER.severe("Unexpected error: " + e.getMessage());
        }
    }


    private static void initConsole() {
        Console.init(System.getProperty("os.name").contains("Windows"));
    }

}
