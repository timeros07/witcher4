package com.company;

import com.company.application.GameService;
import com.company.infrastructure.XmlFileGameRepository;
import com.company.application.exceptions.SystemException;
import com.company.ui.Console;
import com.company.ui.ViewDispatcher;

import java.util.logging.Logger;

public class Witcher4Application {

    private static final Logger LOGGER = Logger.getLogger(Witcher4Application.class.getName());

    public static void main(String[] args) {
         try {
            initConsole();
            ViewDispatcher.init(getGameService());
            ViewDispatcher.getInstance().runMainMenuView();
        }
        catch (SystemException e) {
            LOGGER.severe("Unexpected error: " + e.getMessage());
        }
    }

    private static void initConsole() {
        Console.init(System.getProperty("os.name").contains("Windows"));
    }

    private static GameService getGameService() {
        return new GameService(new XmlFileGameRepository());
    }


}
