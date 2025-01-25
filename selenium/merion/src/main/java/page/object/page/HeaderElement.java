package page.object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HeaderElement {

    private final WebDriver driver;

    private By cartIconLocator = By.cssSelector(".b-header-b-personal-e-icon-count-m-cart");

    public HeaderElement(WebDriver driver) {
        this.driver = driver;
    }

    public ResultPage searchFor(String term) {
        driver.findElement(By.cssSelector("#search-field")).click();
        driver.findElement(By.cssSelector("#search-field")).sendKeys("Java", Keys.ENTER);
        return new ResultPage(driver);
    }

    public void getIconText() {
        String cardIconCounter = driver.findElement(cartIconLocator).getText();
        System.out.println("Count products: " + cardIconCounter);
    }

    public CartPage openCartIcon() {
        driver.findElement(cartIconLocator).click();
        return new CartPage(driver);
    }
}