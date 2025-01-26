package tesk3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import tesk3.page.CalculatorPage;

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

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        CalculatorPage calculatorPage = new CalculatorPage(driver).open();

        calculatorPage.sendKeysDelay();

        calculatorPage.press("7");
        calculatorPage.press("+");
        calculatorPage.press("8");
        calculatorPage.press("=");

        driver.quit();
    }
}