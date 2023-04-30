package org.example.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<String> getTextFromFile(String filename) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String str;
            while ((str = br.readLine()) != null) {
                strings.add(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }
}