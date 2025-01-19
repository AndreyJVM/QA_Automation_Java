package page.object;

import base.selenium.factory.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LabirintScenario {

    static WebDriver driver;
    private static By cartIconLocator = By.cssSelector(".b-header-b-personal-e-icon-count-m-cart");

    public static void main(String[] args) {
        driver = WebDriverFactory.create("chrome");

        open();
        searchFor("Java");
        addAllItemsToCart();
        checkIconText();
        openCart();


        String cardCounter = driver.findElement(By.cssSelector("#basket-default-prod-count2")).getText();
        System.out.println("Count products: " + cardCounter);

        String price = driver.findElement(By.cssSelector("#basket-default-sumprice-discount")).getText();
        System.out.println("Price: " + price);

        driver.quit();
    }


    private static void open() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://www.labirint.ru/books/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);
    }

    private static void searchFor(String term) {
        driver.findElement(By.cssSelector("#search-field")).click();
        driver.findElement(By.cssSelector("#search-field")).sendKeys("Java", Keys.ENTER);
    }

    private static void addAllItemsToCart() {
        int counter = 0;
        List<WebElement> cards = driver.findElements(By.cssSelector(".product-card"));
        for (WebElement card : cards) {
            card.findElement(By.cssSelector(".buy-link")).click();
            counter++;
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(cartIconLocator, String.valueOf(counter)));
    }

    private static void checkIconText() {
        String cardIconCounter = driver.findElement(cartIconLocator).getText();
        System.out.println("Count products: " + cardIconCounter);
    }

    private static void openCart() {
        driver.findElement(cartIconLocator).click();
    }
}