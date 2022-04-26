package ru.job4j;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Steve", 1, new GregorianCalendar(1988, Calendar.NOVEMBER, 25, 10, 15, 11));
        User user2 = new User("Steve", 1, new GregorianCalendar(1988, Calendar.NOVEMBER, 25, 10, 15, 11));

        Map<User, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(user1, new Object());
        userObjectMap.put(user2, new Object());

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));

        for (Map.Entry<User, Object> u : userObjectMap.entrySet()) {
            System.out.println(u.getKey() + " - " + u.getValue());
        }
    }
}
