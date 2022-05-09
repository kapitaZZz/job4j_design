package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static List<Path> pathList = new ArrayList<>();

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(String.valueOf(target))))) {
            Iterator<Path> it = sources.iterator();
            while (it.hasNext()) {
                File file = it.next().toFile();
                zipOutputStream.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zipOutputStream.write(out.readAllBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void checkParams(String[] args, Path rootDir) throws FileNotFoundException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough parameters");
        }
        if (!rootDir.toFile().isDirectory()) {
            throw new FileNotFoundException("Directory does not exist!");
        }
        ArgsName argsMap = new ArgsName().of(args);
        Set<String> keySet = argsMap.getKeys();
        for (String key : keySet) {
            if (!"-d".equals(key)) {
                throw new IllegalArgumentException("First parameter must by path to source directory");
            }
            if (!"-e".equals(key)) {
                throw new IllegalArgumentException("Second parameter must have exclude file extension");
            }
            if (!"-o".equals(key)) {
                throw new IllegalArgumentException("Second parameter must have target filename");
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
        if (args.length == 0) {
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
        pathList = Search.search(Path.of(argsMap.get("d")), p -> !p.toFile().getName().endsWith(argsMap.get("e")));
        new Zip().packFiles(pathList, Path.of(argsMap.get("o")));
    }
}
