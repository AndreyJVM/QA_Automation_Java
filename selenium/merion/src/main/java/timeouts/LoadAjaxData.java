package timeouts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import spting.factory.WebDriverFactory;

import java.time.Duration;

public class LoadAjaxData {
    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(16));
        driver.get("http://uitestingplayground.com/ajax");

        driver.findElement(By.cssSelector("#ajaxButton"))
                .click();


        String content = driver.findElement(By.cssSelector("#content p")).getText();
        System.out.println(content);

        driver.quit();
    }
}