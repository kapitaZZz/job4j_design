package ru.job4j.io.criteriaFind;

import ru.job4j.io.ArgsName;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import static ru.job4j.io.Search.search;

public class Searcher {

    public List<Path> findFiles(String type, String name, Path start) throws IOException {
        PredicateSelection selector = PredicateSelection.init();
        Predicate<Path> predicate = selector.selectPredicate(type, name);
        return search(start, predicate);
    }

    public void writeToFile(List<Path> files, String target) {
        try (PrintWriter writer = new PrintWriter(target)) {
            files.forEach(writer::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        ArgsName arg = ArgsName.of(args);
        Path start = Path.of(arg.get("d"));
        if (!Files.isDirectory(start)) {
            throw new IOException("Invalid directory name for -d");
        }
        Searcher finder = new Searcher();
        List<Path> files = finder.findFiles(arg.get("t"), arg.get("n"), start);
        finder.writeToFile(files, arg.get("o"));
    }
}
