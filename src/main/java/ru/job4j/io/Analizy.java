package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean flag = true;
        String start = null;
        String end;
        List<String> status = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(source))) {
            for (String line = bf.readLine(); line != null; line = bf.readLine()) {
                String[] out = line.split(" ");
                if (flag && line.startsWith("400") || flag && line.startsWith("500")) {
                    flag = false;
                    start = out[1];
                } else if (!flag && line.startsWith("200") || line.startsWith("300")) {
                    flag = true;
                    end = out[1];
                    status.add(start + "; " + end);
                }
            }
            try (PrintWriter write = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (String s : status) {
                    write.println(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
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

