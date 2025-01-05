package spting.navigate;

import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

public class GetCurrentInfo {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://habr.com/ru");

        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();
        String pageSource = driver.getPageSource();

        driver.quit();
    }
}
