package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Ivanov Ivan", 2, new GregorianCalendar(1990, Calendar.JULY, 15));
        User user2 = new User("Ivanov Ivan", 2, new GregorianCalendar(1990, Calendar.JULY, 15));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (var user : map.entrySet()) {
            System.out.println("Key = " + user.getKey() + ", value = " + user.getValue()
                    + ", hashCode = " + user.hashCode());
        }
    }
}
