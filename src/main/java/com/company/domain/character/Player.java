package com.company.domain.character;

import com.company.application.exceptions.PlayerWasKilledException;
import com.company.domain.game.Position;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private String name;
    private Position position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getPlayerPower() {
        return ThreadLocalRandom.current().nextInt(0, 99);
    }

    public void fight(Monster monster) throws PlayerWasKilledException {
        if (getPlayerPower() < monster.getPower()) {
            throw new PlayerWasKilledException();
        }
    }
}
