package tesk3.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/data-types.html";
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(BASE_URL);
    }
}