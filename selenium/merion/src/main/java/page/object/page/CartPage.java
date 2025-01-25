package page.object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage checkCardCounter() {
        String cardCounter = driver.findElement(By.cssSelector("#basket-default-prod-count2")).getText();
        System.out.println("Count products: " + cardCounter);
        return this;
    }

    public CartPage checkPrice() {
        String price = driver.findElement(By.cssSelector("#basket-default-sumprice-discount")).getText();
        System.out.println("Price: " + price);
        return this;
    }
}