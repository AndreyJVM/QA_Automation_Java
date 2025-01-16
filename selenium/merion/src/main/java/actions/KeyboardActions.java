package actions;

import base.selenium.factory.WebDriverFactory;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class KeyboardActions {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("http://uitestingplayground.com/textinput");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        Actions act = new Actions(driver);

        By locator = By.cssSelector("#newButtonName");
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        act
                .keyDown(Keys.LEFT_SHIFT)
                .pause(Duration.ofSeconds(4))
                .sendKeys(driver.findElement(locator), "merion")
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.LEFT_SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("cvvv")
                .keyUp(cmdCtrl)
                .perform();

        driver.quit();
    }
}