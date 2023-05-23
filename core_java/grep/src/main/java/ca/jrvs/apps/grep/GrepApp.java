package ca.jrvs.apps.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepApp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java GrepApp <pattern> <file>");
            System.exit(1);
        }

        String patternString = args[0];
        String filePath = args[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Pattern pattern = Pattern.compile(patternString);

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
