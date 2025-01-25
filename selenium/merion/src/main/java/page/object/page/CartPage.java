package page.object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCardCounter() {
        return driver.findElement(By.cssSelector("#basket-default-prod-count2")).getText();
    }

    public String getCartPrice() {
        return driver.findElement(By.cssSelector("#basket-default-sumprice-discount")).getText();
    }
}