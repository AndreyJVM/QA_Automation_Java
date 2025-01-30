package task4;

import com.codeborne.selenide.Condition;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Перейти на страницу http://uitestingplayground.com/ajax
 * Нажать на синюю кнопку
 * Получить текст из зеленой плашки
 * Вывести его в консоль (”Data loaded with AJAX get request.”)
 * */

public class ClickOnButton {

    public static void main(String[] args) {
        open("http://uitestingplayground.com/ajax");

       $("#ajaxButton").click();

        String textRequest = $("#content p").shouldBe(Condition.visible, Duration.ofSeconds(16)).getText();
        System.out.println("Text from the green field: \"" + textRequest +"\"");
    }
}
