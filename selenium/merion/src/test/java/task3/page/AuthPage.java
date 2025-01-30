package task3.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AuthPage {
    private final WebDriver driver;
    private final String BASE_URL = "https://www.saucedemo.com/";

    private static final String USER_NAME_CSS = "#user-name";
    private static final String PASSWORD_CSS = "#password";
    private static final String LOGIN_BUTTON_CSS = "#login-button";


    @FindBy(css = USER_NAME_CSS)
    private WebElement usernameCss;

    @FindBy(css = PASSWORD_CSS)
    private WebElement passwordCss;

    @FindBy(css = LOGIN_BUTTON_CSS)
    private WebElement loginButtonCss;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public AuthPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public CatalogPage loginAs(String username, String password) {
        usernameCss.sendKeys(username);
        passwordCss.sendKeys(password);
        loginButtonCss.click();
        return new CatalogPage(driver);
    }
}