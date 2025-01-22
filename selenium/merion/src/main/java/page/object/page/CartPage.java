package page.object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    private By cartIconLocator = By.cssSelector(".b-header-b-personal-e-icon-count-m-cart");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        driver.findElement(cartIconLocator).click();
    }

    public void checkCardCounter() {
        String cardCounter = driver.findElement(By.cssSelector("#basket-default-prod-count2")).getText();
        System.out.println("Count products: " + cardCounter);
    }

    public void checkPrice() {
        String price = driver.findElement(By.cssSelector("#basket-default-sumprice-discount")).getText();
        System.out.println("Price: " + price);

    }
}
