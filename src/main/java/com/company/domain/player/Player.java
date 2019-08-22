package com.company.domain.player;

import com.company.domain.world.Character;
import com.company.domain.world.Position;

public class Player implements Character {

    private String name;
    private Position position;
    private String sign;

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
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
