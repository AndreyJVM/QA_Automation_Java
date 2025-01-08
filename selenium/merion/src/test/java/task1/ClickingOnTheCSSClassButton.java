package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ClickingOnTheCSSClassButton {

    /**
     * Open a page http://uitestingplayground.com/classattr
     * Click on the blue button
     * Run the script 3 times. Make sure that the code does not need to be edited – the script always works.
     * */

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://uitestingplayground.com/classattr");

        driver.findElement(By.cssSelector(".btn .btn-primary"))
                .click();

        driver.quit();
    }
}
