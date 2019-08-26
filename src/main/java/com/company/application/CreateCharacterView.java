package com.company.application;

import com.company.Console;
import com.company.domain.player.Player;
import com.company.domain.world.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class CreateCharacterView {

    public void run() {
        Console.writeInConsole(Console.Color.BLUE, "##################         CHARACTER CREATION      #####################");
        Console.writeInConsole(Console.Color.BLUE, "Provide player's name:");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        Console.writeInConsole(Console.Color.BLUE, "Welcome " + answer + ", let's start a game...");



        GameView gameView = new GameView();
        gameView.run(initGame(answer));
    }

    public Game initGame(String playerName) {
        Player player = new Player();
        player.setName(playerName);
        player.setPosition(new Position(0, 0));
        player.setSign("$");

        Friend vesemir = new Friend();
        vesemir.setName("Vesemir");
        vesemir.setPosition(new Position(2,2));

        Monster vivern = new Monster();
        vivern.setName("Viverna");
        vivern.setPosition(new Position(1,3));
        return new Game(4, Arrays.asList(vesemir, vivern), player);
    }
}
