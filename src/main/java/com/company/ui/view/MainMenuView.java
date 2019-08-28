package com.company.ui.view;

import com.company.domain.GameService;
import com.company.application.exceptions.ApplicationException;
import com.company.domain.game.Game;
import com.company.ui.Console;
import com.company.ui.Images;
import com.company.ui.ViewDispatcher;

import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class MainMenuView {

    private static final String NEW_GAME = "N";
    private static final String RESUME = "R";
    private static final String EXIT = "E";

    private GameService gameService;

    public MainMenuView(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() throws ApplicationException {
        showMenuWithLogo();
        readUserInput();
    }

    public void showMenuWithLogo() {
        Console.getInstance().printLine(Console.Color.GREEN, Images.LOGO);
        Console.getInstance().printLine(Console.Color.BLUE,
                "##################         NEW GAME : N      #####################\n" +
                "##################         RESUME   : R      #####################\n" +
                "##################         EXIT     : E      #####################\n" +
                "##################################################################\n" +
                "                 Choose option and press enter\n");
    }


    private void readUserInput() throws ApplicationException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String answer = scanner.nextLine();
            switch(answer) {
                case NEW_GAME:
                    ViewDispatcher.getInstance().runCreateCharacterView();
                    return;
                case RESUME:
                    Game game = gameService.loadPreviousGame();
                    ViewDispatcher.getInstance().runGameView(game);
                    return;
                case EXIT:
                    return;
                default:
                    Console.getInstance().printLine(Console.Color.RED, "Unrecognized value: " + answer);
            }
        }
    }

}
