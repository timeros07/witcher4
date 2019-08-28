package com.company.infrastructure;

import com.company.application.GameRepository;
import com.company.application.exceptions.ApplicationException;
import com.company.domain.game.Game;

public class XmlFileGameRepository implements GameRepository {

    @Override
    public Game readDefaultGame() throws ApplicationException {
        XmlGameReader reader = new XmlGameReader();
        return reader.readDefaultGame();
    }

    @Override
    public void saveGame(Game game) throws ApplicationException {
        XmlGameWriter writer = new XmlGameWriter();
        writer.saveGame("save.xml", game);
    }

    @Override
    public Game readPreviousGame() throws ApplicationException {
        XmlGameReader reader = new XmlGameReader();
        return reader.readGameFromFile("save.xml");
    }
}
