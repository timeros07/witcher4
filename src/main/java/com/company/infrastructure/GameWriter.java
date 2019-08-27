package com.company.infrastructure;

import com.company.application.exceptions.SystemException;
import com.company.domain.character.Player;
import com.company.domain.world.Game;
import com.company.domain.character.Monster;
import com.company.domain.world.Point;
import com.company.domain.world.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by Tomasz Woznicki on 2019-08-27.
 */
public class GameWriter {

    public void saveGame(String path, Game game) throws SystemException {
        Document document = initNewDocument();
        writeContent(document, game);
        saveFile(document, path);
    }

    private Document initNewDocument() throws SystemException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new SystemException("Unexpected exception during document creation: " + e.getMessage());
        }
    }

    private void writeContent(Document doc, Game game) {
        Element rootElement = doc.createElement("game");
        rootElement.appendChild(writeTextElement(doc, "mapSize", String.valueOf(game.getWorldMap().length)));
        rootElement.appendChild(writePlayer(doc, game.getPlayer()));
        rootElement.appendChild(writeMonsters(doc, game.getWorldMap()));
        doc.appendChild(rootElement);
    }

    private Element writeMonsters(Document doc, Point[][] worldMap) {
        Element monstersElement = doc.createElement("monsters");
        for (int i = 0; i < worldMap.length; i++) {
            for (int j = 0; j < worldMap[i].length; j++) {
                Monster monster = worldMap[i][j].getMonster();
                if (monster != null) {
                    monstersElement.appendChild(writeMonster(doc, monster));
                }
            }
        }
        return monstersElement;
    }

    private Element writeMonster(Document doc, Monster monster) {
        Element monsterElement = doc.createElement("monster");
        monsterElement.appendChild(writeTextElement(doc, "name", monster.getName()));
        monsterElement.appendChild(writeTextElement(doc, "description", monster.getDescription()));
        monsterElement.appendChild(writeTextElement(doc, "power", String.valueOf(monster.getPower())));
        monsterElement.appendChild(writePosition(doc, monster.getPosition()));
        return monsterElement;
    }

    private Element writePlayer(Document doc, Player player) {
        Element playerElement = doc.createElement("player");
        playerElement.appendChild(writeTextElement(doc, "name", player.getName()));
        playerElement.appendChild(writePosition(doc, player.getPosition()));
        return playerElement;
    }

    private Element writePosition(Document doc, Position position) {
        Element positionElement = doc.createElement("position");
        positionElement.appendChild(writeTextElement(doc, "x", String.valueOf(position.getX())));
        positionElement.appendChild(writeTextElement(doc, "y", String.valueOf(position.getY())));
        return positionElement;
    }

    private Element writeTextElement(Document doc, String name, String text) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(text));
        return element;
    }

    private void saveFile(Document doc, String path) throws SystemException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new SystemException("Unexpected error during file saving:" + e.getMessage());
        }
    }

}
