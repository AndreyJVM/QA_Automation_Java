package spting.frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import spting.factory.WebDriverFactory;

import java.util.List;

public class Frames {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://the-internet.herokuapp.com/nested_frames");

        List<WebElement> shouldBeEmpty = driver.findElements(By.cssSelector("body"));
        System.out.println(shouldBeEmpty.get(0).getText());

        driver.quit();
    }
}