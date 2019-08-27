package com.company.application;

import com.company.application.exceptions.SystemException;
import com.company.domain.world.Game;

public interface GameRepository {

    Game readDefaultGame() throws SystemException;

    void saveGame(Game game) throws SystemException;

    Game readPreviousGame() throws SystemException;
}
