package com.company.ui.view;

import com.company.domain.GameService;
import com.company.application.exceptions.ApplicationException;
import com.company.application.exceptions.PlayerWasKilledException;
import com.company.application.exceptions.StepOutOfMapException;
import com.company.domain.character.Monster;
import com.company.domain.game.Direction;
import com.company.domain.game.Game;
import com.company.domain.game.Position;
import com.company.ui.Console;
import com.company.ui.Images;

import java.util.Scanner;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class GameView {

    private Game game;

    private GameService gameService;

    public GameView(GameService gameService) {
        this.gameService = gameService;
    }

    public void run(Game game) throws ApplicationException {
        this.game = game;
        Console.getInstance().printLine(Console.Color.BLUE, "Game started!!!");
        showMenu();
        showMap();
        try {
            readUserInput();
        } catch (PlayerWasKilledException e) {
            endGameBecausePlayerWasKilled();
        }
    }

    private void showMap() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n#############     MAP     ###################\n");
        builder.append("Your position");
        builder.append(" - ");
        builder.append(Images.PLAYER_SIGN);
        builder.append("(");
        builder.append(game.getPlayer().getName());
        builder.append("), Monsters - X\n\t\t");
        for (int i = 0; i < game.getWorldMap().length; i++) {
            for (int j = 0; j < game.getWorldMap()[i].length; j++) {
                builder.append("|");
                if (game.getPlayer().getPosition().equals(new Position(i, j))) {
                    builder.append(Images.PLAYER_SIGN);
                } else {
                    Monster monster = game.getWorldMap()[i][j].getMonster();
                    builder.append(monster != null ? Images.MONSTER_SIGN : Images.EMPTY_SIGN);
                }
                if (j == game.getWorldMap()[i].length - 1) {
                    builder.append("|");
                }
            }
            builder.append("\n\t\t");
        }
        builder.append("\n#############################################\n");
        Console.getInstance().printLine(Console.Color.GREEN, builder.toString());
    }

    private void showMenu() {
        Console.getInstance().printLine("###########     CONTROL     ############\n" +
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

    private void readUserInput() throws ApplicationException {
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
                    gameService.saveGame(this.game);
                    Console.getInstance().printLine("Game successfully saved");
                    break;
                case "E":
                    return;
                default:
                    Console.getInstance().printLine(Console.Color.RED, "Unrecognized value: " + answer);
            }
        }
    }

    private void makeStep(Direction direction) throws PlayerWasKilledException {
        try {
            Monster monster = game.makeStep(direction);
            if (monster != null) {
                Console.getInstance().printLine(Console.Color.RED, "Yo've just met a monster!!!, you must fight");
                Console.getInstance().printLine(Images.MONSTER);
                Console.getInstance().printLine("\t\t" + monster.getName() + "\n" + monster.getDescription());
                Console.getInstance().printLine("###########     FIGHT     ############");
                for (int i = 0; i < 38; i++) {
                    Console.getInstance().print(".");
                    Thread.sleep(150);
                }
                Console.getInstance().print("\n");
                game.getPlayer().fight(monster);
                Console.getInstance().printLine("You win !!!!! Monster is dead");
            }
        } catch (StepOutOfMapException e) {
            Console.getInstance().printLine(Console.Color.RED, e.getMessage());
        } catch (InterruptedException e) {
            Console.getInstance().printLine(Console.Color.RED, e.getMessage());
            Thread.currentThread().interrupt();
        }
        showMenu();
        showMap();
    }

    private void endGameBecausePlayerWasKilled() {
        Console.getInstance().printLine(Console.Color.RED, "###########   GAME OVER   ############");
        Console.getInstance().printLine(Console.Color.RED, Images.GAME_OVER);
    }
}
