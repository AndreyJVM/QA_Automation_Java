package page.object;

import base.selenium.factory.WebDriverFactory;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.List;

public class LabirintScenario {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.get("https://www.labirint.ru/books/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);

        driver.findElement(By.cssSelector("#search-field")).click();
        driver.findElement(By.cssSelector("#search-field")).sendKeys("Java", Keys.ENTER);

        List<WebElement> cards = driver.findElements(By.cssSelector(".product-card"));
        for (WebElement card : cards) {
            card.findElement(By.cssSelector(".buy-link")).click();
        }

        WebElement cardIcon = driver.findElement(By.cssSelector(".b-header-b-personal-e-icon-count-m-cart"));
        String cardIconCounter = cardIcon.getText();
        System.out.println("Count products: " + cardIconCounter);
        cardIcon.click();

        String cardCounter = driver.findElement(By.cssSelector("#basket-default-prod-count2")).getText();
        System.out.println("Count products: " + cardCounter);

        String price = driver.findElement(By.cssSelector("#basket-default-sumprice-discount")).getText();
        System.out.println("Price: " + price);

        driver.quit();
    }
}