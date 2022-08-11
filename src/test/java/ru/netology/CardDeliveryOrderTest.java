package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    private String day;

    @BeforeEach
    void dateSet() {
        Calendar calendar = Calendar.getInstance();
        day = Integer.toString(calendar.get(Calendar.DATE) + Calendar.DAY_OF_WEEK);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void test() {
//        $$x("//input[@type='text']").get(0).setValue("Мос").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        $$x("//input[@type='text']").get(0).setValue("Мос");
        $$("[class='menu-item__control']").find((exactText("Смоленск"))).click();
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $$("table.calendar__layout td").find(text(day)).click();
        $x("//input[@name='name']").setValue("Иван Иванов");
        $("[name='phone']").setValue("+79672699504");
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='notification']").should(visible, Duration.ofSeconds(15));
    }
}
