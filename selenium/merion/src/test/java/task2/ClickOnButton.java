package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Перейти на страницу http://uitestingplayground.com/ajax
 * Нажать на синюю кнопку
 * Получить текст из зеленой плашки
 * Вывести его в консоль (”Data loaded with AJAX get request.”)
 * */

public class ClickOnButton {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://uitestingplayground.com/ajax");

        driver.findElement(By.cssSelector("#ajaxButton"))
                .click();

        String textRequest = driver.findElement(By.cssSelector("#content > p")).getText();
        System.out.println("Text from the green field: \"" + textRequest +"\"");

        driver.quit();
    }
}
