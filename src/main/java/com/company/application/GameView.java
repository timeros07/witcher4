package com.company.application;

import com.company.Console;
import com.company.application.exceptions.PlayerWasKilledException;
import com.company.application.exceptions.StepOutOfMapException;
import com.company.domain.world.Direction;
import com.company.domain.world.Game;
import com.company.domain.world.Monster;
import com.company.domain.world.Position;

import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class GameView {

    private Game game;

    public GameView(Game game) {
        this.game = game;
    }

    public void run() {
        Console.writeInConsole(Console.Color.BLUE, "Game started!!!");

        showMenu();
        showMap();
        try {
            readUserInput();
        }catch(PlayerWasKilledException e) {
            endGameBecausePlayerWasKilled();
        }
    }

    public void showMap() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#############     MAP     ###################\n");
        builder.append( "Your position" + " - " + game.getPlayer().getSign() + ", Monsters - X\n\t\t");
        for (int i = 0; i < game.getWorldMap().length; i++) {
            for (int j = 0; j < game.getWorldMap()[i].length; j++) {
                builder.append("|");
                if (game.getPlayer().getPosition().equals(new Position(i, j))) {
                    builder.append(game.getPlayer().getSign());
                }
                else {
                    Monster monster = game.getWorldMap()[i][j].getMonster();
                    builder.append(monster != null ? monster.getSign() : "_");
                }
                if (j == game.getWorldMap()[i].length - 1) {
                    builder.append("|");
                }
            }
            builder.append("\n\t\t");
        }
        builder.append("\n#############################################\n");
        Console.writeInConsole(Console.Color.GREEN, builder.toString());
    }

    public void showMenu() {
        Console.writeInConsole("###########     CONTROL     ############\n" +
                "Choose option and press enter.\n" +
                "\t\tUp    : U\n" +
                "\t\tDown  : D\n" +
                "\t\tRight : R\n" +
                "\t\tLeft  : L \n" +
                "\t\t=========\n" +
                "\t\tSave  : S\n" +
                "\t\tExit  : E\n" +
                "============================================");
    }

    public void readUserInput() throws PlayerWasKilledException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String answer = scanner.nextLine();
                switch (answer) {
                    case "U":
                        makeStep(Direction.UP);
                        break;
                    case "D":
                        makeStep(Direction.DOWN);
                        break;
                    case "R":
                        makeStep(Direction.RIGHT);
                        break;
                    case "L":
                        makeStep(Direction.LEFT);
                        break;
                    case "S":
                        //save
                        break;
                    case "E":
                        return;
                    default:
                        Console.writeInConsole(Console.Color.RED, "Unrecognized value: " + answer);
                }

        }
    }

    public void makeStep(Direction direction) throws PlayerWasKilledException {
        try {
            Monster monster = this.game.makeStep(direction);
            if (monster != null) {
                Console.writeInConsole(Console.Color.RED, "Yo've just met a monster!!!, you must fight");
                Console.writeInConsole(Images.MONSTER);
                Console.writeInConsole("\t\t" + monster.getName() + "\n" + monster.getDescription());
                Console.writeInConsole("###########     FIGHT     ############");
                for(int i=0 ; i<38 ; i++) {
                    System.out.print(".");
                    Thread.sleep(150);
                }
                System.out.print("\n");

                game.getPlayer().fight(monster);
                Console.writeInConsole("You win !!!!! Monster is dead");
            }
        }catch (StepOutOfMapException | InterruptedException e) {
            Console.writeInConsole(Console.Color.RED, e.getMessage());
        }
        showMenu();
        showMap();
    }

    private void endGameBecausePlayerWasKilled() {
        Console.writeInConsole(Console.Color.RED, "###########   GAME OVER   ############");

                Console.writeInConsole(Console.Color.RED, Images.GAME_OVER);
    }
}
