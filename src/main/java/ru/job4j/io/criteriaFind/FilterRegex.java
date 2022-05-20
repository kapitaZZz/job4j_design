package ru.job4j.io.criteriaFind;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FilterRegex implements Filter {
    @Override
    public Predicate<Path> getPathPredicate(String name) {
        return path -> Pattern.matches(name, path.getFileName().toString());
    }
}
