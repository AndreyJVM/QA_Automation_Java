package spting.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import spting.factory.WebDriverFactory;

import java.util.List;

public class Alerts {
    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        List<WebElement> buttons = driver.findElements(By.cssSelector("ul li button"));

        buttons.get(0).click();

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();

        buttons.get(1).click();
        alert = driver.switchTo().alert();
        text = alert.getText();
        alert.dismiss();

        buttons.get(2).click();
        alert = driver.switchTo().alert();
        text = alert.getText();
        alert.sendKeys("Hello!");
        alert.accept();

        driver.quit();
    }
}
