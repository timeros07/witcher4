package com.company.domain.game;

import java.util.Objects;

public class Position {
    private int x = 0;
    private int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position calculateNewPosition(Direction direction) {
        Position newPosition = new Position(this.getX(), this.getY());
        switch (direction) {
            case LEFT:
                newPosition.setY(newPosition.getY() - 1);
                break;
            case RIGHT:
                newPosition.setY(newPosition.getY() + 1);
                break;
            case UP:
                newPosition.setX(newPosition.getX() - 1);
                break;
            case DOWN:
                newPosition.setX(newPosition.getX() + 1);
                break;
            default:
                break;
        }
        return newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
