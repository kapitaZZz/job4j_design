package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> propertyListMap = new HashMap<>();

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.toFile().getName());
        if (propertyListMap.containsKey(fileProperty)) {
            List<Path> pathList = propertyListMap.get(fileProperty);
            pathList.add(file.toAbsolutePath());
            propertyListMap.put(fileProperty, pathList);
        } else {
            List<Path> prevPathList = new ArrayList<>();
            prevPathList.add(file.toAbsolutePath());
            propertyListMap.put(fileProperty, prevPathList);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> showPathList() {
        List<Path> pathList = new ArrayList<>();
        for (List<Path> list : propertyListMap.values()) {
            if (list.size() > 1) {
                pathList.addAll(list);
            }
        }
        return pathList;
    }
}


