package timeouts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.selenium.factory.WebDriverFactory;

import java.time.Duration;

public class ECDemo {
    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        /**
         * Wait until an element is no longer attached to the DOM.
         *
         * @param element The element to wait for.
         * @return false if the element is still attached to the DOM, true otherwise.
         */
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        /**
         * An expectation for checking number of WebElements with given locator
         *
         * @param locator used to find the element
         * @param number used to define number of elements
         * @return Boolean true when size of elements list is equal to defined
         */
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 7));

        /**
         * An expectation for checking that an element, known to be present on the DOM of a page, is
         * visible. Visibility means that the element is not only displayed but also has a height and
         * width that is greater than 0.
         *
         * @param element the WebElement
         * @return the (same) WebElement once it is visible
         */
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));

        // alert/confirm/prompt
        wait.until(ExpectedConditions.alertIsPresent());

        /**
         * An expectation for checking that an element is present on the DOM of a page. This does not
         * necessarily mean that the element is visible.
         *
         * @param locator used to find the element
         * @return the WebElement once it is located
         */
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        /**
         * An expectation for checking WebElement with given locator has attribute which contains specific
         * value
         *
         * @param locator used to define WebElement to check its parameters
         * @param attribute used to define css or html attribute
         * @param value used as expected attribute value
         * @return Boolean true when element has css or html attribute which contains the value
         */
        wait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "class", "btn-success"));

        wait.until(ExpectedConditions.titleIs("Message"));
        wait.until(ExpectedConditions.titleContains("Message"));


    }
}