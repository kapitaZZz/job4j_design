package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botSay = readPhrases();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            sb.append(System.lineSeparator()).append(str);
            while (!OUT.equals(str)) {
                if (STOP.equals(str)) {
                    str = reader.readLine();
                    sb.append(System.lineSeparator()).append(str);
                    while (!CONTINUE.equals(str)) {
                        str = reader.readLine();
                        sb.append(System.lineSeparator()).append(str);
                    }
                }
                int random = (int) (Math.random() * botSay.size());
                String answerBot = botSay.get(random);
                System.out.println(answerBot);
                sb.append(System.lineSeparator()).append(answerBot);
                str = reader.readLine();
            }
            saveLog(Collections.singletonList(sb.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrase = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = bf.readLine(); line != null; line = bf.readLine()) {
                phrase.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrase;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter print = new PrintWriter(new FileWriter(path,
                StandardCharsets.UTF_8, true))) {
            log.forEach(print::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "dialog_log.txt";
        String botAnswers = "phrases.txt";
        ConsoleChat cc = new ConsoleChat(path, botAnswers);
        cc.run();
    }
}
