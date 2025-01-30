package task3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartCheckoutPage {
    private final WebDriver driver;
    private final String BASE_URL = "https://www.saucedemo.com/cart.html";

    public CartCheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartCheckoutPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public CartCheckoutPage clickCheckout() {
        driver.findElement(By.cssSelector("#checkout")).click();
        return this;
    }

    public CartCheckoutPage setContactData(String firstName, String secondName, String postalCode) {
        driver.findElement(By.cssSelector("#first-name")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#last-name")).sendKeys(secondName);
        driver.findElement(By.cssSelector("#postal-code")).sendKeys(postalCode);
        driver.findElement(By.cssSelector("#continue")).click();
        return this;
    }

    public String getTotalPrice() {
        return driver.findElement(By.cssSelector(".summary_total_label")).getText();
    }
}