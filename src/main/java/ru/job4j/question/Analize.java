package ru.job4j.question;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);
        Map<Integer, User> previousMap = previous.stream().collect(toMap(User::getId, e -> e));
        Map<Integer, User> currentMap = current.stream().collect(toMap(User::getId, e -> e));
        for (Map.Entry<Integer, User> entryPrev : previousMap.entrySet()) {
            Integer key = entryPrev.getKey();
            if (currentMap.containsKey(key)) {
                if (!currentMap.get(key).equals(entryPrev.getValue())) {
                    result.setChanged(result.getChanged() + 1);
                }
            } else {
                result.setDeleted(result.getDeleted() + 1);
            }
        }
        for (Integer currentKey : currentMap.keySet()) {
            if (!previousMap.containsKey(currentKey)) {
                result.setAdded(result.getAdded() + 1);
            }
        }
        return result;
    }
}