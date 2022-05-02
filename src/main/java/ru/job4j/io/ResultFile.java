package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream fio = new FileOutputStream("test.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    fio.write((i * j + " ").getBytes(StandardCharsets.UTF_8));
                }
                fio.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

