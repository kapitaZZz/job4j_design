package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int count;
            boolean rs;
            while ((count = fis.read()) != -1) {
                sb.append((char) count);
            }
            String[] text = sb.toString().split(System.lineSeparator());
            for (String s : text) {
                if (Integer.parseInt(s) % 2 == 0) {
                    rs = true;
                    System.out.println(s + " " + rs);
                } else {
                    rs = false;
                    System.out.println(s + " " + rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
