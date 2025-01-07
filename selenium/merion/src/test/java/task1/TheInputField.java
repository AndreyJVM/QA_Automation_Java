package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TheInputField {

    /**
     * http://the-internet.herokuapp.com/inputs
     * Enter the text 1000 in the field
     * Clear this field (clear method)
     * and enter the text 2000 in the same field.
     * */

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://the-internet.herokuapp.com/inputs");

        WebElement inputField = driver.findElement(By.cssSelector("[type='number']"));
        inputField.clear();
        inputField.sendKeys("1000");
        inputField.clear();
        inputField.sendKeys("2000");

        driver.quit();
    }
}
