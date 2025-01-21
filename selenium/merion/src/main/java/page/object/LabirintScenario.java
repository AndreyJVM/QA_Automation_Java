package page.object;

import base.selenium.factory.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.object.page.MainPage;
import page.object.page.ResultPage;

import java.time.Duration;
import java.util.List;

public class LabirintScenario {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");
        MainPage mainPage = new MainPage(driver);
        ResultPage resultPage = new ResultPage(driver);

        mainPage.open();
        mainPage.searchFor("Java");
        resultPage.addAllItemsToCart();
        resultPage.checkIconText();
        openCart();
        checkCardCounter();
        checkPrice();

        driver.quit();
    }

    private static void openCart() {
        driver.findElement(cartIconLocator).click();
    }

    private static void checkCardCounter() {
        String cardCounter = driver.findElement(By.cssSelector("#basket-default-prod-count2")).getText();
        System.out.println("Count products: " + cardCounter);
    }

    private static void checkPrice() {
        String price = driver.findElement(By.cssSelector("#basket-default-sumprice-discount")).getText();
        System.out.println("Price: " + price);

    }

}