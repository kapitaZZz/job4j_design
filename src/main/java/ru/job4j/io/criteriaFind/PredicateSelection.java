package ru.job4j.io.criteriaFind;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class PredicateSelection {
    private final Map<String, Filter> filters = new HashMap<>();

    private PredicateSelection() {

    }

    public static PredicateSelection init() {
        PredicateSelection selector = new PredicateSelection();
        selector.load();
        return selector;
    }

    private void load() {
        filters.put("name", new NameFilter());
        filters.put("mask", new MaskFilter());
        filters.put("regex", new FilterRegex());
    }

    public Predicate<Path> selectPredicate(String type, String name) {
        Filter filter = filters.get(type);
        if (filter == null) {
            throw new IllegalArgumentException("Incorrect parameter for -t");
        }
        return filter.getPathPredicate(name);
    }
}
