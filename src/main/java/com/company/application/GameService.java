package com.company.application;

import com.company.application.exceptions.NoPreviousGameException;
import com.company.application.exceptions.SystemException;
import com.company.domain.world.Game;

public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(String playerName) throws SystemException {
        Game game = gameRepository.readDefaultGame();
        game.getPlayer().setName(playerName);
        return game;
    }

    public Game loadPreviousGame() throws SystemException {
        Game game = this.gameRepository.readPreviousGame();
        if (game == null) {
            throw new NoPreviousGameException("There is no previous game");
        }
        return game;
    }


    public void saveGame(Game game) throws SystemException {
        this.gameRepository.saveGame(game);
    }

}
