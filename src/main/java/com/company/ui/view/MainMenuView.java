package com.company.ui.view;

import com.company.application.GameService;
import com.company.application.exceptions.SystemException;
import com.company.domain.world.Game;
import com.company.ui.Console;
import com.company.ui.ViewDispatcher;

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

    private GameService gameService;

    public MainMenuView(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() throws SystemException {
        showMenuWithLogo();
        readUserInput();
    }

    public void showMenuWithLogo() {
        Console.getInstance().print(Console.Color.BLUE,
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
                    ViewDispatcher.getInstance().runCreateCharacterView();
                    return;
                case Options.RESUME:
                    Game game = gameService.loadPreviousGame();
                    ViewDispatcher.getInstance().runGameView(game);
                    return;
                case Options.EXIT:
                    return;
                default:
                    Console.getInstance().print(Console.Color.RED, "Unrecognized value: " + answer);
            }
        }
    }

}
