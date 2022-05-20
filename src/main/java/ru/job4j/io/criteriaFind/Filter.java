package ru.job4j.io.criteriaFind;

import java.nio.file.Path;
import java.util.function.Predicate;

public interface Filter {
    Predicate<Path> getPathPredicate(String name);
}
