package ru.job4j.io.criteriaFind;

import java.nio.file.Path;
import java.util.function.Predicate;

public class NameFilter implements Filter {
    @Override
    public Predicate<Path> getPathPredicate(String name) {
        return path -> name.equals(path.getFileName().toString());
    }
}
