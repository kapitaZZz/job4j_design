package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(bf.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        if (args.length < 3) {
            throw new IllegalArgumentException("Not enough parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        Path start = Path.of(argsName.get("d"));
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException("Invalid directory");
        }
        List<File> files = Search.search(start, p -> !p.toString().endsWith(argsName.get("e")))
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        new Zip().packFiles(files, new File(argsName.get("o")));
    }
}
