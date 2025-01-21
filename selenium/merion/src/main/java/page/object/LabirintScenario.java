package page.object;

import base.selenium.factory.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.object.page.CartPage;
import page.object.page.MainPage;
import page.object.page.ResultPage;

import java.time.Duration;
import java.util.List;

public class LabirintScenario {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");
        MainPage mainPage = new MainPage(driver);
        ResultPage resultPage = new ResultPage(driver);
        CartPage cartPage = new CartPage(driver);

        mainPage.open();
        mainPage.searchFor("Java");
        resultPage.addAllItemsToCart();
        resultPage.checkIconText();
        cartPage.openCart();
        cartPage.checkCardCounter();
        cartPage.checkPrice();

        driver.quit();
    }
}