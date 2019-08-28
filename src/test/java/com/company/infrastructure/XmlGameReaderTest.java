package com.company.infrastructure;

import com.company.domain.game.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class XmlGameReaderTest {

    @Test
    public void readDefaultFile_success() throws Throwable {
        XmlGameReader reader = new XmlGameReader();
        Game game = reader.readDefaultGame();
        Assert.assertEquals(4, game.getWorldMap().length);
        Assert.assertNotNull(game.getWorldMap()[0][2].getMonster());
        Assert.assertNotNull(game.getWorldMap()[1][1].getMonster());
        Assert.assertEquals("Anonymouse", game.getPlayer().getName());
        Assert.assertEquals(0, game.getPlayer().getPosition().getX());
        Assert.assertEquals(0, game.getPlayer().getPosition().getY());
    }
}
