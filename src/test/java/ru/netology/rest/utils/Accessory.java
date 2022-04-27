package ru.netology.rest.utils;

import ru.netology.rest.generate.UserReg;

import static ru.netology.rest.utils.DataGenerator.faker;
import static ru.netology.rest.utils.DataGenerator.sendRequest;

public class Accessory {
    public static String getRandomLogin() {
        return faker.name().username();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static class Registration {
        private Registration() {

        }

        public static UserReg getUser(String status) {
            UserReg user = new UserReg(
                    getRandomLogin(),
                    getRandomPassword(),
                    status);
            return user;
        }

        public static UserReg getRegisteredUser(String status) {
            var registeredUser = getUser(status);
            sendRequest(registeredUser);
            return registeredUser;
        }
    }

}
