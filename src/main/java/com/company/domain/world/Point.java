package com.company.domain.world;

public class Point {
    private Character character;

    public Point() {
    }

    public Point(Monster character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
