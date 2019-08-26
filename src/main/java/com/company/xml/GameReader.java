package com.company.xml;

import com.company.application.exceptions.SystemException;
import com.company.domain.player.Player;
import com.company.domain.world.Game;
import com.company.domain.world.Monster;
import com.company.domain.world.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameReader {

    public Game readDefaultGame() throws SystemException {
        Document document = readXmlFile(readDefaultFile());
        return readGame(document);
    }

    private Game readGame(Document document) {
        int size = Integer.parseInt(document.getElementsByTagName("mapSize").item(0).getTextContent());
        return new Game(size, readMonsters(document), new Player());
    }

    private InputStream readDefaultFile() throws SystemException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("default.xml");
        if (inputStream == null) {
            throw new SystemException("default.xml not found");
        }
        return inputStream;
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

    private Document readXmlFile(InputStream inputStream) throws SystemException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(inputStream);
        }catch(ParserConfigurationException | SAXException | IOException e) {
            throw new SystemException("Unexpected exception during file reading: " + e.getMessage());
        }
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
        Element positionElement = (Element)monsterElement.getElementsByTagName("position").item(0);
        int x= Integer.parseInt(positionElement.getElementsByTagName("x").item(0).getTextContent());
        int y = Integer.parseInt(positionElement.getElementsByTagName("y").item(0).getTextContent());
        return new Position(x, y);
    }
}
