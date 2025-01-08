package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class OpenMerionAcademy {

    /**
     * Open the chrome browser
     * Go to the page google.com
     * In the search bar, write "Merion Academy wiki"
     * Press Enter (Keys.RETURN)
     * On the results page, select the first link and click on it
     * After the transition, get the current URL:
     * */

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://google.com");

        WebElement searchBar = driver.findElement(By.cssSelector("#APjFqb"));
        searchBar.clear();
        searchBar.sendKeys("Merion Academy wiki");
        searchBar.sendKeys(Keys.ENTER);

        List<WebElement> resultList = driver.findElements(By.cssSelector("#res a[href]"));
        resultList.get(0).click();
        System.out.println(driver.getCurrentUrl());

        driver.quit();
    }
}