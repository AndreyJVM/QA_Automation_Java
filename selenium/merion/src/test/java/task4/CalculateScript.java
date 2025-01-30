package task4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Открыть страницу https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html
 * В поле ввода по локатору #delay ввести значение 7 (timeout)
 * Нажать на кнопки
        * 7
        * +(плюс)
        * 8
        * =
 * Дождаться результата. Вывести его в консоль.
 * */

public class CalculateScript {
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html";
    private static final int timeout = 7;

    public static void main(String[] args) {
        open(BASE_URL);

        $("#delay").clear();
        $("#delay").sendKeys(String.valueOf(timeout));

        //SelenideElement keyboard = $(".keys");

        $(".keys").$x("//*[text() = '7']").click();
        $(".keys").$x("//*[text() = '+']").click();
        $(".keys").$x("//*[text() = '8']").click();
        $(".keys").$x("//*[text() = '=']").click();
    }
}