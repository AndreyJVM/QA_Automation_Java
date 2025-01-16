package base.selenium;

import org.openqa.selenium.WebDriver;
import base.selenium.factory.WebDriverFactory;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://google.com");
    }
}