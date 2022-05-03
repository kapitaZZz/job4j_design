package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader bf = new BufferedReader(new FileReader(this.path))) {
            for (String lines = bf.readLine(); lines != null; lines = bf.readLine()) {
                if (lines.startsWith("#")) {
                    continue;
                }
                String[] mass = lines.split("=", 2);
                if (!lines.contains("=") || mass[0] == null || mass.length < 2) {
                    throw new IllegalArgumentException("Check out keys and values in configuration file."
                            + " Not enough parameters. It should be ## key=value");
                }
                values.put(mass[0], mass[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}