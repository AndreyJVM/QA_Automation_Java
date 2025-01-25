package page.object.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected final WebDriver driver;
    public final HeaderElement headerElement;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.headerElement = PageFactory.initElements(driver, HeaderElement.class);
    }

    public WebDriver getDriver() {
        return driver;
    }
}