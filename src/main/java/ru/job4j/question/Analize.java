package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Разница между начальным и конечным множеством
 * считается разница в добавленных, изменённых и удалённых пользователях
 * за линейное время O(n)
 */

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> prev =
                previous.stream().collect(Collectors.toMap(User::getId,
                        User::getName));
        int add = 0;
        int changed = 0;
        for (var el : current) {
            if (!prev.containsKey(el.getId())) {
                add++;
            } else if (!prev.containsValue(el.getName()) && prev.containsKey(el.getId())) {
                changed++;
            }
            prev.remove(el.getId());
        }
        return new Info(add, changed, prev.size());
    }
}
