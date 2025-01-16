package find.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.selenium.factory.WebDriverFactory;

public class Click {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://habr.com/ru");

        // login button
        WebElement login = driver.findElement(By.cssSelector(".tm-header-user-menu__login"));
        login.click();

        driver.quit();


        /**
         * 'click()'
         * @throws StaleElementReferenceException If the element no longer exists as initially defined
         * ! - Don`t use variable WebElement
         */

        WebDriver driverTheInternet = WebDriverFactory.create("chrome");
        driverTheInternet.get("http://uitestingplayground.com/click");

        // button "Button That Ignores DOM Click Event"
        WebElement buttonThatIgnoresDOMClickEvent = driverTheInternet.findElement(By.cssSelector("#badButton"));
        buttonThatIgnoresDOMClickEvent.click();

        driverTheInternet.navigate().refresh();
        buttonThatIgnoresDOMClickEvent.click();

        driverTheInternet.quit();
    }
}