package com.company.application.exceptions;

import com.company.domain.game.Direction;

public class StepOutOfMapException extends ApplicationException {

    public StepOutOfMapException(Direction direction) {
        super("You cannot go: " + direction.name().toLowerCase() + ", It's out of a map !!!");
    }
}
