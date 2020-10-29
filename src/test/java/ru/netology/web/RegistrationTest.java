package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    @Test
    void shouldRegisterCardDelivery() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(text("Успешно!"), 15000).shouldBe(visible);
    }

    @Test
    void shouldNotFillCity() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=city].input_invalid").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotFillDate() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=date]").shouldHave(exactText("Неверно введена дата"));
    }


    @Test
    void shouldNotFillName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotFillPhone() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Мобильный телефон Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitAgreement() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $(byText("Забронировать")).click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }


    @Test
    void shouldFillWrongCity() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Клин");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=city].input_invalid").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillWrongDate() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=date]").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldFillWrongName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Belousova Anna");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFillWrongPhone() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillWrongPhone2() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+7926685810");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}

