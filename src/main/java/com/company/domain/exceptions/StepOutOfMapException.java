package com.company.domain.exceptions;

import com.company.domain.world.Direction;

public class StepOutOfMapException extends Exception {

    private Direction direction;

    public StepOutOfMapException(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String getMessage() {
        return "You cannot go: " + direction.name().toLowerCase() + ", It's out of a map !!!";
    }
}
