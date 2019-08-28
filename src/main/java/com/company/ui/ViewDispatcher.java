package com.company.ui;

import com.company.application.exceptions.ApplicationException;
import com.company.domain.GameService;
import com.company.domain.game.Game;
import com.company.ui.view.CreateCharacterView;
import com.company.ui.view.GameView;
import com.company.ui.view.MainMenuView;

public class ViewDispatcher {

    private static ViewDispatcher instance;

    private MainMenuView mainMenuView;
    private CreateCharacterView createCharacterView;
    private GameView gameView;

    public static ViewDispatcher getInstance() {
        assert (instance != null);
        return instance;
    }

    public static void init(GameService gameService) {
        instance = new ViewDispatcher(gameService);
    }

    private ViewDispatcher(GameService gameService) {
        this.mainMenuView = new MainMenuView(gameService);
        this.createCharacterView = new CreateCharacterView(gameService);
        this.gameView = new GameView(gameService);
    }

    public void runCreateCharacterView() throws ApplicationException {
        this.createCharacterView.run();
    }

    public void runMainMenuView() throws ApplicationException {
        this.mainMenuView.run();
    }

    public void runGameView(Game game) throws ApplicationException {
        this.gameView.run(game);
    }
}
