package com.company.ui;

import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class Console {

    private static Console instance;
    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    public static Console getInstance() {
        assert (instance != null);
        return instance;
    }

    public static void init(boolean isWindows) {
        if (isWindows) {
            LOGGER.warning("Game is running on Windows platform, so ASCII colors are not available :( ...");
        }
        instance = new Console(isWindows);
    }

    private boolean isWindows;
    private PrintStream printer;

    private Console(boolean isWindows) {
        this.isWindows = isWindows;
        this.printer = System.out;
    }

    public enum Color {
        RESET("\u001B[0m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        BLUE("\u001B[34m");

        private final String ansiCode;

        Color(String ansiCode) {
            this.ansiCode = ansiCode;
        }
    }


    public void printLine(Color color, String text) {
        if (isWindows) {
            printLine(text);
        } else {
            printer.println(color.ansiCode + text + Color.RESET.ansiCode);
        }
    }

    public void printLine(String text) {
        printer.println(text);
    }

    public void print(String text) {
        printer.print(text);
    }
}
