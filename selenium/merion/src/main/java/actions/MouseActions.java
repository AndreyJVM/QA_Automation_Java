package actions;

import base.selenium.factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class MouseActions {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://jspaint.app/#local:241221908e26d");

        WebElement canvas = driver.findElement(By.cssSelector(".main-canvas"));

        new Actions(driver)
                .clickAndHold(canvas)
                .moveByOffset(100, -50)
                .perform();
        driver.quit();
    }
}
