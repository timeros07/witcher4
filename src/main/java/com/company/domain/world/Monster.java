package com.company.domain.world;

import java.util.List;

public class Monster implements Character{
    private String name;
    private String description;
    private List<String> actions;
    private Position position;

    @Override
    public String getSign() {
        return "X";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
