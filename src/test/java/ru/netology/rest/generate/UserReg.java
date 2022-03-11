package ru.netology.rest.generate;

import lombok.AllArgsConstructor;
import lombok.Value;

    @Value
    @AllArgsConstructor
    public class UserReg {
        String login;
        String password;
        String status;
}
