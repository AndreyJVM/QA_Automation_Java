package task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Перейти на сайт https://bonigarcia.dev/selenium-webdriver-java/loading-images.html
 * Дождаться загрузки 3й картинки
 * Получить значение атрибута src у 3й картинки
 * Вывести значение в консоль
 * */

public class WaitPicture {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#spinner")));

        List<WebElement> images = driver.findElement(By.cssSelector("#image-container"))
                        .findElements(By.cssSelector("img"));

        String src = images.get(2).getAttribute("src");
        System.out.println(src);

        driver.quit();
    }
}