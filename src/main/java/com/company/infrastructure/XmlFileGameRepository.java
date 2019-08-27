package com.company.infrastructure;

import com.company.application.GameRepository;
import com.company.application.exceptions.SystemException;
import com.company.domain.world.Game;

public class XmlFileGameRepository implements GameRepository {

    @Override
    public Game readDefaultGame() throws SystemException {
        GameReader reader = new GameReader();
        return reader.readDefaultGame();
    }

    @Override
    public void saveGame(Game game) throws SystemException {
        GameWriter writer = new GameWriter();
        writer.saveGame("save.xml", game);
    }

    @Override
    public Game readPreviousGame() throws SystemException {
        GameReader reader = new GameReader();
        return reader.readGame("save.xml");
    }


}
