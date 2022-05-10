package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private List<Path> pathList = new ArrayList<>();

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(String.valueOf(target))))) {
            for (Path file : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(file.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void checkParams(String[] args, Path rootDir) throws FileNotFoundException {
        if (!rootDir.toFile().isDirectory()) {
            throw new FileNotFoundException("Directory does not exist!");
        }
        ArgsName argsMap = new ArgsName().of(args);
        for (String value : argsMap.getKeys()) {
            if ("".equals(value)) {
                throw new IllegalArgumentException("Parameter values cannot bu null.");
            }
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Enter: -d directory for archiving, -e exclude file, -o output archive file"
            );
        }
        ArgsName argsMap = ArgsName.of(args);
        Set<String> argSet = argsMap.getKeys();
        for (String key : argSet) {
            if (!"d".equals(key) && !"e".equals(key) && !"o".equals(key)) {
                throw new IllegalArgumentException("-d directory, -e exclude, -o output");
            }
        }
        zip.pathList = Search.search(Path.of(argsMap.get("d")), p -> !p.toFile().getName().endsWith(argsMap.get("e")));
        new Zip().packFiles(zip.pathList, Path.of(argsMap.get("o")));
    }
}
