package com.company.domain;

import com.company.domain.character.Player;
import com.company.domain.world.Game;
import com.company.domain.character.Monster;
import com.company.domain.world.Position;
import com.company.infrastructure.GameReader;
import com.company.infrastructure.GameWriter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Tomasz Woznicki on 2019-08-27.
 */
@RunWith(JUnit4.class)
public class GameWriterTest {

    private Game game;
    private GameWriter writer;
    private GameReader gameReader;

    @Before
    public void init() {
        Monster wyvern = new Monster();
        wyvern.setName("Wyvern");
        wyvern.setDescription("Wyverns are great flying reptiles with snake-like necks and long tails that end in a venomous trident");
        wyvern.setPower(95);
        wyvern.setPosition(new Position(1, 1));

        Monster alghoul = new Monster();
        alghoul.setName("Alghoul");
        alghoul.setDescription("Alghouls are ghouls which had been devouring corpses for so many years that human flesh becomes irresistible and they begin to prey on the living");
        alghoul.setPower(5);
        alghoul.setPosition(new Position(0, 2));

        Player player = new Player();
        player.setName("John");
        player.setPosition(new Position(1,2));

        this.game = new Game(4, Arrays.asList(wyvern, alghoul), player);
        this.writer = new GameWriter();
        this.gameReader = new GameReader();
    }

    @After
    public void clear() {
        File file = new File("tmp.xml");
        file.delete();
    }

    @Test
    public void saveDefaultGame_success() throws Throwable {
        writer.saveGame("tmp.xml", game);
        Game newGame = gameReader.readGame("tmp.xml");

        Assert.assertEquals(game.getWorldMap().length, newGame.getWorldMap().length);
        Assert.assertEquals(game.getPlayer().getName(), newGame.getPlayer().getName());
        Assert.assertEquals(game.getPlayer().getPosition(), newGame.getPlayer().getPosition());
        Assert.assertNotNull(newGame.getWorldMap()[0][2].getMonster());
        Assert.assertNotNull(newGame.getWorldMap()[1][1].getMonster());

    }
}
