package timeouts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Progressbar {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(100));

        driver.get("http://uitestingplayground.com/progressbar");

        driver.findElement(By.cssSelector("#startButton"))
                .click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("#progressBar"), "75%"));

        driver.findElement(By.cssSelector("#stopButton"))
                .click();

        driver.quit();
    }
}