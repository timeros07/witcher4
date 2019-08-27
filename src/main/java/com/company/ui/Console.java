package com.company.ui;

import java.util.logging.Logger;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class Console {

    private static Console instance;
    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    private boolean isWindows;

    public static Console getInstance() {
        assert (instance != null);
        return instance;
    }

    private Console(boolean isWindows) {
        this.isWindows = isWindows;
    }

    public static void init(boolean isWindows) {
        if (isWindows) {
            LOGGER.warning("Game is running on Windows platform, so ASCII colors are not available :( ...");
        }
        instance = new Console(isWindows);
    }

    public enum Color {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        WHITE("\u001B[37m");

        private final String ansiCode;

        Color(String ansiCode) {
            this.ansiCode = ansiCode;
        }
    }


    public void print(Color color, String text) {
        if (isWindows) {
            print(text);
        } else {
            System.out.println(color.ansiCode + text + Color.RESET.ansiCode);
        }
    }

    public void print(String text) {
        System.out.println(text);
    }
}
