package find.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

public class SendKeys {

    public static void main(String[] args) {
        WebDriver driverTheInternet = WebDriverFactory.create("chrome");
        driverTheInternet.get("http://uitestingplayground.com/textinput");

        driverTheInternet.findElement(By.cssSelector("#newButtonName"))
                .sendKeys("Merion");

        driverTheInternet.findElement(By.cssSelector("#newButtonName"))
                .sendKeys(Keys.BACK_SPACE);

        driverTheInternet.findElement(By.cssSelector("#newButtonName"))
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.ARROW_UP));

        driverTheInternet.findElement(By.cssSelector("#updatingButton"))
                .click();

        driverTheInternet.quit();
    }
}