package page.object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://www.labirint.ru/books/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);
    }

    public void searchFor(String term) {
        driver.findElement(By.cssSelector("#search-field")).click();
        driver.findElement(By.cssSelector("#search-field")).sendKeys("Java", Keys.ENTER);
    }
}
