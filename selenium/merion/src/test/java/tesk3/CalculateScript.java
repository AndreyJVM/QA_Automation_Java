package tesk3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

        int timeout = 7;

        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        driver.findElement(By.cssSelector("#delay"))
                .clear();
        driver.findElement(By.cssSelector("#delay"))
                .sendKeys(String.valueOf(timeout));

        WebElement keyboard = driver.findElement(By.cssSelector(".keys"));

        keyboard.findElement(By.xpath("//*[text() = '7']")).click();
        keyboard.findElement(By.xpath("//*[text() = '+']")).click();
        keyboard.findElement(By.xpath("//*[text() = '8']")).click();
        keyboard.findElement(By.xpath("//*[text() = '=']")).click();

        // write

        driver.quit();
    }
}