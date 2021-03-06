package ru.netology.rest.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.rest.generate.UserReg;
import ru.netology.rest.utils.Accessory;
import ru.netology.rest.utils.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestMode {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    void shouldSuccessfulLoginRegisteredUser() {


        var user = Accessory.Registration.getUser("active");
        DataGenerator.sendRequest(user);
        $("[data-test-id='login'] input").val(user.getLogin());
        $("[data-test-id='password'] input").val(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[id='root']").shouldBe(Condition.exactText("Личный кабинет"));
    }

    @Test
    void shouldLoginBlockedUser() {
        UserReg user = Accessory.Registration.getUser("blocked");
        DataGenerator.sendRequest(user);
        $("[data-test-id='login'] input").val(user.getLogin());
        $("[data-test-id='password'] input").val(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[class='notification__content']").shouldBe(Condition.exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldInvalidLogin() {
        UserReg user = Accessory.Registration.getRegisteredUser("active");
        DataGenerator.sendRequest(user);
        var anotherLogin = Accessory.getRandomLogin();
        $("[data-test-id='login'] input").val(anotherLogin);
        $("[data-test-id='password'] input").val(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[class='notification__content']").shouldBe(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldInvalidPassword() {
        UserReg user = Accessory.Registration.getRegisteredUser("active");
        DataGenerator.sendRequest(user);
        var anotherPassword = Accessory.getRandomPassword();
        $("[data-test-id='login'] input").val(user.getLogin());
        $("[data-test-id='password'] input").val(anotherPassword);
        $("[data-test-id='action-login']").click();
        $("[class='notification__content']").shouldBe(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginNotRegisteredUser() {
        var notRegisteredUser = Accessory.Registration.getUser("active");
        $("[data-test-id='login'] input").val(notRegisteredUser.getLogin());
        $("[data-test-id='password'] input").val(notRegisteredUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[class='notification__content']").shouldBe(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }
}
