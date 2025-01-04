package spting;

import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://google.com");
    }
}