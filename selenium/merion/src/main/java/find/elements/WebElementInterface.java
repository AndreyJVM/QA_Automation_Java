package find.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.selenium.factory.WebDriverFactory;

import java.util.List;

public class WebElementInterface {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://habr.com/ru");
        // login button
        WebElement login = driver.findElement(By.cssSelector(".tm-header-user-menu__login"));

        // output list links class "a"
        WebElement nav = driver.findElement(By.cssSelector("nav"));
        List<WebElement> links = nav.findElements(By.cssSelector("a"));
        System.out.println(links.size()); // 8

        driver.quit();
    }
}
