package net.praktikum2024.honorarnoten;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHandling {

    private static PrintWriter pwOutput;

    static {
        try {
            pwOutput = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FileHandling.OUTPUTFILENAME), StandardCharsets.UTF_8), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readOutFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));){

            // 2 mal da ein header im input file ist
            String line = br.readLine();
            line = br.readLine();

            while (line != null && !line.isEmpty()) {
                lines.add(line);

                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public static void writeInHonorarnotenanzahlFile(String header, String text, String filename) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FileHandling.HONORARNOTEANZAHLFILENAME), StandardCharsets.UTF_8), true)) {
            pw.println(header);
            pw.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeOutputFile(String text) {
        System.out.println("Write in file: " + text);
        pwOutput.println(text);
    }
}
