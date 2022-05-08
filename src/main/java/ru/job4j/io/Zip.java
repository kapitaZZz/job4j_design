package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(String.valueOf(target))))) {
            for (File file : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file))) {
                    zipOutputStream.write(bf.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkParams(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough parameters");
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

    public static void main(String[] args) {
        new Zip().checkParams(args);
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
