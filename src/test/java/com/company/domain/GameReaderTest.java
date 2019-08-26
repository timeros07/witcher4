package com.company.domain;

import com.company.domain.world.Game;
import com.company.xml.GameReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GameReaderTest {

    @Test
    public void readDefaultFile_success() throws Throwable {
        GameReader reader = new GameReader();
        Game game = reader.readDefaultGame();
        Assert.assertEquals(4, game.getWorldMap().length);
        Assert.assertNotNull(game.getWorldMap()[0][2].getMonster());
        Assert.assertNotNull(game.getWorldMap()[1][1].getMonster());
    }
}
