package base.selenium.navigate;

import org.openqa.selenium.WebDriver;
import base.selenium.factory.WebDriverFactory;

public class Navigation {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://habr.com/ru");
        driver.navigate().refresh();

        driver.navigate().to("https://rzd.ru");
        driver.navigate().back();
        driver.navigate().forward();

        driver.quit();
    }
}