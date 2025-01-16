package find.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.selenium.factory.WebDriverFactory;

public class Clear {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://the-internet.herokuapp.com/inputs");

        driver.findElement(By.cssSelector("input")).sendKeys("12345");
        driver.findElement(By.cssSelector("input")).clear();

        driver.quit();
    }
}
