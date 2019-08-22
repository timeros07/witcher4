package com.company.domain;

import com.company.domain.exceptions.StepOutOfMapException;
import com.company.domain.player.Player;
import com.company.domain.world.Direction;
import com.company.domain.world.Game;
import com.company.domain.world.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;

@RunWith(JUnit4.class)
public class GameTest {

    Game game = null;

    @Before
    public void init() {
        Player player = new Player();
        player.setPosition(new Position(0,0));
        game = new Game(2, Collections.emptyList(), player);
        /**
         * | 0:0 || 0:1 |
         * | 1:0 || 1:1 |
         */
    }

    @Test(expected = StepOutOfMapException.class)
    public void leftStep_noSpace_error() throws Exception {
        game.getPlayer().setPosition(new Position(0,0));
        game.makeStep(Direction.LEFT);
    }

    @Test
    public void leftStep_isSpace_success() throws Exception {
        game.getPlayer().setPosition(new Position(0,1));
        game.makeStep(Direction.LEFT);
    }

    @Test(expected = StepOutOfMapException.class)
    public void rightStep_noSpace_error() throws Exception {
        game.getPlayer().setPosition(new Position(0,1));
        game.makeStep(Direction.RIGHT);
    }

    @Test
    public void rightStep_isSpace_success() throws Exception {
        game.getPlayer().setPosition(new Position(0,0));
        game.makeStep(Direction.RIGHT);
    }

    @Test(expected = StepOutOfMapException.class)
    public void upStep_noSpace_error() throws Exception {
        game.getPlayer().setPosition(new Position(0,0));
        game.makeStep(Direction.UP);
    }

    @Test
    public void upStep_isSpace_success() throws Exception {
        game.getPlayer().setPosition(new Position(1,0));
        game.makeStep(Direction.UP);
    }

    @Test(expected = StepOutOfMapException.class)
    public void downStep_noSpace_error() throws Exception {
        game.getPlayer().setPosition(new Position(1,0));
        game.makeStep(Direction.DOWN);
    }

    @Test
    public void downStep_isSpace_success() throws Exception {
        game.getPlayer().setPosition(new Position(0,0));
        game.makeStep(Direction.DOWN);
    }
}
