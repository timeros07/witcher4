package com.company;

import com.company.application.MainMenuView;
import com.company.domain.player.Player;
import com.company.domain.world.*;
import com.company.domain.world.Character;

import java.util.Arrays;
import java.util.Scanner;

public class Main {





    public static void main(String[] args) throws Exception {
        MainMenuView mainMenu = new MainMenuView();
        mainMenu.run();



/*

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
        printMap(world);*/
    }



}
