package com.company.application;

import com.company.Console;
import com.company.application.exceptions.SystemException;
import com.company.domain.player.Player;
import com.company.domain.world.*;
import com.company.xml.GameReader;

import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class CreateCharacterView {

    public void run() throws SystemException {
        Console.getInstance().print(Console.Color.BLUE, "##################         CHARACTER CREATION      #####################");
        Console.getInstance().print(Console.Color.BLUE, "Provide player's name:");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        Console.getInstance().print(Console.Color.BLUE, "Welcome " + answer + ", let's start a game...");


        GameReader reader = new GameReader();
        Game game = reader.readDefaultGame();
        game.getPlayer().setName(answer);
        GameView gameView = new GameView(game);

        gameView.run();
    }
}
