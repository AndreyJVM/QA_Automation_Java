package spting.settings.browsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

import java.util.ArrayList;
import java.util.Set;

public class SwitchTabs {
    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://the-internet.herokuapp.com/windows");

        driver.findElement(By.cssSelector("#content a")).click();

        Set<String> tabIds = driver.getWindowHandles();
        String firstTabId = driver.getWindowHandle();
        tabIds.remove(firstTabId);
        String sendTabId = new ArrayList<>(tabIds).get(0);

        driver.switchTo().window(sendTabId);

        driver.getCurrentUrl();

    }
}