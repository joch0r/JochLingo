package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.FileReader;

public class Parsers {
    public static Set<String> parse(File file, int wordsOfLength, char firstChar) {
        return parse(file, wordsOfLength, true, firstChar);
    }

    public static Set<String> parse(File file, int wordsOfLength) {
        return parse(file, wordsOfLength, false, (char) 0);
    }

    private static Set<String> parse(File file, int wordsOfLength, boolean perFirstChar, char firstChar) {
        HashSet<String> result = new HashSet<String>();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                if (validChars(line) && line.toLowerCase().replace("ij", "{").length() == wordsOfLength
                        && (!perFirstChar || line.charAt(0) == firstChar)) {
                    result.add(line.toLowerCase().replace("ij", "{"));
                }
                line = br.readLine();
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (perFirstChar) {
            System.out.println(String.format("Woorden met lengte %s en beginletter %s uit file %s geparsed - %s woorden gevonden",
                    wordsOfLength, firstChar, file.getAbsoluteFile(), result.size()));
        } else {
            System.out.println(String.format("Woorden met lengte %s uit file %s geparsed - %s woorden gevonden", wordsOfLength, firstChar,
                    file.getAbsoluteFile(), result.size()));
        }
        return result;
    }

    private static boolean validChars(String s) {
        return s.matches("^[a-zA-Z]+$");
    }

    public static void main(String[] args) {
        File f = new File("data\\OpenTaal-210G-basis-gekeurd.txt");
        for (int i = 5; i < 9; i++) {
            for (char c = 'a'; c < 'z'; c++) {
                Set<String> lijst = parse(f, i, c);
                System.out.println(String.format("%s - %s - %s - %s", i, c, lijst.size(), lijst));
            }
        }
    }

}
