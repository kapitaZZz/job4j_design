package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        int added = 0;

        Map<Integer, User> map = new HashMap<>();

        for (User user : previous) {
            map.put(user.getId(), user);
        }
        for (User user : current) {
            User tempUser = map.putIfAbsent(user.getId(), user);
            if (tempUser != null) {
                if (!user.equals(tempUser)) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }
}

