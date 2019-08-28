package com.company.infrastructure;

import com.company.application.exceptions.ApplicationException;
import com.company.domain.character.Monster;
import com.company.domain.character.Player;
import com.company.domain.game.Game;
import com.company.domain.game.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlGameReader {

    public Game readGameFromFile(String path) throws ApplicationException {
        Document document;
        try {
            document = readXmlFile(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            return null;
        }
        return readGame(document);
    }

    public Game readDefaultGame() throws ApplicationException {
        Document document = readXmlFile(readFileFromJar());
        return readGame(document);
    }

    private Game readGame(Document document) {
        int size = Integer.parseInt(document.getElementsByTagName("mapSize").item(0).getTextContent());
        return new Game(size, readMonsters(document), readPlayer(document));
    }

    private Player readPlayer(Document document) {
        Player player = new Player();
        Element playerElement = (Element) document.getElementsByTagName("player").item(0);
        player.setName(playerElement.getElementsByTagName("name").item(0).getTextContent());
        player.setPosition(readPosition(playerElement));
        return player;
    }

    private List<Monster> readMonsters(Document document) {
        List<Monster> monsters = new ArrayList<>();
        NodeList nList = document.getElementsByTagName("monster");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                monsters.add(readMonster((Element) nNode));
            }
        }
        return monsters;
    }

    private Monster readMonster(Element monsterElement) {
        Monster monster = new Monster();
        monster.setName(monsterElement.getElementsByTagName("name").item(0).getTextContent());
        monster.setDescription(monsterElement.getElementsByTagName("description").item(0).getTextContent());
        monster.setPower(Integer.parseInt(monsterElement.getElementsByTagName("power").item(0).getTextContent()));
        monster.setPosition(readPosition(monsterElement));
        return monster;
    }

    private Position readPosition(Element monsterElement) {
        Element positionElement = (Element) monsterElement.getElementsByTagName("position").item(0);
        int x = Integer.parseInt(positionElement.getElementsByTagName("x").item(0).getTextContent());
        int y = Integer.parseInt(positionElement.getElementsByTagName("y").item(0).getTextContent());
        return new Position(x, y);
    }

    private InputStream readFileFromJar() throws ApplicationException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("default.xml");
        if (inputStream == null) {
            throw new ApplicationException("default.xml not found");
        }
        return inputStream;
    }

    private Document readXmlFile(InputStream inputStream) throws ApplicationException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(inputStream);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ApplicationException("Unexpected exception during file reading: " + e.getMessage());
        }
    }
}
