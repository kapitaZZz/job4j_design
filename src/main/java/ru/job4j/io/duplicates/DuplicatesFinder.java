package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\projects\\job4j_design\\");
        List<Path> pathList = getPath(path);
        pathList.forEach(System.out::println);
    }

    public static List<Path> getPath(Path path) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(path, duplicatesVisitor);
        return duplicatesVisitor.showPathList();
    }
}
