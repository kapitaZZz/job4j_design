package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private List<Path> pathList = new ArrayList<>();
    private boolean validateParams;

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(String.valueOf(target))))) {
            for (Path file : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sources.toString()))) {
                    zipOutputStream.write(bis.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkParams(String[] args) throws FileNotFoundException {
        ArgsName argsMap = new ArgsName().of(args);
        for (String value : argsMap.getKeys()) {
            if (!Path.of(value).toFile().isDirectory()) {
                throw new IllegalArgumentException("Root directory cannot be null.");
            }
            if (!value.startsWith(".")) {
                throw new IllegalArgumentException("Parameter must be file extension");
            }
            if (!value.endsWith(".zip")) {
                throw new IllegalArgumentException("Parameter must be pack extension");
            }
        }
        validateParams = true;
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
        ArgsName argsName = new ArgsName();
        ArgsName map = argsName.of(args);
        zip.checkParams(args);
        if (zip.validateParams) {
            zip.pathList = Search.search(Path.of(argsName.get("-d")),
                    p -> p.toFile().getName().endsWith(map.get("-e")));
            zip.packFiles(zip.pathList, Path.of(map.get("-o")));
        }
    }
}
