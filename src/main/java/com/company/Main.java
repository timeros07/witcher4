package com.company;

import com.company.domain.player.Player;
import com.company.domain.world.*;
import com.company.domain.world.Character;

import java.util.Arrays;

public class Main {

    enum Color {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        WHITE("\u001B[37m");

        private final String ansiCode;
        private Color(String ansiCode) {
            this.ansiCode = ansiCode;
        }

    }



    public static void main(String[] args) throws Exception {
        writeInConsole(Color.WHITE, "             |>\n" +
                "            //_____________________\n" +
                "<((((((((((|_/_/____//________/____/ THE WITCHER 4\n" +
                "            \\\\\n" +
                "             |>\n" +
                "##################         NEW GAME : N      #####################\n" +
                "##################         RESUME   : R      #####################\n" +
                "##################         EXIT     : E      #####################\n" +
                "##################################################################\n" +
                "                 Choose option and press enter\n");


        Friend vesemir = new Friend();
        vesemir.setName("Vesemir");
        vesemir.setPosition(new Position(2,2));

        Monster vivern = new Monster();
        vivern.setName("Viverna");
        vivern.setPosition(new Position(1,3));

        Player player = new Player();
        player.setName("Thomas");
        player.setSign("$");
        player.setPosition(new Position(0,0));


        Game world = new Game(4, Arrays.asList(vesemir, vivern), player);
        printMap(world);
world.makeStep(Direction.RIGHT);
        world.makeStep(Direction.DOWN);
        printMap(world);
    }

    public static void printMap(Game world) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<world.getWorldMap().length ; i++) {
            for (int j = 0; j<world.getWorldMap()[i].length ; j++) {
                Character character = world.getWorldMap()[i][j].getCharacter();
                builder.append("|" + (character != null ? character.getSign() : "_") +"|");
            }
            builder.append("\n");
        }
        writeInConsole(Color.GREEN, builder.toString());
    }



    public static void writeInConsole(Color color, String text) {
        System.out.println(color.ansiCode + text + Color.RESET.ansiCode);
    }
}
