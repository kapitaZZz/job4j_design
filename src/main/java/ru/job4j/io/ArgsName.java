package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if ("".equals(key) || !values.containsKey(key)) {
            throw new IllegalArgumentException("Key cannot be null!");
        }
        return values.get(key);
    }

    public Set<String> getKeys() {
        return values.keySet();
    }

    private void parse(String[] args) {
        for (String s : args) {
            if (!s.contains("=")) {
                throw new IllegalArgumentException("Not enough parameters, like: -key=value");
            }
            String[] temp = s.split("=", 2);
            if ("".equals(temp[0]) || !temp[0].startsWith("-")) {
                throw new IllegalArgumentException("Key cannot be null");
            }
            if ("".equals(temp[1])) {
                throw new IllegalArgumentException("Value cannot be null");
            }
            values.put(temp[0].substring(1), temp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
