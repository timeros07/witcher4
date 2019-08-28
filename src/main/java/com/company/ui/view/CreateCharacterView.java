package com.company.ui.view;

import com.company.domain.GameService;
import com.company.application.exceptions.ApplicationException;
import com.company.domain.game.*;
import com.company.ui.Console;
import com.company.ui.ViewDispatcher;

import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class CreateCharacterView {

    private GameService gameService;

    public CreateCharacterView(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() throws ApplicationException {
        Console.getInstance().printLine(Console.Color.BLUE, "##################         CHARACTER CREATION      #####################");
        Console.getInstance().printLine(Console.Color.BLUE, "Provide character's name:");

        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        Console.getInstance().printLine(Console.Color.BLUE, "Welcome " + playerName + ", let's start a game...");
        Game game = gameService.startNewGame(playerName);
        ViewDispatcher.getInstance().runGameView(game);
    }
}
