package com.company.domain.game;

import com.company.domain.character.Monster;

public class Point {
    private Monster monster;

    public Point() {
        this.monster = null;
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
