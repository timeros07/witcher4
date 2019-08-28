package com.company.application;

import com.company.application.exceptions.ApplicationException;
import com.company.application.exceptions.NoPreviousGameException;
import com.company.domain.GameService;
import com.company.domain.game.Game;

/**
 * Created by Tomasz Woznicki on 2019-08-28.
 */
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(String playerName) throws ApplicationException {
        Game game = gameRepository.readDefaultGame();
        game.getPlayer().setName(playerName);
        return game;
    }

    public Game loadPreviousGame() throws ApplicationException {
        Game game = this.gameRepository.readPreviousGame();
        if (game == null) {
            throw new NoPreviousGameException();
        }
        return game;
    }

    public void saveGame(Game game) throws ApplicationException {
        this.gameRepository.saveGame(game);
    }
}
