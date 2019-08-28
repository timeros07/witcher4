package com.company.application.exceptions;

public class NoPreviousGameException extends ApplicationException {

    public NoPreviousGameException() {
        super("There is no previous game");
    }
}
