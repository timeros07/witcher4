package com.company.ui;

import com.company.application.GameService;
import com.company.application.exceptions.SystemException;
import com.company.domain.world.Game;
import com.company.ui.view.CreateCharacterView;
import com.company.ui.view.GameView;
import com.company.ui.view.MainMenuView;

public class ViewDispatcher {

    private static ViewDispatcher instance;

    public static ViewDispatcher getInstance() {
        assert (instance != null);
        return instance;
    }

    public static void init(GameService gameService) {
        instance = new ViewDispatcher(gameService);
    }

    private MainMenuView mainMenuView;
    private CreateCharacterView createCharacterView;
    private GameView gameView;

    private ViewDispatcher(GameService gameService) {
        this.mainMenuView = new MainMenuView(gameService);
        this.createCharacterView = new CreateCharacterView(gameService);
        this.gameView = new GameView(gameService);
    }

    public void runCreateCharacterView() throws SystemException {
        this.createCharacterView.run();
    }

    public void runMainMenuView() throws SystemException {
        this.mainMenuView.run();
    }

    public void runGameView(Game game) throws SystemException {
        this.gameView.run(game);
    }
}
