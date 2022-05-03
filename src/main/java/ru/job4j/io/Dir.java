package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Dir {
    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsolutePath()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            if (subfile.isFile()) {
                System.out.println(subfile.getName() + " - " + subfile.length());
            } else if (subfile.isDirectory()) {
                System.out.println(subfile.getName() + " - " + Files.walk(subfile.toPath())
                        .mapToLong(p -> p.toFile().length()).sum());
            }

        }
    }
}
