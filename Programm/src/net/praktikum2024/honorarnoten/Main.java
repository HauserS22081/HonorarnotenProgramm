package net.praktikum2024.honorarnoten;

public class Main {
    public static void main(String[] args) {
        FileHandling.openAndReadInFiles();
        try {
            FileHandling.createOutputMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FileHandling.writeInOutputFile();

    }
}
