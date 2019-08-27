package com.company.ui.view;

import com.company.application.GameService;
import com.company.application.exceptions.SystemException;
import com.company.domain.world.*;
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

    public void run() throws SystemException {
        Console.getInstance().print(Console.Color.BLUE, "##################         CHARACTER CREATION      #####################");
        Console.getInstance().print(Console.Color.BLUE, "Provide character's name:");

        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        Console.getInstance().print(Console.Color.BLUE, "Welcome " + playerName + ", let's start a game...");
        Game game = gameService.startNewGame(playerName);
        ViewDispatcher.getInstance().runGameView(game);
    }
}
