package com.company.domain.world;

public class Point {
    private Monster monster;

    public Point() {
    }

    public Point(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
