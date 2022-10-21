package ru.job4j.ood.dip;

/**
 * Класс зависит от реализации, а не от абстракции
 */
public class ServiceCheck {

    private User user;

    /**
     * Прямая зависимость самого логирования от реализации.
     * Пример исправления:
     * private static final Logger LOGGER = Logger.getLogger
     * ("Invalid ages!* ");
     */
    public boolean validate(User user) {
        if (user.getAge() < 16) {
            System.out.println("Too young!" + user);
            throw new IllegalArgumentException("Invalid ages!");
        }
        return true;
    }
}
