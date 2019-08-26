package com.company.domain.world;

import com.company.application.exceptions.StepOutOfMapException;
import com.company.domain.player.Player;

import java.util.List;

/**
 * TODO moze zamiast konstruktora fabryke
 */
public class Game {
    private Point[][] worldMap;
    private Player player;

    public Game(int size, List<Monster> monsters, Player player) {
        this.worldMap = new Point[size][size];
        this.player = player;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                getWorldMap()[i][j] = new Point();
            }
        }
        monsters.forEach(c->addMonster(c));
    }

    private void addMonster(Monster monster) {
        getWorldMap()[monster.getPosition().getX()][monster.getPosition().getY()].setMonster(monster);
    }

    public Monster makeStep(Direction direction) throws StepOutOfMapException {
        Position newPosition = new Position(player.getPosition().getX(), player.getPosition().getY());
        switch(direction) {
            case LEFT:
                newPosition.setY(newPosition.getY()-1);
                break;
            case RIGHT:
                newPosition.setY(newPosition.getY()+1);
                break;
            case UP:
                newPosition.setX(newPosition.getX()-1);
                break;
            case DOWN:
                newPosition.setX(newPosition.getX()+1);
                break;
        }
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() == worldMap.length || newPosition.getY() == worldMap.length) {
            throw new StepOutOfMapException(direction);
        }
        player.setPosition(newPosition);
        Monster monster = worldMap[player.getPosition().getX()][ player.getPosition().getY()].getMonster();
        worldMap[player.getPosition().getX()][ player.getPosition().getY()].setMonster(null);
        return monster;
    }

    public Point[][] getWorldMap() {
        return worldMap;
    }
    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
