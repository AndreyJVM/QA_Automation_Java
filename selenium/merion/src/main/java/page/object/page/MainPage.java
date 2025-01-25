package page.object.page;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.labirint.ru/books/");
    }
}