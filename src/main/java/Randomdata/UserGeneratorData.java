package Randomdata;

import Models.User;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGeneratorData {
    public static User getRandomUser() {
        String name = "Andrew" + RandomStringUtils.randomAlphabetic(6);
        String email = name.toLowerCase() + "@mail.ru";
        String password = "YS" + RandomStringUtils.randomAlphabetic(9);

        // Убрали Allure.addAttachment, чтобы не ломать контекст запуска JUnit

        return new User(email, password, name);
    }
}
