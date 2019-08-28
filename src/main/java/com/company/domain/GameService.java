package com.company.domain;

import com.company.application.exceptions.ApplicationException;
import com.company.domain.game.Game;

public interface GameService {

    Game startNewGame(String playerName) throws ApplicationException;

    Game loadPreviousGame() throws ApplicationException;

    void saveGame(Game game) throws ApplicationException;
}
