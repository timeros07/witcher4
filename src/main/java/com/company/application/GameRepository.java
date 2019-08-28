package com.company.application;

import com.company.application.exceptions.ApplicationException;
import com.company.domain.game.Game;

public interface GameRepository {

    Game readDefaultGame() throws ApplicationException;

    void saveGame(Game game) throws ApplicationException;

    Game readPreviousGame() throws ApplicationException;
}
