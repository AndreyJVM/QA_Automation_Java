package tesk3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalculatorPage {
    private final WebDriver driver;
    private final int timeout = 7;

    private final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html";

    private static final String KEYS_CSS = ".keys";
    private static final String DELAY_CSS = "#delay";

    @FindBy(css = KEYS_CSS)
    private WebElement keys;

    @FindBy(css = DELAY_CSS)
    private WebElement delay;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public CalculatorPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public void sendKeysDelay() {
        delay.clear();
        delay.sendKeys(String.valueOf(timeout));
    }

    public void press(String value) {
        keys.findElement(By.xpath("//*[text() = '" + value + "']")).click();
    }
}