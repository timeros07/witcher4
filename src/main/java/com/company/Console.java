package com.company;

/**
 * Created by Tomasz Woznicki on 2019-08-26.
 */
public class Console {

    public enum Color {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        WHITE("\u001B[37m");

        private final String ansiCode;
        private Color(String ansiCode) {
            this.ansiCode = ansiCode;
        }

    }


    public static void writeInConsole(Color color, String text) {
        System.out.println(color.ansiCode + text + Color.RESET.ansiCode);
    }

    public static void writeInConsole(String text) {
        System.out.println(text);
    }
}
