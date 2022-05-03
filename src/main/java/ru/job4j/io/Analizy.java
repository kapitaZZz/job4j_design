package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean flag = true;
        String start = null;
        String end;
        try (BufferedReader bf = new BufferedReader(new FileReader(source));
             PrintWriter write = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String line = bf.readLine(); line != null; line = bf.readLine()) {
                String[] out = line.split(" ");
                if (flag && line.startsWith("400") || flag && line.startsWith("500")) {
                    flag = false;
                    start = out[1];
                } else if (!flag && line.startsWith("200") || line.startsWith("300")) {
                    flag = true;
                    end = out[1];
                    write.println(start + "; " + end);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.csv", "target.csv");
    }
}

