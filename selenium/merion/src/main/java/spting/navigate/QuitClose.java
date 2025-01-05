package spting.navigate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

public class QuitClose {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://the-internet.herokuapp.com/windows");

        driver.findElement(By.cssSelector("#content a")).click();

        /**
         * Close the current window, quitting the browser if it's the last window currently open.
         *
         * <p>See <a href="https://w3c.github.io/webdriver/#close-window">W3C WebDriver specification</a>
         * for more details.
         */
        driver.close();

        /**
         * Quits this driver, closing every associated window.
         */
        driver.quit();
    }
}