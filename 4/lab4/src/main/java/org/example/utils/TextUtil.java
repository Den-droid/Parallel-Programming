package org.example.utils;

import java.util.Arrays;
import java.util.List;

public class TextUtil {
    public static List<String> getWordsFromLine(String line) {
        return Arrays.asList(line.split("(\\s|\\p{Punct})+"));
    }
}
