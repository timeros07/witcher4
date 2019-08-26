package com.company.application;

import com.company.Console;
import com.company.application.exceptions.SystemException;

import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class MainMenuView {

    class Options {
        public static final String NEW_GAME = "N";
        public static final String RESUME = "R";
        public static final String EXIT = "E";
    }

    public void run() throws SystemException {
        showMenuWithLogo();
        readUserInput();
    }

    public void showMenuWithLogo() {
        Console.writeInConsole(Console.Color.BLUE,
                "##################         NEW GAME : N      #####################\n" +
                "##################         RESUME   : R      #####################\n" +
                "##################         EXIT     : E      #####################\n" +
                "##################################################################\n" +
                "                 Choose option and press enter\n");
    }


    private void readUserInput() throws SystemException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String answer = scanner.nextLine();
            switch(answer) {
                case Options.NEW_GAME:
                    CreateCharacterView createCharacterView = new CreateCharacterView();
                    createCharacterView.run();
                    return;
                case Options.RESUME:
                    break;
                case Options.EXIT:
                    return;
                default:
                    Console.writeInConsole(Console.Color.RED, "Unrecognized value: " + answer);
            }
        }
    }

}
