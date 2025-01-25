package page.object.page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final WebDriver driver;
    private final HeaderElement headerElement;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.headerElement = new HeaderElement(driver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public WebDriver getDriver() {
        return driver;
    }
}