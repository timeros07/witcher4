package com.company.domain.player;

import com.company.application.exceptions.PlayerWasKilledException;
import com.company.domain.world.Monster;
import com.company.domain.world.Position;

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

    public String getSign() {
        return "$";
    }

    public void fight(Monster monster) throws PlayerWasKilledException {
        int value = ThreadLocalRandom.current().nextInt(0, 99);
        if (value < monster.getPower()) {
            throw new PlayerWasKilledException();
        }
    }
}
