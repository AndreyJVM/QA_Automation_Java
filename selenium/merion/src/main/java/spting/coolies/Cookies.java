package spting.coolies;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

import java.util.Date;

public class Cookies {
    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://www.labirint.ru/");

        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);

        driver.navigate().refresh();

    }
}
