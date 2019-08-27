package com.company.domain;

import com.company.application.exceptions.PlayerWasKilledException;
import com.company.domain.character.Monster;
import com.company.domain.character.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PlayerTest {

    private Player player;

    @Before
    public void init() {
        player = new Player() {
            @Override
            public int getPlayerPower() {
                return 50;
            }
        };
    }

    @Test
    public void fight_samePower_playerWin() throws Exception {
        Monster monster = new Monster();
        monster.setPower(50);
        player.fight(monster);
    }

    @Test(expected = PlayerWasKilledException.class)
    public void fight_biggerPower_playerLose() throws Exception {
        Monster monster = new Monster();
        monster.setPower(60);
        player.fight(monster);
    }

    @Test
    public void fight_lowerPower_playerWin() throws Exception {
        Monster monster = new Monster();
        monster.setPower(5);
        player.fight(monster);
    }
}
