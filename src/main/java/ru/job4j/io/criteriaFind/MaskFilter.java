package ru.job4j.io.criteriaFind;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class MaskFilter implements Filter {
    @Override
    public Predicate<Path> getPathPredicate(String name) {
        return folder -> Pattern.matches((name.replace("?", ".")
                .replace("*", ",*")), folder.getFileName().toString());
    }
}
