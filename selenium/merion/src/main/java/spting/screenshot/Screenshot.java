package spting.screenshot;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

import java.io.File;

public class Screenshot {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://google.com");

        ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE).renameTo(new File("res.png"));

        driver.quit();
    }
}
