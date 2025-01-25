package page.object;

import base.selenium.factory.WebDriverFactory;
import org.openqa.selenium.*;
import page.object.page.CartPage;
import page.object.page.HeaderElement;
import page.object.page.MainPage;
import page.object.page.ResultPage;

public class LabirintScenario {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.getHeaderElement().searchFor("Java");
        //mainPage.getHeaderElement().searchFor("Java");

        ResultPage resultPage = new ResultPage(driver);
        resultPage.addAllItemsToCart();
        resultPage.getHeaderElement().checkIconText();


        CartPage cartPage = resultPage.getHeaderElement().openCartIcon();
        cartPage
                .checkCardCounter()
                .checkPrice();

        driver.quit();
    }
}