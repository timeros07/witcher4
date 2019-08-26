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
        Console.writeInConsole(Console.Color.BLUE, "##################         CHARACTER CREATION      #####################");
        Console.writeInConsole(Console.Color.BLUE, "Provide player's name:");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        Console.writeInConsole(Console.Color.BLUE, "Welcome " + answer + ", let's start a game...");


        GameReader reader = new GameReader();
        Game game = reader.readDefaultGame();
        Player player = new Player();
        player.setPosition(new Position(0,0));
        player.setSign("$");
        player.setName(answer);
        game.setPlayer(player);
        GameView gameView = new GameView(game);


        gameView.run();
    }
}
