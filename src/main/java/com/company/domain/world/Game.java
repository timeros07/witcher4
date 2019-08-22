package com.company.domain.world;

import com.company.domain.exceptions.StepOutOfMapException;
import com.company.domain.player.Player;

import java.util.List;

/**
 * TODO moze zamiast konstruktora fabryke
 */
public class Game {
    private Point[][] worldMap;
    private Player player;

    public Game() {
    }

    public Game(int size, List<Character> characters, Player player) {
        this.worldMap = new Point[size][size];
        this.player = player;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                getWorldMap()[i][j] = new Point();
            }
        }
        characters.forEach(c->addCharacter(c));
        addCharacter(player);
    }

    private void addCharacter(Character character) {
        getWorldMap()[character.getPosition().getX()][character.getPosition().getY()].setCharacter(character);
    }

    public void makeStep(Direction direction) throws StepOutOfMapException {
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
            throw new StepOutOfMapException();
        }
        worldMap[player.getPosition().getX()][ player.getPosition().getY()].setCharacter(null);
        worldMap[newPosition.getX()][ newPosition.getY()].setCharacter(player);
        player.setPosition(newPosition);

    }

    public Point[][] getWorldMap() {
        return worldMap;
    }
    public Player getPlayer() {
        return this.player;
    }
}
