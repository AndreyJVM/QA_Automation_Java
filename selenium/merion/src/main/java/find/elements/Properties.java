package find.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

public class Properties {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://habr.com/ru");

        String target1 = driver.findElement(By.cssSelector(".tm-feature__link")).getDomProperty("target");
        String target2 = driver.findElement(By.cssSelector(".tm-feature__link")).getDomAttribute("target");
        String target3 = driver.findElement(By.cssSelector(".tm-feature__link")).getCssValue("target");

        driver.quit();
    }
}