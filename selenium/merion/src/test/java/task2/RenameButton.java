package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Перейти на сайт http://uitestingplayground.com/textinput
 * Указать в поле ввода текст "Merion"
 * Нажать на синюю кнопку
 * Получить текст кнопки и вывести в консоль (Merion)
 * */
public class RenameButton {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://uitestingplayground.com/textinput");

        driver.findElement(By.cssSelector("#newButtonName"))
                .sendKeys("Merion");

        driver.findElement(By.cssSelector("#updatingButton"))
                .click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("#updatingButton"), "Merion"));

        System.out.println("Button text: " +
                driver.findElement(By.cssSelector("#updatingButton")).getText());

        driver.quit();
    }
}