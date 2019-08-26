package com.company.application;

import com.company.Console;
import com.company.domain.exceptions.StepOutOfMapException;
import com.company.domain.player.Player;
import com.company.domain.world.Character;
import com.company.domain.world.Direction;
import com.company.domain.world.Game;

import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class GameView {

    private Game game;

    public void run(Game game) {
        this.game = game;
        Console.writeInConsole(Console.Color.BLUE, "##################         GAME STARTER!!      #####################");
        Console.writeInConsole(Console.Color.BLUE, "##################         PLAYER " + game.getPlayer().getName() + "      #####################");

        Console.writeInConsole(Console.Color.GREEN, "Your position" + " - " + game.getPlayer().getSign());
        Console.writeInConsole(Console.Color.GREEN, "Monsters" + " - X");
        Console.writeInConsole(Console.Color.GREEN, "Friends" + " - F");
        showMap();
        showInput();
        readInput();

    }

    public void showMap() {
        StringBuilder builder = new StringBuilder();
        builder.append("#### MAP ####\n");
        for (int i = 0; i < game.getWorldMap().length; i++) {
            for (int j = 0; j < game.getWorldMap()[i].length; j++) {
                Character character = game.getWorldMap()[i][j].getCharacter();
                builder.append("|" + (character != null ? character.getSign() : "_"));
                if (j == game.getWorldMap()[i].length - 1) {
                    builder.append("|");
                }
            }
            builder.append("\n");
        }
        Console.writeInConsole(Console.Color.GREEN, builder.toString());
    }

    public void showInput() {
        Console.writeInConsole("Write character and press enter.\n" +
                "\t\tUp    : U\n" +
                "\t\tDown  : D\n" +
                "\t\tRight : R\n" +
                "\t\tLeft  : L \n" +
                "\t\t=========\n" +
                "\t\tSave  : S\n" +
                "\t\tExit  : E\n" +
                "============================================");
    }

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String answer = scanner.nextLine();
            try {
                switch (answer) {
                    case "U":
                        this.game.makeStep(Direction.UP);
                        showInput();
                        showMap();
                        break;
                    case "D":
                        this.game.makeStep(Direction.DOWN);
                        showInput();
                        showMap();
                        break;
                    case "R":
                        this.game.makeStep(Direction.RIGHT);
                        showInput();
                        showMap();
                        break;
                    case "L":
                        this.game.makeStep(Direction.LEFT);
                        showInput();
                        showMap();
                        break;
                    default:
                        Console.writeInConsole(Console.Color.RED, "Unrecognized value: " + answer);
                }
            } catch (StepOutOfMapException e) {
                Console.writeInConsole(Console.Color.RED, e.getMessage());
            }
        }

    }
}
