package com.company.domain.world;

public class Friend implements Character {

    private String name;
    private Position position;

    @Override
    public String getSign() {
        return "F";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
