package base.selenium.settings.browsers;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import base.selenium.factory.WebDriverFactory;

public class WindowPositions {
    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");

        Point position = driver.manage().window().getPosition();

        System.out.println(position);

        driver.manage().window().setPosition(new Point(100, 100));

        driver.quit();
    }
}