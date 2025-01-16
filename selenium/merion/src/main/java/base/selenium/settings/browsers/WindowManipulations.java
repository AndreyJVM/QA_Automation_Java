package base.selenium.settings.browsers;

import org.openqa.selenium.WebDriver;
import base.selenium.factory.WebDriverFactory;

public class WindowManipulations {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        driver.quit();
    }
}
