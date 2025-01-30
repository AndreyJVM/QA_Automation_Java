package task3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/data-types.html";
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RegisterPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public void set(String field, String value) {
        driver.findElement(By.cssSelector("[name=" + field +"]")).sendKeys(value);
    }

    public void clickTypeSubmit() {
        driver.findElement(By.cssSelector("[type=submit]"))
                .click();
    }

    public String getCssProperty(String field, String cssProperty) {
        return driver.findElement(By.cssSelector(field)).getCssValue(cssProperty);
    }
}