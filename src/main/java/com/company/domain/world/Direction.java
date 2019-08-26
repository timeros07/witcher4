package com.company.domain.world;

public enum Direction {
    LEFT("L"), RIGHT("R"), UP("R"), DOWN("D");

    private String code;
    private Direction(String code) {
        this.code = code;
    }
}
